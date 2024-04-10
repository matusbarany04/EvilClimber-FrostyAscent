package com.spseke.splhun;

import java.awt.Shape;

public class LevelConfig {

    private final String texture;
    private final int[] enabledJumpItems;
    float[][] badGuys;


    public LevelConfig(String texture, int[] enabledJumpItems, float[][] badGuys){
        this.texture = texture;
        this.badGuys = badGuys;
        this.enabledJumpItems = enabledJumpItems;
    }

    public float[][] getBadGuys() {
        return badGuys;
    }

//    TODO make this happen
    public Shape[] getBadGuysAsShapes() {
        return null;
    }



    public String getTexture() {
        return texture;
    }

    public int[] getEnabledJumpItems() {
        return enabledJumpItems;
    }
}
