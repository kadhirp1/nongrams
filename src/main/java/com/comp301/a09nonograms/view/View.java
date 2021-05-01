package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View implements FXComponent{
    private final Controller _controller;

    public View(Controller controller){
        _controller = controller;
    }

    @Override
    public Parent render() {
        VBox vBox = new VBox();
        GridComponent grid = new GridComponent(_controller);
        vBox.getChildren().add(grid.render());
        return vBox;
    }
}
