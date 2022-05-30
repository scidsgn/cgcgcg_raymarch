package com.scidsgn.raymarch.sdf.csg;

import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.sdf.SDFObject;

public class CSGUnion extends CSGObject {
    public CSGUnion(SDFObject object1, SDFObject object2) {
        super(object1, object2);
    }

    @Override
    public double distanceAt(Vector point) {
        return Math.min(
                object1.distanceAt(point),
                object2.distanceAt(point)
        );
    }
}
