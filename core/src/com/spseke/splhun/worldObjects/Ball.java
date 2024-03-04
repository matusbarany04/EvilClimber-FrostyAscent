package com.spseke.splhun.worldObjects;

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

    int x;
    int y;
    int radius;

    Sprite sprite;
    Texture img;

    float density  = 1f;

    CircleShape circle;

    public Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x,y);
    }

    @Override
    public void setRotation(float v) {
        sprite.setRotation(v);
    }

    @Override
    public void create(World world) {
        img = new Texture("python.png");
        sprite = new Sprite(img);

        sprite.setScale(
                 (float) radius / img.getWidth() ,
                 (float) radius /  img.getHeight()
        );

        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
        // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // Set our body's starting position in the world
        bodyDef.position.set(x, y);

        // Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

        // Create a circle shape and set its radius to 6
        circle = new CircleShape();
        circle.setRadius(radius);

        // Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = density;

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

        body.createFixture(fixtureDef);

        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.

        fixture.setUserData(this);

        circle.dispose();
    }


    @Override
    public void update() {

        sprite.draw(MyGdxGame.batch);

    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(MyGdxGame.batch);
    }

    @Override
    public void dispose() {
        circle.dispose();
    }
}
