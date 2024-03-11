package com.spseke.splhun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.spseke.splhun.worldObjects.Ball;
import com.spseke.splhun.worldObjects.Ground;
import com.spseke.splhun.worldObjects.UPJS;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
    public static SpriteBatch batch;

    public static World world;
    Ball ball;
    Ball ball2;

    public static Array<Fixture> bodies = new Array<>();

    Ground ground;
    Box2DDebugRenderer debugRenderer;

    Camera camera;

    Sprite mapSprite;

    RenderSystem renderSystem;


    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 100;

	ControlPanel controlPanel;
	Matus matus;
    UPJS upjs;

    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(50, 50 * (h / w));

//        camera.position.set(camera.viewportWidth * 2f, camera.viewportHeight * 2f, 0);


        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();

        world = new World(new Vector2(0, -10f), true);
        renderSystem = new RenderSystem(world, camera);



        matus = new Matus(world);
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

        matus.create(world);


        upjs = new UPJS();
        upjs.create(world);

        ground = new Ground();
        ground.create(world);

        mapSprite = new Sprite(new Texture(Gdx.files.internal("upjs.jpeg")));
        mapSprite.setScale(1,1f);
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

//        ground = new Ground(0, Gdx.graphics.getHeight() / 5);
//        ground.create(world);
    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
		matus.update();
		controlPanel.update();

        renderSystem.update(batch);

//        mapSprite.draw(batch);
        batch.end();

        debugRenderer.render(world, camera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }


    private void createObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);


        // add it to the world
        Body bodyd = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        CircleShape shape = new CircleShape();
        shape.setRadius(1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyd.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }




    @Override
    public void dispose() {
        world.dispose();
        ground.dispose();
        ball.dispose();
        ball2.dispose();
    }
}
