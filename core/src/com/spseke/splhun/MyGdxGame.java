package com.spseke.splhun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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

public class MyGdxGame extends ApplicationAdapter {    SpriteBatch batch;
	Sprite sprite;
	Texture img;
	public static World world;
	public static Body body;
	Ball ball;

	// Create an array to be filled with the bodies
// (better don't create a new one every time though)
	public static Array<Body> bodies = new Array<Body>();

	Ground ground;

	@Override
	public void create() {
		batch = new SpriteBatch();

		ground = new Ground(0, Gdx.graphics.getHeight()/2, batch);
		ball = new Ball(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2, 6);


		img = new Texture("matus.gif");
		sprite = new Sprite(img);


		// Center the sprite in the top/middle of the screen
		sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);


		world = new World(new Vector2(0, -98f), true);


		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		// Set our body to the same position as our sprite
		bodyDef.position.set(sprite.getX(), sprite.getY());

		// Create a body in the world using our definition
		body = world.createBody(bodyDef);

	}

	@Override
	public void render() {
		world.getBodies(bodies);


		sprite.setPosition(body.getPosition().x, body.getPosition().y);

		// TOTO neviem či nážžľm teraz treba
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		ground.update();
		batch.draw(sprite, sprite.getX(), sprite.getY());
		ball.update();

		//az po tadial
		for (Body b : bodies) {
			// Get the body's user data - in this example, our user
			// data is an instance of the Entity class
			Entity e = (Entity) b.getUserData();

			if (e != null) {
				// Update the entities/sprites position and angle
				e.setPosition(b.getPosition().x, b.getPosition().y);
				// We need to convert our angle from radians to degrees
				e.setRotation(MathUtils.radiansToDegrees * b.getAngle());
			}
		}
		batch.end();
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
	}

	@Override
	public void dispose() {
		img.dispose();
		world.dispose();
		ground.dispose();
	}
}
