package com.spseke.splhun.groups;

import com.spseke.splhun.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LayerManager {

    private static final ArrayList<ArrayList<Entity>> layerObjects = new ArrayList<>();
    private static final HashMap<Entity, Layers> allObjects = new HashMap<>();

    public static Layers[] getAllLayers(){
        return Layers.values();
    }

    public static void init(){
        for (Layers _ : getAllLayers()) {
            layerObjects.add(new ArrayList<>());
        }
    }


    // TODO check if entity exists already in another layer
    public static void registerEntity(Entity entity, Layers layer){
        if(allObjects.containsKey(entity)){
            // remove that entry
            removeEntity(entity);
        }
        layerObjects.get(layer.ordinal()).add(entity);
        allObjects.put(entity, layer);
    }

    public static void removeEntity(Entity entity){
        Layers layers = allObjects.get(entity);
        if(layers != null){
            layerObjects
                    .get(layers.ordinal())
                    .remove(entity);

            allObjects.remove(entity);
        }

    }

    public static ArrayList<ArrayList<Entity>> getLayers() {
        return layerObjects;
    }

    public static Set<Entity> getAllEntities() {
        return allObjects.keySet();
    }

    //TODO handle removing entities


}
