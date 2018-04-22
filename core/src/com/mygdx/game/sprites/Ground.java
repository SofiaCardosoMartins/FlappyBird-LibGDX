package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ground {

    private Vector2 groundPos1, groundPos2;
    public static final Texture groundTexture = new Texture("ground.png");

    public Ground(float groundX1, float groundX2, float groundY)
    {
        groundPos1 = new Vector2(groundX1,groundY);
        groundPos2 = new Vector2(groundX2,groundY);
    }

    public void update(OrthographicCamera cam)
    {
        if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + groundTexture.getWidth())
            groundPos1.add(groundTexture.getWidth() * 2, 0);

        if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + groundTexture.getWidth())
            groundPos2.add(groundTexture.getWidth() * 2, 0);
    }

    public float getGroundX1()
    {
        return groundPos1.x;
    }

    public float getGroundX2()
    {
        return groundPos2.x;
    }

    public float getGroundY()
    {
        return groundPos1.y;
    }

}
