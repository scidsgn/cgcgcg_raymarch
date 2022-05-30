package com.scidsgn.raymarch.rendering.light;

import com.scidsgn.raymarch.math.Vector;

public interface AbstractLight {
    Vector getColor();
    Vector getDirection(Vector position);
}
