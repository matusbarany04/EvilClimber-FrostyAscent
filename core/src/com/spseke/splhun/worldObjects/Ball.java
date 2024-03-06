package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;
import com.spseke.splhun.MyGdxGame;
import com.spseke.splhun.Units;

public class Ball extends Entity {

    float x;
    float y;
    int radius;

    Sprite sprite;
    Texture img;

    float density = 1f;

    CircleShape circle;

    public Ball(float x, float y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(
                x - radius,
                y - radius
        );
    }

    @Override
    public void setRotation(float v) {
        sprite.setRotation(v);
    }

    @Override
    public void create(World world) {
        img = new Texture("python.png");
        sprite = new Sprite(img);
//
        sprite.setOrigin(radius, radius);
        sprite.setSize(2 * radius, 2 * radius);


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        circle = new CircleShape();
        circle.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = density;

        body.createFixture(fixtureDef);


        body.setUserData(this);

    }


    @Override
    public void update() {

    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }


    @Override
    public void dispose() {
        circle.dispose();
    }
}
