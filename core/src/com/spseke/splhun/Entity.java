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


//    public Entity(){
//        create(MyGdxGame.world);
//    }
    protected  BodyDef bodyDef = new BodyDef();

    public abstract void setPosition(float x, float y);

    public abstract void setRotation(float v);


    public abstract void create(World world);

    public abstract void update();

    public abstract void render(SpriteBatch batch);

    public void dispose(){
    }


    public void setBodyType(BodyDef.BodyType type) {
        bodyDef.type = type;
    }


}
