package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;
import com.spseke.splhun.Point;

public class JumpItem extends Entity {


    float x;
    float y;

    float width;
    float height;

    Sprite sprite;
    Texture img;

    boolean enabled = true;

    public static final float DEFAULT_WIDTH = 2;
    public static final float DEFAULT_HEIGHT = 0.3f;

    public JumpItem() {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }

    public JumpItem(float width, float height) {
        this.height = height;
        this.width = width;
    }

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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        body.setActive(enabled);
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
        shape.setAsBox(width, height);
        shape.dispose();
        body = world.createBody(bodyDef);
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


    public Point getPosition(){
        return new Point(x,y);
    }


    public Point getDownRightPosition() {
        return new Point(x + width,y );
    }

    public Point getDownLeftPosition() {
        return new Point(x,y );
    }


    public Point getUpLeftPosition() {
        return new Point(x - width/2,y + height/2);
    }

    public Point getUpRightPosition() {
        return new Point(x + width, y + height);
    }


    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
