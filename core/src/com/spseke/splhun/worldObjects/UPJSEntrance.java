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

public class UPJSEntrance  extends Entity {

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



        Sprite sprite;
        Texture texture;

        float width = 0;
        float height = 0;
        float x = 0;
        float y = 0;

        ArrayList<JumpItem> jumpItems;



        Matus matus;

        LevelConfig currentConfig;

        public UPJSEntrance(float x , float y, float width, Matus matus){
            this.x = x;
            this.y = y;
            this.matus = matus;
            currentConfig = new LevelConfig("upjs_entrance.jpeg", new int[]{0,1,2,3,4}, new float[][]{});

                    texture = new Texture(currentConfig.getTexture()); //-pixelicious
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


            bodyDef = new BodyDef();
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

