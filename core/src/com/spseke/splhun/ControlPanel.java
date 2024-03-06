package com.spseke.splhun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ControlPanel {

    Stage stage;
    Button buttonUp;
    Button buttonLeft;
    Button buttonRight;

    public ControlPanel() {
        stage = new Stage();

        // Create a button
        buttonUp = new Button(new TextureRegionDrawable(new Texture(Gdx.files.internal("arrow-up.png"))));
        buttonUp.setPosition(Gdx.graphics.getWidth() - 200, 20); // Set button position
        buttonUp.setSize(150, 150);

        buttonLeft = new Button(new TextureRegionDrawable(new Texture(Gdx.files.internal("arrow-left.png"))));
        buttonLeft.setPosition(50, 20); // Set button position
        buttonLeft.setSize(150, 150);

        buttonRight = new Button(new TextureRegionDrawable(new Texture(Gdx.files.internal("arrow-right.png"))));
        buttonRight.setPosition(200 , 20); // Set button position
        buttonRight.setSize(150, 150);
        // Add a click listener to the button

        // Add button to stage
        stage.addActor(buttonUp);
        stage.addActor(buttonLeft);
        stage.addActor(buttonRight);

        // Set the input processor to the stage
        Gdx.input.setInputProcessor(stage);
    }

    public void onClickButtonUp(Runnable runnable) {
        buttonUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Button", "Clicked");
                runnable.run();
            }
        });
    }

    public void onClickButtonLeft(Runnable runnable) {
        buttonLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Button", "Clicked");
                runnable.run();
            }
        });
    }

    public void onClickButtonRight(Runnable runnable) {
        buttonRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Button", "Clicked");
                runnable.run();
            }
        });
    }

    public void update() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
