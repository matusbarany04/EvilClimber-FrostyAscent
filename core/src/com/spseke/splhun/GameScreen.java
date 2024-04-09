package com.spseke.splhun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.spseke.splhun.groups.LayerManager;
import com.spseke.splhun.worldObjects.Ball;
import com.spseke.splhun.worldObjects.Ground;
import com.spseke.splhun.worldObjects.JumpItem;
import com.spseke.splhun.worldObjects.UPJS;

public class GameScreen implements Screen {
    public static SpriteBatch batch;

    public static World world;
    Ball ball;
    Ball ball2;

    public static Array<Fixture> bodies = new Array<>();

    Ground ground;
    Box2DDebugRenderer debugRenderer;

    Camera camera;


    RenderSystem renderSystem;


    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

    public final float[] JUMP_ITEM_POSITIONS = {-10.5f, -5.5f, 0, 5.5f, 10.5f};

	ControlPanel controlPanel;
	Matus matus;
    ArrayList<UPJS> upjs= new ArrayList<>();

    @Override
    public void show() {
        LayerManager.init();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(50, 50 * (h / w));


//        camera.position.set(camera.viewportWidth * 2f, camera.viewportHeight * 2f, 0);


        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        world = new World(new Vector2(0, -10f), true);
        renderSystem = new RenderSystem(world, camera);
        UPJS firstFloor = new UPJS(-25,-10,50,40);
        firstFloor.create(world);
        upjs.add(firstFloor);


        ground = new Ground(0,-11);
        ground.create(world);

        matus = new Matus();
        matus.create(world);

        controlPanel = new ControlPanel();
		controlPanel.onClickButtonUp(matus::stepUp);
		controlPanel.onClickButtonRight(matus::stepRight);
		controlPanel.onClickButtonLeft(matus::stepLeft);


        ball = new Ball(
                0.5f,
                -5,
                1);
        ball.create(world);

        ball2 = new Ball(
                -0,
                20,
                1);
        ball2.setDensity(1);
        ball2.create(world);

        for(int i = 0; i < JUMP_ITEM_POSITIONS.length; i++) {
            JumpItem jumpItem = new JumpItem();
            jumpItem.setPosition(JUMP_ITEM_POSITIONS[i], -7);
            jumpItem.create(world);
        }

    }

    public void updateUpjsBuilding(){

    }

    @Override
    public void render(float delta) {
        camera.update();
        camera.translate(0,0.01f,0);
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();

        renderSystem.update(batch);
        batch.end();
        controlPanel.update();

        debugRenderer.render(world, camera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

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
        world.dispose();
        ground.dispose();
        ball.dispose();
        ball2.dispose();
        matus.dispose();
    }
}
