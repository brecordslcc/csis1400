package com.brecord.csis1400.hw1;

public class Coordinate {
    private final byte x;

    public Coordinate(final byte x, final byte y) {
        this.x = x;
        this.y = y;
    }

    public byte getX() {

        return x;
    }

    public byte getY() {
        return y;
    }

    private final byte y;
}
