package com.example.paint;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;


/*Clasa curenta genereaza interfata grafica in care se pot genera figuri geometrice de diferite culori
Atributul shapes impiedica programul de a genera mai mult de 500 de figuri, contorizunsdu-le folosing shapeCount
canvas reprezinta locul in care se genereaza figurile geometrice

*  */
public class HelloApplication extends Application {

    private Shape[] shapes = new Shape[500];
    private int shapeCount = 0;
    private Canvas canvas;
    private Color culoareCurenta = Color.WHITE;

    /*Metoda start creeaza pannel-ul aplicatiei denumita "Paint"
     * */
    public void start(Stage stage) {
        canvas = makeCanvas();
        StackPane canvasHolder = new StackPane(canvas);
        canvasHolder.setStyle("-fx-border-width: 2px; -fx-border-color: #444");
        BorderPane root = new BorderPane(canvasHolder);
        root.setStyle("-fx-border-width: 1px; -fx-border-color: black");
        root.setTop(makeToolPanel(canvas));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.setResizable(false);
        stage.show();
    }

/*
 Metoda makeCanvas creeaza posibilitatea de a muta o figura geometrica folosing metodele: mousePressed, mouseReleased si mouseDragged
 */

    private Canvas makeCanvas() {
        Canvas canvas = new Canvas(800,600);
        canvas.setOnMousePressed( this::mousePressed );
        canvas.setOnMouseReleased( this::mouseReleased );
        canvas.setOnMouseDragged( this::mouseDragged );
        return canvas;
    }
/*
Optiunile care apar in tool-ul de sus
 */

    private HBox makeToolPanel(Canvas canvas) {
        Button triangleButton=new Button("Triangle");
        triangleButton.setOnAction( (e) -> addShape( new TriangleShape()) );
        Button ovalButton = new Button("Oval");
        ovalButton.setOnAction( (e) -> addShape( new OvalShape() ) );
        Button rectButton = new Button("Rectangle");
        rectButton.setOnAction( (e) -> addShape( new RectShape() ) );
        Button roundRectButton = new Button("RoundRectangle");
        roundRectButton.setOnAction( (e) -> addShape( new RoundRectShape() ) );
        ColorPicker MyColorPicker = new ColorPicker();
        MyColorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                culoareCurenta = MyColorPicker.getValue();
            }
        });

        HBox tools = new HBox(10);

        tools.getChildren().add(ovalButton);
        tools.getChildren().add(triangleButton);
        tools.getChildren().add(rectButton);
        tools.getChildren().add(roundRectButton);
        tools.getChildren().add(MyColorPicker);
        tools.setStyle("-fx-border-width: 5px; -fx-border-color: transparent; -fx-background-color: lightgrey");
        return tools;
    }
    /*
     * metoda care permite creearea in canvas
     * */
    private void paintCanvas(Color white) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.WHITE);
        g.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        for (int i = 0; i < shapeCount; i++) {
            Shape s = shapes[i];
            s.draw(g);
        }
    }
/*
Adaugarea unei figuri geometrice si contorizarea ei
 */

    private void addShape(Shape shape) {
        shape.setColor(culoareCurenta);
        shape.reshape(10,10,150,100);
        shapes[shapeCount] = shape;
        shapeCount++;
        paintCanvas(Color.WHITE);
    }



    private Shape shapeBeingDragged = null;


    private int prevDragX;
    private int prevDragY;
/*
Metoda mousePressed permite mutarea unei figuri geometrice cu ajutorul unui click
 */

    private void mousePressed(MouseEvent evt) {

        int x = (int)evt.getX();
        int y = (int)evt.getY();
        for ( int i = shapeCount - 1; i >= 0; i-- ) {
            Shape s = shapes[i];
            if (s.containsPoint(x,y)) {
                shapeBeingDragged = s;
                prevDragX = x;
                prevDragY = y;
                if (evt.isShiftDown()) {
                    for (int j = i; j < shapeCount-1; j++) {

                        shapes[j] = shapes[j+1];
                    }
                    shapes[shapeCount-1] = s;
                    paintCanvas(Color.WHITE);
                }
                return;
            }
        }
    }
    /*
    Deplasarea unei figuri geometrice
     */
    private void mouseDragged(MouseEvent evt) {
        int x = (int)evt.getX();
        int y = (int)evt.getY();
        if (shapeBeingDragged != null) {
            shapeBeingDragged.moveBy(x - prevDragX, y - prevDragY);
            prevDragX = x;
            prevDragY = y;
            paintCanvas(Color.WHITE);
        }
    }
    /*
    Fixarea unei figuri geometrice la eliberare
     */
    private void mouseReleased(MouseEvent evt) {

        shapeBeingDragged = null;
    }
    public static void main(String[] args) {
        launch(args);
    }

}
