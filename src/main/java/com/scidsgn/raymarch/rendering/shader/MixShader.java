package com.scidsgn.raymarch.rendering.shader;

import com.scidsgn.raymarch.hdri.HDRI;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.light.AbstractLight;

import java.util.List;

public class MixShader implements Shader {
    private final Shader shader1;
    private final Shader shader2;
    private final double mix;

    public MixShader(Shader shader1, Shader shader2, double mix) {
        this.shader1 = shader1;
        this.shader2 = shader2;
        this.mix = mix;
    }

    @Override
    public Vector shadeAbstract(Vector position, Vector incoming, Vector normal, List<AbstractLight> lights) {
        return Vector.lerp(
                mix,
                shader1.shadeAbstract(position, incoming, normal, lights),
                shader2.shadeAbstract(position, incoming, normal, lights)
        );
    }

    @Override
    public Vector shadePBR(Vector position, Vector incoming, Vector normal, HDRI hdri) {
        return Vector.lerp(
                mix,
                shader1.shadePBR(position, incoming, normal, hdri),
                shader2.shadePBR(position, incoming, normal, hdri)
        );
    }
}
