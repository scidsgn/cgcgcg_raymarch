package com.scidsgn.raymarch.sdf;

import com.scidsgn.raymarch.math.Vector;

public record Sphere(Vector origin, double radius) implements SDFObject {
    @Override
    public double distanceAt(Vector point) {
        return Vector.sub(point, origin).length() - radius;
    }
}
