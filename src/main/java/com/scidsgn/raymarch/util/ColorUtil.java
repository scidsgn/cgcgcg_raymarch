package com.scidsgn.raymarch.util;

import com.scidsgn.raymarch.math.Vector;

import java.awt.*;

public class ColorUtil {
    public static double channelToFilmic(double channel) {
        channel = Math.max(0, channel);

        return Math.tanh(2.6829 * Math.pow(channel / 10, 0.38));
    }

    public static double channelClamp(double channel) {
        return Math.min(1, Math.max(0, channel));
    }

    public static Color linearVectorToFilmic(Vector linearColor) {
        return new Color(
                (float)channelToFilmic(linearColor.x()),
                (float)channelToFilmic(linearColor.y()),
                (float)channelToFilmic(linearColor.z())
        );
    }

    public static Color linearVectorToLinearColor(Vector linearColor) {
        return new Color(
                (float)channelClamp(linearColor.x()),
                (float)channelClamp(linearColor.y()),
                (float)channelClamp(linearColor.z())
        );
    }
}
