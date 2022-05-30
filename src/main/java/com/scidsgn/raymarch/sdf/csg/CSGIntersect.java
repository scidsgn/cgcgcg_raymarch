package com.scidsgn.raymarch.sdf.csg;

import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.sdf.SDFObject;

public class CSGIntersect extends CSGObject {
    public CSGIntersect(SDFObject object1, SDFObject object2) {
        super(object1, object2);
    }

    @Override
    public double distanceAt(Vector point) {
        return Math.max(
                object1.distanceAt(point),
                object2.distanceAt(point)
        );
    }
}
