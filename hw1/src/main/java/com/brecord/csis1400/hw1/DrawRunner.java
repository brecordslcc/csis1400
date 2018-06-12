package com.brecord.csis1400.hw1;

import com.brecord.csis1400.hw1.shapes.Shape;

import java.util.ArrayList;
import java.util.Collection;

public class DrawRunner {
    private final Collection<Shape> shapes;
    private final StringBuffer sb;

    public DrawRunner() {
        shapes = new ArrayList<Shape>();
        sb = new StringBuffer();
    }

    public void addShape(final Shape shape) {
        shapes.add(shape);
    }

    public void run() {
        sb.setLength(0);
        
        for (final Shape shape : shapes) {

        }
    }
}
