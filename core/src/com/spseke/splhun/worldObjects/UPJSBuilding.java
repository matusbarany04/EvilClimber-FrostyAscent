package com.spseke.splhun.worldObjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.spseke.splhun.Point;

import java.util.ArrayList;

import jdk.jfr.internal.test.WhiteBox;

public class UPJSBuilding {


    Camera camera;
    World world;

    ArrayList<UPJSFloor> upjs= new ArrayList<>();
    public UPJSBuilding(Camera camera, World world){
        this.world = world;
        this.camera = camera;
        init();
    }

    private void init(){
        makeNewFloors();
    }

    private void makeNewFloors(){
        Point pointMiddleGround;
        if(upjs.isEmpty()){
            pointMiddleGround = new Point(0,-10);
        }else {
            Point downLeft = upjs.get(upjs.size() -1).getUpLeftPosition();
            Point downRight= upjs.get(upjs.size() -1).getUpRightPosition();
            pointMiddleGround = Point.getMiddlePoint(downLeft, downRight);
        }

        // while we are in camera we add more buildings
        while (pointMiddleGround.isInCameraView(camera)){
            UPJSFloor floor =
                    new UPJSFloor(
                            pointMiddleGround.getX() -25,
                            pointMiddleGround.getY(),
                            50);

            pointMiddleGround.addY(floor.getHeight());

            upjs.add(floor);
            floor.create(world);


        }

    }

    public void update(){
        makeNewFloors();
    }

    // TODO add variables for pos
    private void addFloor(){
        UPJSFloor firstFloor =
                new UPJSFloor(-25,-10,50);
        upjs.add(firstFloor);
        firstFloor.create(world);
    }
}
