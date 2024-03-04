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
    BodyDef bodyDef;
    int x;
    int y;
    Sprite sprite;

    PolygonShape rectangle;


    public Ground(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void create(World world) {


        texture = new Texture("upjs_ground.jpeg");
        sprite = new Sprite(texture);

        sprite.setSize(Gdx.graphics.getWidth(), texture.getHeight());


        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);

        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(x, y);


        // Create a circle shape and set its radius to 6
        rectangle = new PolygonShape();

        rectangle.setAsBox(Gdx.graphics.getWidth(), texture.getHeight());


        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rectangle;
        fixtureDef.density = 1f;

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

        body.createFixture(fixtureDef);



        fixture.setUserData(this);


        rectangle.dispose();
    }

    public void update() {
        sprite.draw(MyGdxGame.batch);
//        spriteBatch.draw(sprite, sprite.getX(), sprite.getY());
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(MyGdxGame.batch);
    }


    public void dispose() {
        texture.dispose();
    }


    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }


    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void setRotation(float v) {
        sprite.setRotation(v);
    }
}
