package com.scidsgn.raymarch.math;

public record Ray(Vector origin, Vector direction) {
    public Ray advance(double distance) {
        return new Ray(
                Vector.add(origin, direction.scale(distance)),
                direction
        );
    }
}
