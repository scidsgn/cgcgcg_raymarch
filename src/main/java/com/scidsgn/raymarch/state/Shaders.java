package com.scidsgn.raymarch.state;

import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.shader.*;

public class Shaders {
    public static Shader ROUGE = new DiffuseShader(new Vector(0.8, 0.365, 0.6));
    public static Shader ECRU = new DiffuseShader(new Vector(0.78, 0.6, 0.3));

    public static Shader MIRROR = new GlossyShader(new Vector(1, 1, 1));
    public static Shader COPPER = new GlossyShader(new Vector(1, 0.41, 0.11));

    public static Shader ULTRADARK = new DielectricShader(new Vector(0.02, 0.02, 0.02));
    public static Shader PLASTIQUE = new DielectricShader(new Vector(0, 0.25, 0.95));

    public static Shader FRESNEL = new FresnelTestShader();
}
