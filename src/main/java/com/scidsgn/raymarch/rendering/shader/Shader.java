package com.scidsgn.raymarch.rendering.shader;

import com.scidsgn.raymarch.hdri.HDRI;
import com.scidsgn.raymarch.math.Ray;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.rendering.light.AbstractLight;

import java.util.List;

public interface Shader {
    Vector shadeAbstract(Vector position, Vector incoming, Vector normal, List<AbstractLight> lights);
    Vector shadePBR(Vector position, Vector incoming, Vector normal, HDRI hdri);
}
