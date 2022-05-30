package com.scidsgn.raymarch.sdf.csg;

import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.sdf.SDFObject;

public abstract class CSGObject implements SDFObject {
    protected final SDFObject object1;
    protected final SDFObject object2;

    public CSGObject(SDFObject object1, SDFObject object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    abstract public double distanceAt(Vector point);
}
