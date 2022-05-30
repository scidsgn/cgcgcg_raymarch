package com.scidsgn.raymarch.hdri;

import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.math.VectorMap;

public class ColorMapHDRI extends HDRI {
    private final VectorMap colorMap;
    private final VectorMap diffuseMap;

    private final int diffuseMapScale;

    public ColorMapHDRI(VectorMap colorMap, int diffuseMapScale) {
        this.colorMap = colorMap;

        this.diffuseMap = new VectorMap(colorMap.getWidth() / diffuseMapScale, colorMap.getHeight() / diffuseMapScale);
        this.diffuseMapScale = diffuseMapScale;
        prepareDiffuseMap();
    }

    private void prepareDiffuseMap() {
        for (int x = 0; x < diffuseMap.getWidth(); x++) {
            for (int y = 0; y < diffuseMap.getHeight(); y++) {
                diffuseMap.set(x, y, calculateDiffuseAt(imageCoordinatesToNormal(x * diffuseMapScale, y * diffuseMapScale)));
            }
        }
    }

    private Vector calculateDiffuseAt(Vector normal) {
        Vector diffuseGather = new Vector(0, 0, 0);
        double sum = 0.;

        for (int x = 0; x < colorMap.getWidth(); x += diffuseMapScale) {
            for (int y = 0; y < colorMap.getHeight(); y += diffuseMapScale) {
                Vector pixelNormal = imageCoordinatesToNormal(x, y);
                double intensity = Math.max(0, Vector.dot(normal, pixelNormal));
                if (intensity == 0) {
                    continue;
                }

                diffuseGather = Vector.add(diffuseGather, colorMap.get(x, y).scale(intensity));
                sum += intensity;
            }
        }

        return diffuseGather.scale(1. / sum);
    }

    private double[] anglesToImageCoordinates(double azimuth, double inclination) {
        double x = (azimuth + Math.PI) / (2 * Math.PI);
        double y = inclination / Math.PI;

        return new double[] { (x * colorMap.getWidth()), (y * colorMap.getHeight()) };
    }

    private double[] imageCoordinatesToAngles(int x, int y) {
        double azimuth = ((double)x / colorMap.getWidth()) * 2 * Math.PI - Math.PI;
        double inclination = ((double)y / colorMap.getHeight()) * Math.PI;

        return new double[] { azimuth, inclination };
    }

    private Vector imageCoordinatesToNormal(int x, int y) {
        double[] angles = imageCoordinatesToAngles(x, y);

        return sphericalToNormal(angles[0], angles[1]);
    }

    @Override
    public Vector sample(double azimuth, double inclination) {
        double[] coords = anglesToImageCoordinates(azimuth, inclination);

        return colorMap.getBilinear(coords[0], coords[1]);
    }

    @Override
    public Vector sampleDiffuse(double azimuth, double inclination) {
        double[] coords = anglesToImageCoordinates(azimuth, inclination);

        return diffuseMap.getBilinear(coords[0] / diffuseMapScale, coords[1] / diffuseMapScale);
    }
}
