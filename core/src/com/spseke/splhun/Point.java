package com.spseke.splhun;

import com.badlogic.gdx.graphics.Camera;

public class Point {


    float x;
    float y;


    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float distanceFrom(Point point) {
        return (float)
                Math.sqrt(
                        Math.pow(x - point.getX(), 2) +
                                Math.pow(y - point.getY(), 2)
                );
    }

    public boolean isInCameraView(Camera camera) {
        return camera.frustum.pointInFrustum(x, y, 0);
    }


    public static Point getMiddlePoint(Point a, Point b) {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);

    }


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addX(float x) {
        this.x += x;
    }

    public void addY(float y){
        this.y += y;
    }
}
