package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridComponent implements FXComponent{
    private Controller _controller;
    GridPane gridpane;
    BorderPane borderPane;

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



        return borderPane;
    }
}
