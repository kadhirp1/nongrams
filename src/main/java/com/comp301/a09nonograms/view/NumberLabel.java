package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NumberLabel implements FXComponent {
  private final Controller _controller;

  public NumberLabel(Controller controller) {
    _controller = controller;
  }

  @Override
  public Parent render() {
    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    int currIndex = _controller.getModel().getPuzzleIndex();
    currIndex++;
    String currPuzzleIndex = String.valueOf(currIndex);
    String maxPuzzleCount = String.valueOf(_controller.getModel().getPuzzleCount());
    Text text =
        new Text(
            "You are currently playing nonogram "
                + currPuzzleIndex
                + " of "
                + maxPuzzleCount
                + "!");
    text.setFill(Color.DARKBLUE);
    text.setStyle("-fx-font: 28 arial;");
    vBox.getChildren().add(text);
    Text instructions = new Text("Fill the rows and columns according to the clues!");
    Text details = new Text("A green x indicates shaded and a red x indicates eliminated");
    instructions.setFill(Color.DARKMAGENTA);
    details.setFill(Color.DARKMAGENTA);
    instructions.setStyle("-fx-font: 18 arial;");
    details.setStyle("-fx-font: 18 arial;");
    vBox.getChildren().add(instructions);
    vBox.getChildren().add(details);
    vBox.setSpacing(15);
    return vBox;
  }
}
