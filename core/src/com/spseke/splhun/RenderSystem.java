package com.spseke.splhun;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class RenderSystem {

    private ArrayList<Entity> entities;

    public RenderSystem(List<Entity> entities) {
        this.entities = (ArrayList<Entity>) entities;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public void update(SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
        }
    }
}