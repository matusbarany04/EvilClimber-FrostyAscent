package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;

public class JumpItem extends Entity {


    float x;
    float y;

    Sprite sprite;
    Texture img;

    @Override
    public void setPosition(float x, float y) {
        bodyDef.position.set(x, y);
        this.x = x;
        this.y = y;
        if(sprite != null){
            sprite.setPosition(
                    x ,
                    y
            );
        }

    }

    @Override
    public void setRotation(float v) {
        sprite.setRotation(v);
    }

    @Override
    public void create(World world) {
        img = new Texture("upjs_part.jpeg");
        sprite = new Sprite(img);
        sprite.setSize(500, 500);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2, 0.3f);
        shape.dispose();
        Body body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        body.setUserData(this);
    }

    @Override
    public void update() {

        float aspectRatio = (float) img.getWidth()
                / img.getHeight();

        sprite.setSize(img.getWidth() ,
                img.getWidth() / aspectRatio);


        sprite.setOrigin(
                sprite.getWidth() / 2,
                sprite.getHeight() / 2
        );
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
