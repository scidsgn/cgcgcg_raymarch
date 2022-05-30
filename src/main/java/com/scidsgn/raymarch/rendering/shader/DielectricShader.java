package com.scidsgn.raymarch.rendering.shader;

import com.scidsgn.raymarch.math.Vector;

public class DielectricShader extends FresnelMixShader {
    public DielectricShader(Vector color) {
        super(
                new DiffuseShader(color),
                new GlossyShader(new Vector(1, 1, 1))
        );
    }
}
