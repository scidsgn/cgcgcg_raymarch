package com.scidsgn.raymarch.hdri;

import com.scidsgn.raymarch.math.Vector;

public abstract class HDRI {
    private double tilt = 0.0;
    private double turntable = 0.0;

    public void setRotation(double tilt, double turntable) {
        this.tilt = tilt;
        this.turntable = turntable;
    }

    private Vector tiltVector(Vector normal) {
        return Vector.rotate(
                Vector.rotate(normal, new Vector(1, 0, 0), tilt),
                new Vector(0, 0, 1), turntable
        );
    }

    abstract public Vector sample(double azimuth, double inclination);
    abstract public Vector sampleDiffuse(double azimuth, double inclination);

    public Vector sample(Vector normal) {
        Vector angles = normalToSpherical(tiltVector(normal));

        return sample(angles.x(), angles.y());
    }

    public Vector sampleDiffuse(Vector normal) {
        Vector angles = normalToSpherical(tiltVector(normal));

        return sampleDiffuse(angles.x(), angles.y());
    }

    protected Vector normalToSpherical(Vector normal) {
         return new Vector(
                 Math.atan2(normal.y(), normal.x()),
                 Math.acos(normal.z()),
                 0
         );
    }

    protected Vector sphericalToNormal(double azimuth, double inclination) {
        return new Vector(
                Math.cos(azimuth) * Math.sin(inclination),
                Math.sin(azimuth) * Math.sin(inclination),
                Math.cos(inclination)
        );
    }
}
