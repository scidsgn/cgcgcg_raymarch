package com.scidsgn.raymarch.rendering.shader;

import com.scidsgn.raymarch.hdri.HDRI;
import com.scidsgn.raymarch.math.Ray;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.light.AbstractLight;

import java.util.List;

public class GlossyShader implements Shader {
    private final Vector color;

    public GlossyShader(Vector color) {
        this.color = color;
    }

    @Override
    public Vector shadeAbstract(Vector position, Vector incoming, Vector normal, List<AbstractLight> lights) {
        Vector lightColor = new Vector(0, 0, 0);

        for (AbstractLight light : lights) {
            Vector direction = light.getDirection(position);
            Vector reflected = Vector.reflect(direction.scale(-1), normal);
            double intensity = Math.max(
                    0, Math.pow(Vector.dot(reflected, incoming), 25)
            );

            lightColor = Vector.add(lightColor, light.getColor().scale(intensity));
        }

        return Vector.multiply(lightColor, color);
    }

    @Override
    public Vector shadePBR(Vector position, Vector incoming, Vector normal, HDRI hdri) {
        Vector reflected = Vector.reflect(incoming.scale(-1), normal);

        return Vector.multiply(hdri.sample(reflected), color);
    }
}
