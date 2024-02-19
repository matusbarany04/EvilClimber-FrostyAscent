package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Entity;
import com.spseke.splhun.MyGdxGame;

public class Ground extends Entity {


    Texture texture;
    BodyDef bodyDef;
    int x;
    int y;
    Sprite sprite;
    SpriteBatch spriteBatch;


    public Ground(int x, int y, SpriteBatch batch) {
        this.x = x;
        this.y = y;
        this.spriteBatch = batch;

        create(MyGdxGame.world);
    }

    @Override
    public void create(World world){


        texture = new Texture("upjs_ground.jpeg");
        sprite = new Sprite(texture);
        sprite.setSize(Gdx.graphics.getWidth() , texture.getHeight());
//        sprite.scale(0.3f);
        float aspectRatio = (float) texture.getWidth() / texture.getHeight();

//        sprite.setScale(
//                (float)   Gdx.graphics.getWidth() / texture.getWidth(), 1f);

        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(sprite.getX(), sprite.getY());

    }

    public void update(){
        sprite.draw(spriteBatch);
//        spriteBatch.draw(sprite, sprite.getX(), sprite.getY());
    }


    public void dispose(){

    }


    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }


    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x,y);
    }

    @Override
    public void setRotation(float v) {
        sprite.setRotation(v);
    }
}
