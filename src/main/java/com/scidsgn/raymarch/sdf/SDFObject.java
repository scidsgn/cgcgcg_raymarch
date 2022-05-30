package com.scidsgn.raymarch.sdf;

import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.shader.Shader;

public interface SDFObject {
    double distanceAt(Vector point);
}
