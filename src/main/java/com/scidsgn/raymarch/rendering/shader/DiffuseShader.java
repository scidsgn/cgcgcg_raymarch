package com.scidsgn.raymarch.rendering.shader;

import com.scidsgn.raymarch.hdri.HDRI;
import com.scidsgn.raymarch.math.Ray;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.light.AbstractLight;

import java.util.List;

public class DiffuseShader implements Shader {
    private final Vector color;

    public DiffuseShader(Vector color) {
        this.color = color;
    }

    @Override
    public Vector shadeAbstract(Vector position, Vector incoming, Vector normal, List<AbstractLight> lights) {
        Vector lightColor = new Vector(0, 0, 0);

        for (AbstractLight light : lights) {
            Vector direction = light.getDirection(position);
            double intensity = Math.max(0, Vector.dot(normal, direction));

            lightColor = Vector.add(lightColor, light.getColor().scale(intensity));
        }

        return Vector.multiply(lightColor, color);
    }

    @Override
    public Vector shadePBR(Vector position, Vector incoming, Vector normal, HDRI hdri) {
        return Vector.multiply(hdri.sampleDiffuse(normal), color);
    }
}
