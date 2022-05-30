package com.scidsgn.raymarch.state;

import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.light.DirectionalLight;
import com.scidsgn.raymarch.rendering.light.LightingContext;
import com.scidsgn.raymarch.rendering.light.PointLight;

public class LightingContexts {
    public static LightingContext BASIC = new LightingContext().addAbstractLight(
            new DirectionalLight(new Vector(1, 1, 1), new Vector(0, 0, 1)),
            new DirectionalLight(new Vector(0.5, 0.5, 0.5), new Vector(-0.4, -1, -0.4).normalized()),
            new PointLight(new Vector(0.8, 0.8, 0.8), new Vector(1, 2, -1))
    );
    public static LightingContext RGB = new LightingContext().addAbstractLight(
            new DirectionalLight(new Vector(1, 1, 1), new Vector(0, -1, 0)),
            new DirectionalLight(new Vector(1, 0, 0), new Vector(1, 0, 0)),
            new DirectionalLight(new Vector(0, 0, 1), new Vector(-1, 0, 0)),
            new DirectionalLight(new Vector(0, 1, 0), new Vector(0, 1, 0))
    );
}
