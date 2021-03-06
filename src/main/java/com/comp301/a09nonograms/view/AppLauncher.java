package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.util.List;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Launch your GUI here
    List<Clues> list = new PuzzleLibrary().create();
    Model model = new ModelImpl(list);
    Controller controller = new ControllerImpl(model);

    View view = new View(controller);
    Scene scene = new Scene(view.render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
    /*
    model.addObserver((Model m)->{
      scene.setRoot(view.render());
      stage.sizeToScene();
    });

     */
    // stage.sizeToScene();

    ModelObserver toggle =
        (Model _model) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        };
    model.addObserver(toggle);
    stage.setTitle("Nonograms");
    stage.show();
  }
}
