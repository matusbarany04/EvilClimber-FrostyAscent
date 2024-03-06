package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;
import com.spseke.splhun.MyGdxGame;

public class Ground extends Entity {


    Texture texture;
    BodyDef bodyDef;
    int x;
    int y;
    Sprite sprite;

    float width = 100;
    float height = 1;

    PolygonShape rectangle;


    public Ground(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void create(World world) {

//        texture = new Texture("upjs_ground.jpeg");
//        sprite = new Sprite(texture);
//
//        sprite.setSize(Gdx.graphics.getWidth(), texture.getHeight());


        // create a new body definition (type and location)
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);
        // add it to the world
        Body bodys = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        rectangle = new PolygonShape();
        rectangle.setAsBox(width, height);
        // create the physical object in our body)
        // without this our body would just be data in the world
        bodys.createFixture(rectangle, 0.0f);
        // we no longer use the shape object here so dispose of it.
        rectangle.dispose();

        bodys.setUserData(this);


        rectangle.dispose();
    }

    @Override
    public void update() {

    }


    public void dispose() {
        texture.dispose();
    }


    public Texture getTexture() {
        return texture;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }


    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void setRotation(float v) {
        sprite.setRotation(v);
    }
}
