package com.brecord.csis1400.hw1.shapes;

import com.brecord.csis1400.hw1.Coordinate;

import java.util.HashMap;
import java.util.Map;

public abstract class Shape {
    private final byte width;
    private final byte height;
    private final Map<Coordinate, Character> coordinateValues;

    public Shape(final byte width, final byte height) {
        this.width = width;
        this.height = height;
        coordinateValues = new HashMap<Coordinate, Character>();
    }

    public byte getWidth() {
        return width;
    }

    public byte getHeight() {
        return height;
    }

    public Map<Coordinate, Character> getCoordinateValues() {
        return coordinateValues;
    }

    public void addCoordinateValue(final Coordinate coordinate, final char character) {
        coordinateValues.put(coordinate, character);
    }
}
