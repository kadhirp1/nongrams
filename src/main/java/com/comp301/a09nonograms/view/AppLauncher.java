package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import com.comp301.a09nonograms.model.ModelObserver;
import com.comp301.a09nonograms.model.Puzzles;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Launch your GUI here
    Group group = new Group();

    PuzzleLibrary lib = new PuzzleLibrary();
    Model _model = new ModelImpl(lib.create());

    _model.setPuzzleIndex(3);
    Controller control = new ControllerImpl(_model);
    FXComponent comp = new GridComponent(control);

    ModelObserver toggleObserver = (Model model) ->{
      //First line is to represent changes to individual cells
      comp.render();
      //Potentially render solved component
    };

     control.getModel().addObserver(toggleObserver);
    VBox mainThing = new VBox();
    mainThing.setAlignment(Pos.CENTER);
    mainThing.getChildren().add(comp.render());
    Scene scene = new Scene(mainThing,700,700);
    stage.setScene(scene);
    stage.show();
  }
}
