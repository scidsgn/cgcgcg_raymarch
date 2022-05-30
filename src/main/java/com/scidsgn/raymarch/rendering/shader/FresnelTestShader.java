package com.scidsgn.raymarch.rendering.shader;

import com.scidsgn.raymarch.hdri.HDRI;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.light.AbstractLight;

import java.util.List;

public class FresnelTestShader implements Shader {
    private double fresnelCoefficient(Vector incoming, Vector normal) {
        double ior = 1.45;
        double angle = Math.acos(Vector.dot(incoming, normal));
        double angleRefl = Math.asin(Math.sin(angle) / ior);

        double Rs = Math.pow(
                (Math.cos(angle) - ior * Math.cos(angleRefl)) / (Math.cos(angle) + ior * Math.cos(angleRefl)),
                2
        );
        double Rp = Math.pow(
                (ior * Math.cos(angle) - Math.cos(angleRefl)) / (ior * Math.cos(angle) + Math.cos(angleRefl)),
                2
        );

        return (Rs + Rp) / 2.0;
    }

    @Override
    public Vector shadeAbstract(Vector position, Vector incoming, Vector normal, List<AbstractLight> lights) {
        return new Vector(1, 1, 1).scale(fresnelCoefficient(incoming, normal));
    }

    @Override
    public Vector shadePBR(Vector position, Vector incoming, Vector normal, HDRI hdri) {
        return new Vector(1, 1, 1).scale(fresnelCoefficient(incoming, normal));
    }
}
