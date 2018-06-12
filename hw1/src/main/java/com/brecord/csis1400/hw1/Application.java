package com.brecord.csis1400.hw1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Application {
    public static void main(String[] args) throws IOException {
        final String url = "http://images.clipartpanda.com/simple-black-and-white-tree-design-jixxLnAiE.png";
//        final String url = "https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX19008563.jpg";

        final BufferedImage img = ImageIO.read(new URL(url));

        final int height = img.getHeight(),
                width = img.getWidth(),
                maxWidth = 100,
                maxHeight = 50;

        final double stepY = (double) height / (double) maxHeight,
                stepX = (double) width / (double) maxWidth;

        final StringBuilder sb = new StringBuilder();

        for (int y = 0; y < height; y += stepY < 1 ? 1 : stepY) {
            for (int x = 0; x < width; x += stepX < 1 ? 1 : stepX) {
                final int rgb = img.getRGB(x, y);
                int red = (rgb & 0x00ff0000) >> 16,
                        green = (rgb & 0x0000ff00) >> 8,
                        blue = rgb & 0x000000ff;
                sb.append(red < 125 && green < 125 && blue < 125 ? '*' : ' ');
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
