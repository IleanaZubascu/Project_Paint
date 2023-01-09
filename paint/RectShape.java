package com.example.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/*
Clasa pentru creearea dreptunghiurilor
 */
public class RectShape extends Shape {

    void draw(GraphicsContext g) {
        g.setFill(color);
        g.fillRect(stanga, sus,latime, inaltime);
        g.setStroke(Color.BLACK);
        g.strokeRect(stanga, sus,latime, inaltime);
    }
}