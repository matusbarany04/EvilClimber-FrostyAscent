package com.spseke.splhun;

public class LevelConfig {

    private final String texture;
    private final int[] enabledJumpItems;

    public LevelConfig(String texture, int[] enabledJumpItems){
        this.texture = texture;
        this.enabledJumpItems = enabledJumpItems;
    }

    public String getTexture() {
        return texture;
    }

    public int[] getEnabledJumpItems() {
        return enabledJumpItems;
    }
}
