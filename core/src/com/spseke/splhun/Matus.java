package com.spseke.splhun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
    
public class Matus extends Entity {

    SpriteBatch batch;
    Sprite sprite;
    Texture right;
    Texture left;

    Texture sideRight;
    Texture sideLeft;
    Body body;
    PolygonShape shape;

    final int speed = 1;

    boolean directionR;

    public Matus() {

    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(
                x - sprite.getWidth()/2,
                y  - sprite.getHeight()/2
        );
    }

    @Override
    public void setRotation(float v) {
        sprite.setRotation(v);
    }


    @Override
    public void create(World world) {
        right = new Texture("matus-right.png");
        left = new Texture("matus-left.png");
        sideRight = new Texture("matus-move-right.png");
        sideLeft = new Texture("matus-move-left.png");
        sprite = new Sprite(right);
        batch = new SpriteBatch();

        float aspectRatio = sprite.getWidth() / sprite.getHeight();

        sprite.setSize(1, 1 * aspectRatio );
        // Center the sprite in the top/middle of the screen
//        sprite.setOrigin(
//                 sprite.getWidth() / 2,
//                sprite.getHeight() / 2
//        );


//        sprite.setPosition(
//                Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
//                Gdx.graphics.getHeight() / 2);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        // Set our body to the same position as our sprite
        bodyDef.position.set(0, 0);

        // Create a body in the world using our definition
        body = world.createBody(bodyDef);

         shape = new PolygonShape();

        shape.setAsBox(1, 3);

        shape.dispose();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2;

        body.createFixture(fixtureDef);
        body.setUserData(this);
    }

    public void update() {

        float aspectRatio = sprite.getWidth() / sprite.getHeight();

        sprite.setSize(1 * aspectRatio, 1 );
//        sprite.setPosition(body.getPosition().x, body.getPosition().y);
//        batch.begin();
//        batch.draw(sprite, sprite.getX(), sprite.getY());
//        batch.end();
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public void stepUp() {
        body.applyLinearImpulse(0, 100, body.getPosition().x, body.getPosition().y - speed, true);
        directionR = !directionR;
        sprite.setTexture(directionR ? right : left);
    }

    public void stepRight() {
        sprite.setTexture(sideRight);
        delayedRun(() -> {
            sprite.setTexture(right);
            body.applyLinearImpulse(-100, 0, body.getPosition().x, body.getPosition().y, true);

        }, 200);
    }

    public void stepLeft() {
        sprite.setTexture(sideLeft);
        delayedRun(() -> {
            sprite.setTexture(left);
            body.applyLinearImpulse(100, 0, body.getPosition().x, body.getPosition().y, true);

        }, 200);
    }

    private void delayedRun(Runnable runnable, int millis) {
        Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        }, millis);
    }
}
