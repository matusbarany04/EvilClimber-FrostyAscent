package com.spseke.splhun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class RenderSystem {

    private Array<Body> bodies = new Array<>();

    World world;

    public RenderSystem(World world) {
        this.world = world;
    }


    public void update(SpriteBatch batch) {
        world.getBodies(bodies);

        for (Body b : bodies)
        {
            Entity e = (Entity) b.getUserData();
            if(e != null){
                e.setPosition(b.getPosition().x, b.getPosition().y);
                e.setRotation(MathUtils.radiansToDegrees * b.getAngle());
                e.update();

                e.getSprite().draw(batch);
            }
        }
    }
}