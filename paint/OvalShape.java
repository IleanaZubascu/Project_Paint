package com.example.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/*
Clasa pentru creearea ovalurilor
 */

public class OvalShape extends Shape {

    void draw(GraphicsContext g) {
        g.setFill(color);
        g.fillOval(stanga, sus,latime, inaltime);
        g.setStroke(Color.BLACK);
        g.strokeOval(stanga, sus,latime, inaltime);
    }

}