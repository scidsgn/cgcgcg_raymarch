package com.scidsgn.raymarch.math;

import com.scidsgn.raymarch.util.ColorUtil;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VectorMap {
    private final Vector[][] data;

    @Getter
    private final int width, height;

    public VectorMap(int width, int height) {
        this.width = width;
        this.height = height;

        this.data = new Vector[width][height];
        clear();
    }

    public void clear() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.data[x][y] = new Vector(0, 0, 0);
            }
        }
    }

    public void scale(double s) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.data[x][y] = this.data[x][y].scale(s);
            }
        }
    }

    public Vector get(int x, int y) {
        return this.data[x % width][y % height];
    }

    public Vector getBilinear(double x, double y) {
        int xmin = (int)x;
        int ymin = (int)y;
        int xmax = xmin + 1;
        int ymax = ymin + 1;

        double lerpX = x - xmin;
        double lerpY = y - ymin;

        return Vector.lerp(
                lerpY,
                Vector.lerp(
                        lerpX, get(xmin, ymin), get(xmax, ymin)
                ),
                Vector.lerp(
                        lerpX, get(xmin, ymax), get(xmax, ymax)
                )
        );
    }

    public void set(int x, int y, Vector vector) {
        this.data[x % width][y % height] = vector;
    }

    public BufferedImage toImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, ColorUtil.linearVectorToFilmic(get(x, y)).getRGB());
            }
        }

        return image;
    }

    public static VectorMap fromImage(BufferedImage image) {
        VectorMap map = new VectorMap(image.getWidth(), image.getHeight());

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color color = new Color(image.getRGB(x, y));

                map.set(
                        x, y,
                        new Vector(
                                color.getRed() / 255.,
                                color.getGreen() / 255.,
                                color.getBlue() / 255.
                        )
                );
            }
        }

        return map;
    }
}
