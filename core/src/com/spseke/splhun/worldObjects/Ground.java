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
        super.create(world);
        texture = new Texture("upjs_ground.jpeg");
        sprite = new Sprite(texture);

        sprite.setSize(50  ,2);


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -11);

         body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);


        body.setUserData(this);

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
                x - sprite.getWidth()/2,
                y - sprite.getHeight()/2
        );
    }

    @Override
    public void setRotation(float v) {
//        sprite.setRotation(v);
    }
}
