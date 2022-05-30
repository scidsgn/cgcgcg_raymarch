package com.scidsgn.raymarch;

import com.scidsgn.raymarch.gui.AppFrame;
import com.scidsgn.raymarch.hdri.HDRI;
import com.scidsgn.raymarch.hdri.HDRILoader;
import com.scidsgn.raymarch.math.Ray;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.math.VectorMap;
import com.scidsgn.raymarch.rendering.light.DirectionalLight;
import com.scidsgn.raymarch.rendering.light.LightingContext;
import com.scidsgn.raymarch.rendering.light.PointLight;
import com.scidsgn.raymarch.rendering.shader.DielectricShader;
import com.scidsgn.raymarch.rendering.shader.Shader;
import com.scidsgn.raymarch.sdf.SDFObject;
import com.scidsgn.raymarch.sdf.Sphere;
import com.scidsgn.raymarch.sdf.csg.CSGIntersect;
import com.scidsgn.raymarch.sdf.csg.CSGSubtract;
import com.scidsgn.raymarch.sdf.csg.CSGUnion;
import com.scidsgn.raymarch.state.LightingContexts;
import com.scidsgn.raymarch.state.Objects;
import com.scidsgn.raymarch.state.Shaders;
import com.scidsgn.raymarch.state.State;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        State state = new State();

        for (File hdriFile : new File("hdris").listFiles()) {
            System.out.println(String.format("Loading %s HDRI...", hdriFile.getName()));

            HDRI hdri = HDRILoader.load(hdriFile, 600, 300);
            state.addHDRI(hdri, hdriFile.getName());
        }
        System.out.println("Done!");

        state.addObject(Objects.SPHERE, "Sphere");
        state.addObject(Objects.SPHEREBITE, "Sphere with a bite");
        state.addObject(Objects.APPLE, "Sphere apple");

        state.addShader(Shaders.ROUGE, "Rouge (L)");
        state.addShader(Shaders.ECRU, "Ecru (L)");
        state.addShader(Shaders.MIRROR, "Mirror (M)");
        state.addShader(Shaders.COPPER, "Copper (M)");
        state.addShader(Shaders.ULTRADARK, "Ultradark (D)");
        state.addShader(Shaders.PLASTIQUE, "Plastique (D)");
        state.addShader(Shaders.FRESNEL, "Fresnel test");

        state.addLightingContext(LightingContexts.BASIC, "Basic");
        state.addLightingContext(LightingContexts.RGB, "RGB");

        SwingUtilities.invokeLater(() -> {
            new AppFrame(state);
        });
    }
}
