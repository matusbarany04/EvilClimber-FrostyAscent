package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;
import com.spseke.splhun.LevelConfig;
import com.spseke.splhun.Point;
import com.spseke.splhun.groups.Layers;

import java.util.Random;

public class UPJSFloor extends Entity {

    public final Point[] JUMP_ITEM_POSITIONS = {
            new Point(-10.5f, -7f), // Middle abs left          0
            new Point(-5.5f, -7f),  // Middle little left       1
            new Point(0f, -7f),     // Middle middle            2
            new Point(5.5f, -7f),   // Middle little right      3
            new Point(10.5f, -7f),  // Middle abs right         4
            new Point(19.2f, -7f),  // Right side down          5
            new Point(-19.2f, -7f), // Left side down           6
            new Point(-19.2f, 1f),  // Left side up             7
            new Point(19.2f, 1f),   // Right side up            8
    };

    public static final LevelConfig[] LEVELS = {
            new LevelConfig("LeftBadTrump.png", new int[]{8,3,4,5,7}),
            new LevelConfig("SidesFIre.png", new int[]{0,1,2,3,4}),
            new LevelConfig("MiddleBadFico.png", new int[]{5,6,7,8,1}),
            new LevelConfig("MiddleBadFIre.png", new int[]{5,6,7,8,0,2})
    };

    Sprite sprite;
    Texture texture;

    float width = 0;
    float height = 0;
    float x = 0;
    float y = 0;

    static  final Random random = new Random();
    private static LevelConfig getRandomConfig(){
        return LEVELS[random.nextInt(LEVELS.length -1)];
    }


    LevelConfig currentConfig;

    public UPJSFloor(float x , float y, float width){
        this.x = x;
        this.y = y;
        currentConfig = getRandomConfig();
        texture = new Texture("upjs_part.jpeg");
        float aspectRatio = (float) texture.getWidth() / texture.getHeight();
        this.height = width / aspectRatio;
        this.width = width;

        configureLayer(Layers.BACKGROUND1);
    }


    private void createWindows(World world, float moveX, float moveY){
        for(int i = 0; i < JUMP_ITEM_POSITIONS.length; i++) {
            JumpItem jumpItem = new JumpItem();

            jumpItem.setPosition(JUMP_ITEM_POSITIONS[i].getX(),   getCenter().getY() + JUMP_ITEM_POSITIONS[i].getY() );
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
