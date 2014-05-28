package com.devddagnet.bright.lib;

import android.graphics.Bitmap;

public class Bright {

    private static final Luminance sInstance = new Luminance();

    private Bright() {

    }

    public static Luminance getInstance() {
        return sInstance;
    }

    public static Luminance setup(int config) {
        return new Luminance(config);
    }

    public static final class Luminance {

        private int mConfig;

        private Luminance() {
            this(Config.RELATIVE);
        }

        private Luminance(int config) {
            mConfig = config;
        }

        public Luminance config(int config) {
            mConfig = config;
            return (this);
        }

        public boolean isBright(int luminance) {
            return (luminance > 130);
        }

        public boolean isBright(Bitmap bitmap) {
            return isBright(brightness(bitmap));
        }

        public boolean isBright(int[] rgb) {
            return isBright(brightness(rgb));
        }

        public boolean isBright(int r, int g, int b) {
            return isBright(brightness(r, g, b));
        }

        public int brightness(Bitmap bitmap) {
            return this.brightness(Utility.getAverageColorRGB(bitmap));
        }

        public int brightness(int color) {
            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = color & 0xFF;

            return (this.brightness(r, g, b));
        }

        public int brightness(int[] rgb) {
            return (this.brightness(rgb[0], rgb[1], rgb[2]));
        }

        public int brightness(int r, int g, int b) {
            switch (mConfig) {
                case Config.RELATIVE | Config.PERFORMANCE:
                    return ((r + r + b + g + g + g) / 6);
                case Config.PERCEIVED | Config.PERFORMANCE:
                    return ((r + r + r + b + g + g + g + g) >> 3);
                case Config.RELATIVE:
                    return (Math.round((0.2126f * r + 0.7152f * g + 0.0722f * b)));
                case Config.PERCEIVED:
                default:
                    return (Math.round(0.299f * r + 0.587f * g + 0.114f * b));
            }
        }
    }

    public static final class Config {

        /**
         * trade accuracy for performance
         */
        public static final int PERFORMANCE = 1;
        /**
         * luminance relative: Luma CCIR 601
         */
        public static final int RELATIVE    = 2;
        /**
         * luminance perceived: Luma BT. 709
         */
        public static final int PERCEIVED   = 4;
    }
}
