package com.spseke.splhun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.spseke.splhun.groups.LayerManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class RenderSystem {

    private Array<Body> bodies = new Array<>();

    World world;
    Camera camera;


    public RenderSystem(World world, Camera camera) {
        this.world = world;
        this.camera = camera;
    }


    public void update(SpriteBatch batch) {
        world.getBodies(bodies);

        ArrayList<ArrayList<Entity>> layers =  LayerManager.getLayers();

        for (ArrayList<Entity> layer : layers) {

            for (Entity e  : layer) {

                Vector3 spritePosition = new Vector3(
                        e.getBody().getPosition().x,
                        e.getBody().getPosition().y,
                        0);


                e.setPosition(
                        spritePosition.x ,
                        spritePosition.y
                );

                e.setRotation(MathUtils.radiansToDegrees * e.getBody().getAngle());
                e.update();

                e.getSprite().draw(batch);

            }
        }

    }
}