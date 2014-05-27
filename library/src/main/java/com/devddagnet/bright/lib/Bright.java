package com.devddagnet.bright.lib;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Bright {

    public static boolean isBright(int luminance) {
        return (luminance > 130);
    }

    public static int getBrightness(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        return (getBrightness(r, g, b));
    }

    public static int getBrightness(int[] rgb) {
        return (getBrightness(rgb[0], rgb[1], rgb[2]));
    }

    public static int getBrightness(int r, int g, int b) {
        return ((r + r + r + b + g + g + g + g) >> 3);
    }

    public static int[] averageRGB(Bitmap pic) {
        int R, G, B;
        int pixelColor;
        int width = pic.getWidth();
        int height = pic.getHeight();
        int size = width * height;

        R = G = B = 0;
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                pixelColor = pic.getPixel(x, y);
                R += Color.red(pixelColor);
                G += Color.green(pixelColor);
                B += Color.blue(pixelColor);
            }
        }

        R /= size;
        G /= size;
        B /= size;

        return (new int[]{ R, G, B });
    }
}
