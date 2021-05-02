package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.*;

public class View implements FXComponent {
  private final Controller _controller;

  public View(Controller controller) {
    _controller = controller;
  }

  @Override
  public Parent render() {

    VBox vBox = new VBox();
    GridComponent grid = new GridComponent(_controller);
    ControlPanel controlPanel = new ControlPanel(_controller);
    NumberLabel numberLabel = new NumberLabel(_controller);
    vBox.getChildren().add(numberLabel.render());
    vBox.getChildren().add(grid.render());
    vBox.getChildren().add(controlPanel.render());
    vBox.setSpacing(30);
    vBox.setStyle("-fx-background-color: linear-gradient( to bottom, lightblue, pink );");

    return vBox;
  }
}
