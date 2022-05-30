package com.scidsgn.raymarch.rendering.light;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LightingContext {
    @Getter
    private final List<AbstractLight> abstractLights;

    public LightingContext() {
        abstractLights = new ArrayList<>();
    }

    public LightingContext addAbstractLight(AbstractLight ...lights) {
        Collections.addAll(abstractLights, lights);

        return this;
    }
}
