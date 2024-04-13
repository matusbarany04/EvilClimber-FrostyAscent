package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.DeathItem;
import com.spseke.splhun.Entity;
import com.spseke.splhun.LevelConfig;
import com.spseke.splhun.Matus;
import com.spseke.splhun.Point;
import com.spseke.splhun.groups.Layers;

import java.util.ArrayList;
import java.util.Random;

import sun.jvm.hotspot.runtime.DeadlockDetector;

public class UPJSFloor extends Entity {

    public final Point[] JUMP_ITEM_POSITIONS = {
            new Point(-10.5f, -4.5f), // Middle abs left          0
            new Point(-5.5f, -4.5f),  // Middle little left       1
            new Point(0f, -4.5f),     // Middle middle            2
            new Point(5.5f, -4.5f),   // Middle little right      3
            new Point(10.5f, -4.5f),  // Middle abs right         4
            new Point(19.2f, -4.5f),  // Right side down          5
            new Point(-19.2f, -4.5f), // Left side down           6
            new Point(-19.2f, 1f),  // Left side up             7
            new Point(19.2f, 1f),   // Right side up            8
    };

    public static final LevelConfig[] LEVELS = {
            new LevelConfig("SidesFire", new int[]{0,1,2,3,4}, new float[][]{
                    {-19.2f, -4.5f, 1.2f, 2},
                    {-19.2f, 3f, 1.2f, 2},
                    {19.2f, -4.5f, 1.2f, 2},
                    {19.2f, 3f, 1.2f, 2},
            }),
            new LevelConfig("LeftBadTrump", new int[]{8,3,4,5,7}, new float[][]{
                    {-19.2f, -4.5f, 1.2f, 2},
                    {-4.6f, -5f, 6f, 1},
                    {-15f, -6f, 4f, 2},
            }),
            new LevelConfig("MiddleBadFico", new int[]{5,6,7,8,1} , new float[][]{
                    {-10.6f, -2.7f, 1.2f, 2},
                    {4.6f, -5f, 6f, 1},
            }),
            new LevelConfig("MiddleBadFIre", new int[]{5,6,7,8,0,2} , new float[][]{
                    {-5.2f, -3f, 1.2f, 2},
                    {+5.2f, -3f, 1.2f, 2},
            }),
            new LevelConfig("MiddleBrokenWindows", new int[]{0,1,2,3,4} , new float[][]{
                    {-5.2f, -3f, 1.2f, 2},
                    {+5.2f, -3f, 1.2f, 2},
            })
    };

    Sprite sprite;
    Texture texture;

    float width = 0;
    float height = 0;
    float x = 0;
    float y = 0;

    ArrayList<JumpItem> jumpItems;

    private static int lastIndex = -1;
    private static final Random random = new Random();
    private static LevelConfig getRandomConfig(){
        int newTexture = UPJSFloor.lastIndex;
        while(newTexture == lastIndex){
            newTexture = random.nextInt(LEVELS.length);
        }
        lastIndex = newTexture;
        return LEVELS[newTexture];
    }

    Matus matus;

    LevelConfig currentConfig;

    public UPJSFloor(float x , float y, float width, Matus matus){
        this.x = x;
        this.y = y;
        this.matus = matus;
        currentConfig = getRandomConfig();
        texture = new Texture(currentConfig.getTexture() + ".png"); //-pixelicious
        float aspectRatio = (float) texture.getWidth() / texture.getHeight();
        this.height = width / aspectRatio;
        this.width = width;

        jumpItems = new ArrayList<>();

        configureLayer(Layers.BACKGROUND1);
    }


    private void createWindows(World world, float moveX, float moveY){
        int[] enabledJumpItems =  currentConfig.getEnabledJumpItems();
        for(int i = 0; i < enabledJumpItems.length; i++) {
            JumpItem jumpItem = new JumpItem();

            jumpItem.setPosition(
                    JUMP_ITEM_POSITIONS[enabledJumpItems[i]].getX(),
                    getCenter().getY() + JUMP_ITEM_POSITIONS[enabledJumpItems[i]].getY()
            );

            jumpItems.add(jumpItem);
            jumpItem.create(world);
        }
    }

    private void createTraps(World world){
        float[][] badGuys =  currentConfig.getBadGuys();
        for(int i = 0; i < badGuys.length; i++) {
            DeathItem deathItem = new DeathItem(
                    badGuys[i][0],
                    badGuys[i][1],
                    badGuys[i][2],
                    badGuys[i][3]
            );

            deathItem.setPosition(
                    badGuys[i][0],
                    getCenter().getY() + badGuys[i][1]
            );

            deathItem.create(world);
        }
    }

    private void createWalls(World world) {
        JumpItem leftWall = new JumpItem(0.1f, height);
        leftWall.setPosition(-width / 2 - 0.1f, y);
        JumpItem rightWall = new JumpItem(0.1f, height);
        rightWall.setPosition(width / 2 + 0.2f, y);
        leftWall.create(world);
        rightWall.create(world);
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
        createWalls(world);
        createTraps(world);

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
        sprite.setSize(width, width / aspectRatio);

        height = (float ) width / aspectRatio;

        sprite.setOrigin(
                sprite.getWidth() / 2,
                sprite.getHeight() / 2
        );

        for (JumpItem item: jumpItems) {
            item.setEnabled(item.getUpLeftPosition().getY() - 0.5f <= matus.getDownLeftPosition().getY());
        }

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
