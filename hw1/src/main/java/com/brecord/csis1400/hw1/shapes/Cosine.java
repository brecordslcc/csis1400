package com.brecord.csis1400.hw1.shapes;

import com.brecord.csis1400.hw1.Coordinate;

public class Cosine extends Shape {
    public Cosine(final byte width, final byte height) {
        super(width, height);

        for (byte x = 0; x < width; x++) {
            for (byte y = 0; y < height; y++) {
                addCoordinateValue(new Coordinate(x, y), '*');
            }
        }
    }
}
