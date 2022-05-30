package com.scidsgn.raymarch.state;

import com.scidsgn.raymarch.SDFTracer;
import com.scidsgn.raymarch.gui.RenderPanel;
import com.scidsgn.raymarch.hdri.HDRI;
import com.scidsgn.raymarch.math.Ray;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.math.VectorMap;
import com.scidsgn.raymarch.rendering.light.LightingContext;
import com.scidsgn.raymarch.rendering.shader.Shader;
import com.scidsgn.raymarch.sdf.SDFObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class State {
    private final List<HDRI> hdris;
    private final List<String> hdriNames;

    @Setter
    private int currentHDRI = 0;

    private final List<SDFObject> objects;
    private final List<String> objectNames;

    @Setter
    private int currentObject = 0;

    private final List<Shader> shaders;
    private final List<String> shaderNames;

    @Setter
    private int currentShader = 0;

    private final List<LightingContext> lightingContexts;
    private final List<String> lightingContextNames;

    @Setter
    private int currentLightingContext = 0;

    @Getter @Setter
    private double cameraAngle = 0.;

    public State() {
        this.hdris = new ArrayList<>();
        this.hdriNames = new ArrayList<>();

        this.objects = new ArrayList<>();
        this.objectNames = new ArrayList<>();

        this.shaders = new ArrayList<>();
        this.shaderNames = new ArrayList<>();

        this.lightingContexts = new ArrayList<>();
        this.lightingContextNames = new ArrayList<>();
    }

    public HDRI getCurrentHDRI() {
        return hdris.get(currentHDRI);
    }

    public String[] getHDRINames() {
        return hdriNames.toArray(new String[0]);
    }

    public void addHDRI(HDRI hdri, String name) {
        hdris.add(hdri);
        hdriNames.add(name);
    }

    public SDFObject getCurrentObject() {
        return objects.get(currentObject);
    }

    public String[] getObjectNames() {
        return objectNames.toArray(new String[0]);
    }

    public void addObject(SDFObject object, String name) {
        objects.add(object);
        objectNames.add(name);
    }

    public Shader getCurrentShader() {
        return shaders.get(currentShader);
    }

    public String[] getShaderNames() {
        return shaderNames.toArray(new String[0]);
    }

    public void addShader(Shader shader, String name) {
        shaders.add(shader);
        shaderNames.add(name);
    }

    public LightingContext getCurrentLightingContext() {
        return lightingContexts.get(currentLightingContext);
    }

    public String[] getLightingContextNames() {
        return lightingContextNames.toArray(new String[0]);
    }

    public void addLightingContext(LightingContext lightingContext, String name) {
        lightingContexts.add(lightingContext);
        lightingContextNames.add(name);
    }

    public void renderBasic(RenderPanel targetPanel) {
        VectorMap colorMap = new VectorMap(targetPanel.getWidth(), targetPanel.getHeight());

        SDFTracer tracer = new SDFTracer(getCurrentObject(), 128, 1e-8);

        for (int x = 0; x < colorMap.getWidth(); x++) {
            for (int y = 0; y < colorMap.getHeight(); y++) {
                Vector origin = new Vector(
                        (x - (targetPanel.getWidth() / 2.)) / 100.,
                        5,
                        -(y - (targetPanel.getHeight() / 2.)) / 100.
                );
                Ray ray = new Ray(
                        Vector.rotate(origin, new Vector(0, 0, 1), cameraAngle),
                        Vector.rotate(new Vector(0, -1, 0), new Vector(0, 0, 1), cameraAngle)
                );

                Vector hit = tracer.trace(ray);
                if (hit != null) {
                    Vector normal = tracer.normalAt(hit);

                    colorMap.set(
                            x, y,
                            getCurrentShader().shadeAbstract(
                                    hit, ray.direction().scale(-1),
                                    normal,
                                    getCurrentLightingContext().getAbstractLights()
                            )
                    );
                }
            }
        }

        targetPanel.setRender(colorMap.toImage());
    }

    public void renderPBR(RenderPanel targetPanel) {
        VectorMap colorMap = new VectorMap(targetPanel.getWidth(), targetPanel.getHeight());

        SDFTracer tracer = new SDFTracer(getCurrentObject(), 128, 1e-8);

        for (int x = 0; x < colorMap.getWidth(); x++) {
            for (int y = 0; y < colorMap.getHeight(); y++) {
                Vector origin = new Vector(
                        (x - (targetPanel.getWidth() / 2.)) / 100.,
                        5,
                        -(y - (targetPanel.getHeight() / 2.)) / 100.
                );
                Ray ray = new Ray(
                        Vector.rotate(origin, new Vector(0, 0, 1), cameraAngle),
                        Vector.rotate(new Vector(0, -1, 0), new Vector(0, 0, 1), cameraAngle)
                );

                Vector hit = tracer.trace(ray);
                if (hit != null) {
                    Vector normal = tracer.normalAt(hit);

                    colorMap.set(
                            x, y,
                            getCurrentShader().shadePBR(
                                    hit, ray.direction().scale(-1),
                                    normal,
                                    getCurrentHDRI()
                            )
                    );
                }
            }
        }

        targetPanel.setRender(colorMap.toImage());
    }
}
