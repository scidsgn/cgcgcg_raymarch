package com.scidsgn.raymarch.rendering.light;

import com.scidsgn.raymarch.math.Vector;
import lombok.Getter;

public class DirectionalLight implements AbstractLight {
    @Getter
    private final Vector color;
    private final Vector direction;

    public DirectionalLight(Vector color, Vector direction) {
        this.color = color;
        this.direction = direction;
    }

    @Override
    public Vector getDirection(Vector position) {
        return direction.normalized();
    }
}
