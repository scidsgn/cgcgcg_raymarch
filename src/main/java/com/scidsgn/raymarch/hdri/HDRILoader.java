package com.scidsgn.raymarch.hdri;

import com.google.common.io.LittleEndianDataInputStream;
import com.scidsgn.raymarch.math.Vector;
import com.scidsgn.raymarch.math.VectorMap;

import javax.imageio.ImageIO;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HDRILoader {
    public static HDRI load(File file, int width, int height) throws IOException {
        LittleEndianDataInputStream stream = new LittleEndianDataInputStream(new FileInputStream(file));

        VectorMap map = new VectorMap(width, height);

        for (int y = height - 1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                map.set(
                        x, y,
                        new Vector(
                                Math.min(stream.readDouble(), 64),
                                Math.min(stream.readDouble(), 64),
                                Math.min(stream.readDouble(), 64)
                        )
                );
                stream.readDouble();
            }
        }

        return new ColorMapHDRI(map, 10);
    }
}
