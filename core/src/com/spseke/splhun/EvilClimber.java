package com.spseke.splhun;

import com.badlogic.gdx.Game;

public class EvilClimber extends Game {
    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
