package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;

public class Ball extends Entity {

    int x;
    int y;
    int radius;

    public Ball(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    @Override
    public void setPosition(float x, float y) {

    }

    @Override
    public void setRotation(float v) {

    }

    @Override
    public void create(World world) {

    }


    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
