package com.example.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/*
clasa pentru creearea dreptunghiurilor cu varfuri rotunjite
 */
public class RoundRectShape extends Shape {
    void draw(GraphicsContext g) {
        g.setFill(color);
        g.fillRoundRect(stanga, sus,latime, inaltime,latime/3, inaltime /3);
        g.setStroke(Color.BLACK);
        g.strokeRoundRect(stanga, sus,latime, inaltime,latime/3, inaltime /3);
    }
}