package com.example.paint;

import com.example.paint.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/*
Clasa pentru creearea triunghiurilor
 */
public class TriangleShape extends Shape {
    double x[]={100,150,50};
    double y[]={100,150,150};
    int n=3;
    void draw(GraphicsContext g)
    {
        g.setFill(color);
        g.fillPolygon(x, y, n);
        g.setStroke(Color.BLACK);
        g.strokePolygon(x,y,n);

    }
}
