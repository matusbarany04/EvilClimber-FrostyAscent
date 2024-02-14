package com.spseke.splhun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {    SpriteBatch batch;
	Sprite sprite;
	Texture img;
	World world;
	Body body;

	@Override
	public void create() {

		batch = new SpriteBatch();

		img = new Texture("badlogic.jpg");
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

		PolygonShape shape = new PolygonShape();

		shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);


		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 10f;

		Fixture fixture = body.createFixture(fixtureDef);

		// Shape is the only disposable of the lot, so get rid of it
		shape.dispose();
	}

	@Override
	public void render() {


		world.step(Gdx.graphics.getDeltaTime(), 6, 2);


		sprite.setPosition(body.getPosition().x, body.getPosition().y);

		// You know the rest...
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(sprite, sprite.getX(), sprite.getY());
		batch.end();
	}

	@Override
	public void dispose() {
		// Hey, I actually did some clean up in a code sample!
		img.dispose();
		world.dispose();
	}
}