package com.spseke.splhun;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Entity {

    int zIndex = 0;

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }


    protected  BodyDef bodyDef = new BodyDef();
    private Body body;

    public abstract void setPosition(float x, float y);

    public abstract void setRotation(float v);

    public abstract void create(World world);

    public abstract void update();

    public abstract Sprite getSprite();

    public void dispose(){}


    public Body getBody() {
        return body;
    }

}
