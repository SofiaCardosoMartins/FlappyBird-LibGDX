package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyGameClass;
import com.mygdx.game.sprites.Bird;
import com.mygdx.game.sprites.Ground;
import com.mygdx.game.sprites.Tube;

public class PlayState extends State{

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -80;

    private Bird bird;
    private Texture bg;
    private Ground ground;
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,300);
        ground = new Ground(cam.position.x - (cam.viewportWidth/2),(cam.position.x - (cam.viewportWidth/2))+Ground.groundTexture.getWidth(),GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        bg = new Texture("bg.png");
        cam.setToOrtho(false, FlappyGameClass.WIDTH/2,FlappyGameClass.HEIGHT/2);

        for(int i = 1; i<=TUBE_COUNT;i++)
        {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float dt) {
        handleInput();  //should always be in the update method and always come first
        bird.update(dt);
        ground.update(cam);
        cam.position.x = bird.getPosition().x + 100;

        for(Tube tube: tubes){
            if((cam.position.x - (cam.viewportWidth / 2 )) > tube.getPosTopTube().x + tube.getTopTube().getWidth())
                tube.reposition(tube.getPosBotTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));

            if(tube.collides(bird.getBounds()))
            {
                gsm.set(new PlayState(gsm));
                break;
            }
        }

        if(bird.getPosition().y <= Ground.groundTexture.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new PlayState(gsm));

        cam.update(); //to tell libgdx that the camera has been repositioned
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg,cam.position.x - (cam.viewportWidth / 2),0);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        sb.draw(Ground.groundTexture,ground.getGroundX1(),ground.getGroundY());
        sb.draw(Ground.groundTexture,ground.getGroundX2(),ground.getGroundY());

        for(Tube tube: tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.end();

    }

    @Override
    public void dispose()
    {
        bg.dispose();
        bird.dispose();
        for(Tube tube: tubes)
            tube.dispose();
    }
}
