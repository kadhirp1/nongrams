package com.comp301.a09nonograms;

import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import com.comp301.a09nonograms.view.AppLauncher;
import javafx.application.Application;

public class Main {
  public static void main(String[] args) {

    /*
    PuzzleLibrary lib = new PuzzleLibrary();
    Model _model = new ModelImpl(lib.create());

    Controller control = new ControllerImpl(_model);
    int bound1 = control.getModel().getWidth();
    int bound2 = control.getModel().getColClues(0).length;
    for (int i=0; i<bound1; i++){
      for (int j=0; j<bound2; j++){
        System.out.println(control.getModel().getColClues(i)[j]);
      }
    }

     */
    Application.launch(AppLauncher.class);
  }
}
