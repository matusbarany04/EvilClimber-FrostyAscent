package com.spseke.splhun;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.groups.LayerManager;
import com.spseke.splhun.groups.Layers;

public abstract class Entity {

    Layers layer = Layers.FOREGROUND1;

    public Layers getLayer() {
        return layer;
    }


    public void configureLayer(Layers layer) {
        this.layer = layer;
        LayerManager.registerEntity(this, layer);
    }

    protected  BodyDef bodyDef = new BodyDef();
    protected Body body;

    public abstract void setPosition(float x, float y);

    public abstract void setRotation(float v);

    public void create(World world){
        configureLayer(layer);
    }

    public abstract void update();

    public abstract Sprite getSprite();

    public void dispose(){}

    public boolean isVisible(Camera camera){
        Vector3 position = camera.position;
        return camera.frustum.pointInFrustum(
                new Vector3(body.getPosition(), 0)
        );
    }


    public Body getBody() {
        return body;
    }

}
