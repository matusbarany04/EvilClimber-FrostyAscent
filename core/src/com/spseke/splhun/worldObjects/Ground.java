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
import com.spseke.splhun.groups.Layers;

public class Ground extends Entity {


    Texture texture;
    int x;
    int y;
    Sprite sprite;

    float width = 50;
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
        texture = new Texture("matus-right.png");
        sprite = new Sprite(texture);
        sprite.setTexture(texture);



        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x,y);

         body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);


        shape.dispose();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);


        body.setUserData(this);

    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void update() {
        float aspectRatio = (float) sprite.getWidth() / sprite.getHeight();

//        System.out.println(aspectRatio);
//        System.out.println(sprite.getWidth() + "width");

        sprite.setSize(50,50);

        sprite.setOrigin(
                sprite.getWidth() / 2,
                sprite.getHeight() / 2
        );
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
        sprite.setRotation(v);

    }
}
