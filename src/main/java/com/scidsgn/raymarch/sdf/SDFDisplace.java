package com.scidsgn.raymarch.sdf;

import com.scidsgn.raymarch.math.Vector;

import java.util.function.Function;

public class SDFDisplace implements SDFObject {
    private final SDFObject object;
    private final Function<Vector, Double> displacer;

    public SDFDisplace(SDFObject object, Function<Vector, Double> displacer) {
        this.object = object;
        this.displacer = displacer;
    }

    @Override
    public double distanceAt(Vector point) {
        return object.distanceAt(point) + displacer.apply(point);
    }
}
