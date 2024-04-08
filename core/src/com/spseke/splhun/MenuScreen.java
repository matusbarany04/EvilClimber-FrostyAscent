package com.spseke.splhun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

    private Game game;
    private Stage stage;

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        createButton("PLAY", 800, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("click test");
                game.setScreen(new GameScreen());
            }
        });

        createButton("OPTIONS", 600, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        createButton("QUIT", 400, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private void createButton(String label, int y, ClickListener clickListener) {
        Texture buttonTexture = new Texture(Gdx.files.internal("btn_skin.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));

// Create a font for the button text
        BitmapFont font = new BitmapFont();
// Create a text button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonDrawable; // Set the button background
        textButtonStyle.font = font; // Set the font
        font.getData().setScale(6f);
        textButtonStyle.fontColor = Color.WHITE; // Set the font color
        TextButton button = new TextButton(label, textButtonStyle);
        button.setPosition(Gdx.graphics.getWidth() / 2f - 250 , y);
        button.setSize(500, 150);
        button.addListener(clickListener);
        stage.addActor(button);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
