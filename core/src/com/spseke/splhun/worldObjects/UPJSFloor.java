package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;
import com.spseke.splhun.Point;
import com.spseke.splhun.groups.Layers;

public class UPJSFloor extends Entity {

    public final float[] JUMP_ITEM_POSITIONS = {-10.5f, -5.5f, 0, 5.5f, 10.5f};

    Sprite sprite;
    Texture texture;

    float width = 0;
    float height = 0;
    float x = 0;
    float y = 0;


    public UPJSFloor(float x , float y, float width){
        this.x = x;
        this.y = y;
        texture = new Texture("upjs_part.jpeg");
        float aspectRatio = (float) texture.getWidth() / texture.getHeight();
        this.height = width / aspectRatio;
        this.width = width;

        configureLayer(Layers.BACKGROUND1);
    }


    private void createWindows(World world, float moveX, float moveY){
        for(int i = 0; i < JUMP_ITEM_POSITIONS.length; i++) {
            JumpItem jumpItem = new JumpItem();


            jumpItem.setPosition(JUMP_ITEM_POSITIONS[i],   getCenter().getY() -7 );
            jumpItem.create(world);
        }
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
        sprite = new Sprite(texture);


        // TODO change to camera movement + ujps pos
        createWindows(world, 0,0);


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
        sprite.setSize(width , width / aspectRatio);

        height = (float ) width / aspectRatio;

        sprite.setOrigin(
                sprite.getWidth() / 2,
                sprite.getHeight() / 2
        );

    }



    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Point getDownRightPosition() {
        return new Point(x + width,y );
    }

    public Point getDownLeftPosition() {
        return new Point(x ,y );
    }


    public Point getUpLeftPosition() {
        return new Point(x,y + height);
    }

    public Point getUpRightPosition() {
        return new Point(x + width, y + height);
    }

    public Point getCenter(){
        return  new Point(x + width/2 , y + height/2);
    }


}
