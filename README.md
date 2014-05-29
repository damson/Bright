# Bright

<img src='./bright_screen_sample_multicolor.png' width='270' height='480' />
<img src='./bright_screen_sample_picture.png' width='270' height='480' />

## Description

Bright provides an easy way to determine if a background color is light or dark in order to choose
an appropriate text color (black on light colors and white on dark colors) or to convert an image
to grayscale.
To do so, it calculates the luminance of a color from its rgb value
or the luminance of an area (`bitmap`) from its average color.

The library is configurable through two variants:

* `RELATIVE`: relative luminance following the luma recommendation [BT. 709](http://en.wikipedia.org/wiki/Rec._709)
* `PERCEIVED`: perceived luminance following the luma recommendation [CCIR 601](http://en.wikipedia.org/wiki/CCIR_601)

and a performance boost option:

* `PERFORMANCE`: trade accuracy for performance

## Usage

#### Basic

Get the luminance of a color or an area

    int luminance = Bright.getInstance().brightness(color/rgb/bitmap);

Check if it is brighter than dark

    Bright.getInstance().isBright(luminance);

Or directly check from a color or an area

    Bright.getInstance().isBright(rgb/bitmap)

#### Options

Luminance variants: `RELATIVE`, `PERCEIVED`

Performance boost: `PERFORMANCE`

Get brightness from the relative luminance

    int luminance = Bright.setup(Config.RELATIVE).brightness(color/rgb/bitmap)

Get brightness from the perceived luminance

    int luminance = Bright.setup(Config.PERCEIVED).brightness(color/rgb/bitmap)

Trade accuracy for performance

    Bright.setup(Config.RELATIVE | Config.PERFORMANCE)

    Bright.setup(Config.PERCEIVED | Config.PERFORMANCE)

## Sample

__[APK][Sample Apk] | [Sources][Sample Sources]__

`MultiColorFragment` - A set of colored blocks with a white or black text, relatively to the brightness
of the text background color.

`PictureFragment` - A series of pictures with text in all corners and in the center, white or black
relatively to the brightness of the background area

`Bright(X)` - X is the luminous intensity

### Video

[![Sample Video](http://img.youtube.com/vi/g56IAIRhNGM/0.jpg)](http://youtu.be/g56IAIRhNGM)

## Glossary

__Luminance__: a photometric measure of the luminous intensity
per unit area of light travelling in a given direction.

## License

    Copyright 2014 Damien Dagnet

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[Sample Sources]: https://github.com/damson/Bright/tree/master/sample/src/main/java/com/devddagnet/bright/sample
[Sample Apk]: https://github.com/damson/Bright/raw/master/sample/sample.apk