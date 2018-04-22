package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture animationTexture;
    private Animation animation;
    private Sound flap;

    public Bird(int x, int y)
    {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        animationTexture = new Texture("birdanimation.png");
        animation = new Animation(new TextureRegion(animationTexture),3,0.5f);
        bounds = new Rectangle(x,y,animationTexture.getWidth()/3,animationTexture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt)
    {
        animation.update(dt);
        if(position.y > 0)
            velocity.add(0,GRAVITY,0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        velocity.scl(1/dt); //restore velocity to it's initial state

        if(position.y < 0)
            position.y = 0;

        bounds.setPosition(position.x,position.y);
    }

    public void jump()
    {
        velocity.y = 250;
        flap.play(0.5f);
    }

    public void dispose()
    {
        animationTexture.dispose();
        flap.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return animation.getFrame();
    }

    public Rectangle getBounds()
    {
        return bounds;
    }
}
