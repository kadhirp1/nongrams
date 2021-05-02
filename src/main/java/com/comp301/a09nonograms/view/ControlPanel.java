package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.util.Random;

public class ControlPanel implements FXComponent {
  private final Controller _controller;

  public ControlPanel(Controller controller) {
    _controller = controller;
  }

  @Override
  public Parent render() {
    HBox hbox = new HBox();
    hbox.setSpacing(25);

    Button nextButton = new Button();
    nextButton.setText("Next Puzzle");
    nextButton.setOnMouseClicked(
        event -> {
          int index = _controller.getModel().getPuzzleIndex();
          index++;
          _controller.getModel().setPuzzleIndex(index);
        });

    Button backButton = new Button();
    backButton.setText("Previous Puzzle");
    backButton.setOnMouseClicked(
        event -> {
          int index = _controller.getModel().getPuzzleIndex();
          index--;
          _controller.getModel().setPuzzleIndex(index);
        });

    Button randButton = new Button();
    randButton.setText("Random Puzzle");
    randButton.setOnMouseClicked(
        event -> {
          Random rand = new Random();
          int max = _controller.getModel().getPuzzleCount();
          int randIndex = rand.nextInt(max);
          _controller.getModel().setPuzzleIndex(randIndex);
        });

    Button clearButton = new Button();
    clearButton.setText("Clear Puzzle");
    clearButton.setOnMouseClicked(
        event -> {
          _controller.getModel().clear();
        });

    randButton.setAlignment(Pos.CENTER);
    backButton.setAlignment(Pos.CENTER);
    nextButton.setAlignment(Pos.CENTER);
    clearButton.setAlignment(Pos.CENTER);

    hbox.getChildren().add(randButton);
    hbox.getChildren().add(backButton);
    hbox.getChildren().add(nextButton);
    hbox.getChildren().add(clearButton);
    hbox.setAlignment(Pos.CENTER);
    return hbox;
  }
}
