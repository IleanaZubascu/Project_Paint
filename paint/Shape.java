package com.example.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/*
Clasa Shape formeaza figurile geometrice si culoarea acestora
 */
public abstract class Shape {



    int stanga, sus;
    int latime, inaltime;
    Color color = Color.WHITE;

    void reshape(int left, int top, int width, int height) {
        this.stanga = left;
        this.sus = top;
        this.latime = width;
        this.inaltime = height;
    }

    void moveBy(int dx, int dy) {

        stanga += dx;
        sus += dy;
    }

    void setColor(Color color) {

        this.color = color;
    }

    boolean containsPoint(int x, int y) {

        if (x >= stanga && x < stanga +latime && y >= sus && y < sus + inaltime)
            return true;
        else
            return false;
    }

    abstract void draw(GraphicsContext g);


}
