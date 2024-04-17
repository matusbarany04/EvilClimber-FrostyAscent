package com.spseke.splhun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.spseke.splhun.groups.LayerManager;
import com.spseke.splhun.worldObjects.Ball;
import com.spseke.splhun.worldObjects.Ground;
import com.spseke.splhun.worldObjects.JumpItem;
import com.spseke.splhun.worldObjects.UPJSBuilding;
import com.spseke.splhun.worldObjects.UPJSFloor;

public class GameScreen implements Screen {
    public static SpriteBatch batch;

    public static World world;
    Ball ball;
    Ball ball2;

    int gameScore;

    public static Array<Fixture> bodies = new Array<>();

    Ground ground;
    Box2DDebugRenderer debugRenderer;

    public static Camera camera;


    public static boolean touched = false;
    RenderSystem renderSystem;
    UPJSBuilding upjsBuilding;

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

	ControlPanel controlPanel;
	Matus matus;


    @Override
    public void show() {
        LayerManager.init();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(50, 50 * (h / w));

//      camera.position.set(camera.viewportWidth * 2f, camera.viewportHeight * 2f, 0);


        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        world = new World(new Vector2(0, -10f), true);
        renderSystem = new RenderSystem(world, camera);

        ground = new Ground(0,-11);
        ground.create(world);

        matus = new Matus();
        matus.create(world);

        controlPanel = new ControlPanel();
        controlPanel.onClickButtonUp(matus::stepUp);
        controlPanel.onClickButtonRight(matus::stepRight);
		controlPanel.onClickButtonLeft(matus::stepLeft);


        ball = new Ball(
                2f,
                -5,
                1);
        ball.create(world);

        ball2 = new Ball(
                -3,
                20,
                1);
        ball2.setDensity(1);
        ball2.create(world);

        upjsBuilding = new UPJSBuilding(camera, world, matus);

        createWalls();
    }

    private void createWalls() {
        JumpItem leftWall = new JumpItem(0.1f, camera.viewportHeight);
        leftWall.setPosition(-camera.viewportWidth / 2, 0);
        JumpItem rightWall = new JumpItem(0.1f, camera.viewportHeight);
        rightWall.setPosition(camera.viewportWidth / 2 + 0.1f, 0);
        leftWall.create(world);
        rightWall.create(world);
    }

    @Override
    public void render(float delta) {
        camera.update();

        // this makes the world go down
        camera.translate(0,0.02f,0);
        batch.setProjectionMatrix(camera.combined);


        batch.begin();

        renderSystem.update(batch);

        if (GameScreen.touched || matus.getSprite().getY() > -50 && matus.getSprite().getY() + matus.getSprite().getHeight() < camera.position.y - camera.viewportHeight / 2) {

            gameOver();
        }

        batch.end();
        controlPanel.update();

        debugRenderer.render(world, camera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        upjsBuilding.update();
    }

    public void gameOver() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.WHITE; // Set the drawable you want to use
        BitmapFont font = new BitmapFont();
        font.getData().setScale(0.5f);
        style.font = font;
        Label label = new Label("GAME OVER", style);
        label.setPosition(-label.getWidth() / 2, camera.position.y - label.getHeight() / 2);
        label.draw(batch, 1);
        if (gameScore == 0) gameScore = (int) camera.position.y;
        font.getData().setScale(0.3f);
        Label score = new Label(String.format("Skóre: %d", gameScore), style);
        score.setPosition(-label.getWidth() / 2, camera.position.y + 3);
        score.draw(batch, 1);
        handleTouch();
    }

    private void handleTouch() {

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                touched = false;
                Gdx.input.setInputProcessor(null);
                dispose();
                camera.position.y = 0;
                gameScore = 0;
                show();
                return true;
            }

        });
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        ground.dispose();
        ball.dispose();
        ball2.dispose();
        matus.dispose();
    }
}
