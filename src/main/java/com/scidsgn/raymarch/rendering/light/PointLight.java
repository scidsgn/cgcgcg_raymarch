package com.scidsgn.raymarch.rendering.light;

import com.scidsgn.raymarch.math.Vector;
import lombok.Getter;

public class PointLight implements AbstractLight {
    @Getter
    private final Vector color;
    private final Vector position;

    public PointLight(Vector color, Vector position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public Vector getDirection(Vector position) {
        return Vector.sub(this.position, position).normalized();
    }
}
