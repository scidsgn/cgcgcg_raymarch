package com.scidsgn.raymarch;

import com.scidsgn.raymarch.math.Ray;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.sdf.SDFObject;

public class SDFTracer {
    private final SDFObject object;

    private final int maxTraces;
    private final double epsilon;

    public SDFTracer(SDFObject object, int maxTraces, double epsilon) {
        this.object = object;

        this.maxTraces = maxTraces;
        this.epsilon = epsilon;
    }

    public Vector trace(Ray ray) {
        for (int i = 0; i < maxTraces; i++) {
            double distance = object.distanceAt(ray.origin());
            if (distance <= epsilon) {
                return ray.advance(distance).origin();
            }

            ray = ray.advance(distance);
        }

        return null;
    }

    public Vector normalAt(Vector point) {
        Vector dx = new Vector(epsilon, 0, 0);
        Vector dy = new Vector(0, epsilon, 0);
        Vector dz = new Vector(0, 0, epsilon);

        return new Vector(
            object.distanceAt(Vector.add(point, dx)) - object.distanceAt(Vector.sub(point, dx)),
            object.distanceAt(Vector.add(point, dy)) - object.distanceAt(Vector.sub(point, dy)),
            object.distanceAt(Vector.add(point, dz)) - object.distanceAt(Vector.sub(point, dz))
        ).normalized();
    }
}
