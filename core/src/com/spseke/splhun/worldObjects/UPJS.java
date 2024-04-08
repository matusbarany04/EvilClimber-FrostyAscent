package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;
import com.spseke.splhun.groups.Layers;

import org.w3c.dom.Text;

public class UPJS extends Entity {

    Sprite sprite;
    Texture texture;

    int width = 0;
    int height = 0;
    float x = 0;
    float y = 0;


    public UPJS(float x , float y, int width, int height){
        this.width = width;
        this.x = x;
        this.y = y;

        this.height = height;
        configureLayer(Layers.BACKGROUND1);
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        sprite.setPosition(
                x ,
                y
        );
    }

    @Override
    public void setRotation(float v) {
        sprite.setRotation(v);
    }

    @Override
    public void create(World world) {
        super.create(world);
        texture = new Texture("upjs_part.jpeg");
        sprite = new Sprite(texture);



        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

         body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(0, 0); // pozor rozbije matusa
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

        float aspectRatio = (float) texture.getWidth() / texture.getHeight();
//        * aspectRatio
        sprite.setSize(50 , 50 / aspectRatio);

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
