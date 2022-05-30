package com.scidsgn.raymarch.rendering.shader;

import com.scidsgn.raymarch.hdri.HDRI;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.light.AbstractLight;

import java.util.List;

public class FresnelMixShader implements Shader {
    private final Shader shader1;
    private final Shader shader2;

    public FresnelMixShader(Shader shader1, Shader shader2) {
        this.shader1 = shader1;
        this.shader2 = shader2;
    }

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
        return Vector.lerp(
                fresnelCoefficient(incoming, normal),
                shader1.shadeAbstract(position, incoming, normal, lights),
                shader2.shadeAbstract(position, incoming, normal, lights)
        );
    }

    @Override
    public Vector shadePBR(Vector position, Vector incoming, Vector normal, HDRI hdri) {
        return Vector.lerp(
                fresnelCoefficient(incoming, normal),
                shader1.shadePBR(position, incoming, normal, hdri),
                shader2.shadePBR(position, incoming, normal, hdri)
        );
    }
}
