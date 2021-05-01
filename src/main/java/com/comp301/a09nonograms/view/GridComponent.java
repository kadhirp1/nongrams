package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GridComponent implements FXComponent{
    private Controller _controller;
    GridPane gridpane;
    BorderPane borderPane;
    Rectangle rectangle;
    int finalI;
    int finalJ;
    int num;

    public GridComponent(Controller controller){
        _controller = controller;
        gridpane = new GridPane();
        borderPane = new BorderPane();
    }

    String black = "-fx-background-color: #000000; ";
    String red = "-fx-background-color: #FF0000; ";
    String green = "-fx-background-color: #00FF00; ";
    @Override
    public Parent render() {
        gridpane.setHgap(20);
        gridpane.setVgap(20);
        gridpane.setPrefSize(25,25);
        int rowBorder = _controller.getModel().getRowCluesLength();
        int colBorder = _controller.getModel().getColCluesLength();

        for (int i=0; i<_controller.getModel().getHeight() + rowBorder; i++){
            for (int j=0; j<_controller.getModel().getWidth() + colBorder; j++){
                Button button = new Button();
                if (i < rowBorder && j < colBorder){
                    //Button button = new Button();
                    gridpane.add(button,j,i);
                }
                else if (i < rowBorder && j >= colBorder){
                    //Set Col clues here
                    //Button button = new Button();
                    button.setText(String.valueOf(_controller.getModel().getColClues(j-colBorder)[i]));
                    gridpane.add(button,j,i);
                }
                else if (i >= rowBorder && j < colBorder){
                    //set Row clues here
                   //Button button = new Button();
                    button.setText(String.valueOf(_controller.getModel().getRowClues(i-rowBorder)[j]));
                    gridpane.add(button,j,i);
                }
                else{
                    //Set all buttons here
                    //Button button = new Button();
                    button.setStyle(black);
                    rectangle = new Rectangle(20,20);
                    finalJ = j;
                    finalI = i;

                    /*
                    rectangle.setOnMouseClicked((event -> {
                        if (rectangle.getFill().equals(Color.BLACK)){
                            if (event.getButton()==MouseButton.PRIMARY){
                                rectangle.setFill(Color.RED);
                            }
                        }
                    }));

                     */



                    button.setOnMouseClicked((event)->{
                        if (event.getButton() == MouseButton.PRIMARY){
                            //button.setStyle("-fx-background-color: #00FF00; ");
                            if(_controller.isShaded(finalI -rowBorder, finalJ -colBorder)){
                                button.setStyle(black);
                                System.out.println("Turn green to black");
                            }
                            else{
                                button.setStyle(green);
                                System.out.println("Turn green");
                            }

                            _controller.toggleShaded(finalI-rowBorder,finalJ-colBorder);
                        }
                        if (event.getButton() == MouseButton.SECONDARY){

                            if(_controller.isEliminated(finalI-rowBorder,finalJ-colBorder)){
                                button.setStyle(black);
                                System.out.println("Turn red to black");
                            }
                            else{
                                button.setStyle(red);
                                System.out.println("Turn red");
                            }
                            _controller.toggleEliminated(finalI-rowBorder,finalJ-colBorder);
                        }
                    });

                    gridpane.add(button,j,i);
                }
            }
        }
        return gridpane;
/*
        int i1 =0;
        for (int i=0; i<_controller.getModel().getWidth();i++){
            int j1 =0;
            for (int j=0; j<_controller.getModel().getHeight(); j++){

                Button button = new Button();
                int finalI = i1;
                int finalJ = j1;
                button.setStyle(black);
                button.setMinSize(gridpane.getPrefHeight(),gridpane.getPrefWidth());
                button.setOnMouseClicked((event) -> {
                    if (event.getButton() == MouseButton.SECONDARY){
                        //If right click on eliminated make it blank
                        //else set the cell to eliminated and notify
                        if (_controller.getModel().isEliminated(finalI,finalJ)){
                            button.setStyle(black);
                            _controller.toggleEliminated(finalI,finalJ);
                        }
                        //(_controller.getModel().isSpace(finalI,finalJ))
                        else {
                            button.setStyle(red);
                            _controller.toggleEliminated(finalI,finalJ);
                        }


                    }
                    if (event.getButton() == MouseButton.PRIMARY){
                        //If right click on eliminated make it blank
                        //else set the cell to eliminated and notify
                        if (_controller.getModel().isShaded(finalI,finalJ)){
                            button.setStyle(black);
                            _controller.toggleShaded(finalI,finalJ);
                        }
                        //(_controller.getModel().isSpace(finalI,finalJ))
                        else {
                            button.setStyle(green);
                            _controller.toggleShaded(finalI,finalJ);
                        }


                    }


                });

                gridpane.add(button,i,j);
                j1++;
            }
            i1++;
        }
        gridpane.setAlignment(Pos.TOP_LEFT);
        HBox topClues = new HBox(18);
        List<Button> buttonList = new ArrayList<>();
        int colCluesLength = _controller.getModel().getColClues(0).length;
        for (int i=0; i<_controller.getModel().getWidth(); i++){
            Button tempButton = new Button();
            VBox oneColClue = new VBox();
            for (int j=0; j<colCluesLength; j++){
                Button clueButton = new Button();
                clueButton.setStyle("-fx-background-color: #FFFFFF");
                clueButton.setText(String.format("%d",_controller.getModel().getColClues(i)[j]));
                oneColClue.getChildren().add(clueButton);
            }
            topClues.getChildren().add(oneColClue);
        }
        VBox leftClues = new VBox(20);
        int intRows = _controller.getModel().getRowClues(0).length;
        for (int i=0; i<_controller.getModel().getHeight(); i++){
            Button tempButton = new Button();
            HBox oneRowClue = new HBox();
            for (int j=0; j<intRows; j++){
                Button clueButton = new Button();
                clueButton.setStyle("-fx-background-color: #FFFFFF");
                clueButton.setText(String.format("%d",_controller.getModel().getRowClues(i)[j]));
                oneRowClue.getChildren().add(clueButton);
            }
            leftClues.getChildren().add(oneRowClue);
        }
        leftClues.setAlignment(Pos.CENTER);
        topClues.setAlignment(Pos.CENTER);
        gridpane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridpane);
        borderPane.setTop(topClues);
        borderPane.setLeft(leftClues);



 */


    }
}
