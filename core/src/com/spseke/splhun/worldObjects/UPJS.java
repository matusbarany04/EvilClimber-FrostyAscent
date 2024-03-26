package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;

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
        setZIndex(-1);
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
        texture = new Texture("upjs_part.jpeg");
        sprite = new Sprite(texture);



        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        Body bodys = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
        fixtureDef.restitution = 1f;

        bodys.createFixture(fixtureDef);

        shape.dispose();

        bodys.setUserData(this);
    }

    @Override
    public void update() {
        float aspectRatio = (float) texture.getWidth() / texture.getHeight();

        sprite.setSize(1000 * aspectRatio, 1000 );

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
