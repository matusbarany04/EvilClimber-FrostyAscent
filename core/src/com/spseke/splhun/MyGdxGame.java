package com.spseke.splhun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
    public static SpriteBatch batch;

    Sprite sprite;
    Texture img;
    public static World world;
    public static Body body;
    Ball ball;
    Ball ball2;

    // Create an array to be filled with the bodies
// (better don't create a new one every time though)
    public static Array<Fixture> bodies = new Array<>();

    Ground ground;
    Box2DDebugRenderer debugRenderer;

    Camera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();


        world = new World(new Vector2(0, -10f), true);


        ball = new Ball(
                Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 4,
                50);
        ball.create(world);
        ball2 = new Ball(
                Gdx.graphics.getWidth() / 2,
                20,
                100);
        ball2.setDensity(500);
        ball2.create(world);


        // First we create a body definition
        BodyDef bodyDef = new BodyDef();
// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
// Set our body's starting position in the world
        bodyDef.position.set(5, 10);

// Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(6f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();


//
//        ground = new Ground(0, Gdx.graphics.getHeight() / 5);
//        ground.create(world);
    }

    @Override
    public void render() {

        world.getFixtures(bodies);


//        sprite.setPosition(body.getPosition().x, body.getPosition().y);

        // TOTO neviem či  teraz treba
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();


//        batch.draw(sprite, sprite.getX(), sprite.getY());
//        ball.update();
//        ground.update();

        //az po tadial
        for (Fixture b : bodies) {
            // Get the body's user data - in this example, our user
            // data is an instance of the Entity class
            Entity e = (Entity) b.getUserData();

            if (e != null) {
                // Update the entities/sprites position and angle
                e.setPosition(b.getBody().getPosition().x, b.getBody().getPosition().y);
                // We need to convert our angle from radians to degrees
                e.setRotation(MathUtils.radiansToDegrees * b.getBody().getAngle());
                e.update();

            }
        }
        batch.end();
        debugRenderer.render(world, camera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    @Override
    public void dispose() {
        img.dispose();
        world.dispose();
        ground.dispose();
        ball.dispose();
    }
}
