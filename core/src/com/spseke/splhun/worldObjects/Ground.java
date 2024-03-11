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
    int x;
    int y;
    Sprite sprite;

    float width = 100;
    float height = 1;


    public Ground() {
        this.x = 0;
        this.y = 0;
    }
    public Ground(int x, int y) {
        this.x = x;
        this.y = y;

    }

    @Override
    public void create(World world) {
        texture = new Texture("upjs_ground.jpeg");
        sprite = new Sprite(texture);
        sprite.setTexture(texture);
        int size = 50 * 10 ;
        sprite.setOrigin(size  , size );
        sprite.setSize(width * 100  , height * 100);
//        sprite.setPosition(Gdx.graphics.getWidth() /2 ,Gdx.graphics.getHeight() /2 );


        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);

        // add it to the world
        Body bodys = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        // create the physical object in our body)
        // without this our body would just be data in the world
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1f;

        bodys.createFixture(fixtureDef);

//        bodys.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        bodys.setUserData(this);

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
        sprite.setPosition(
                x  ,
                y + height/2
        );
    }

    @Override
    public void setRotation(float v) {
//        sprite.setRotation(v);
    }
}
