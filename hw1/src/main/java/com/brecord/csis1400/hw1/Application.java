package com.brecord.csis1400.hw1;

import com.brecord.csis1400.hw1.shapes.Cosine;

public class Application {
    public static void main(String[] args) {
        final DrawRunner runner = new DrawRunner();
        runner.addShape(new Cosine((byte)1, (byte)1));
    }
}
