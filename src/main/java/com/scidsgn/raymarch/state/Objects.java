package com.scidsgn.raymarch.state;

import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.sdf.SDFDisplace;
import com.scidsgn.raymarch.sdf.SDFObject;
import com.scidsgn.raymarch.sdf.Sphere;
import com.scidsgn.raymarch.sdf.csg.CSGSubtract;

public class Objects {
    public static SDFObject SPHERE = new Sphere(new Vector(0, 0, 0), 2);
    public static SDFObject SPHEREBITE = new CSGSubtract(
            new Sphere(new Vector(0, 0, 0), 2),
            new Sphere(new Vector(0, 2, 0), 1)
    );
    public static SDFObject APPLE = new CSGSubtract(
            new CSGSubtract(
                    new Sphere(new Vector(0, 0, 0), 2),
                    new Sphere(new Vector(2, 1, 0), 1.8)
            ),
            new Sphere(new Vector(-2, 1, 0), 1.8)
    );
}
