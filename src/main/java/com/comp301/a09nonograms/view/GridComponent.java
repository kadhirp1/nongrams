package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GridComponent implements FXComponent {
  private Controller _controller;
  GridPane gridpane;
  BorderPane borderPane;
  int finalI;
  int finalJ;
  int num;

  public GridComponent(Controller controller) {
    _controller = controller;
    // gridpane = new GridPane();
    // borderPane = new BorderPane();
  }

  public void handle(MouseEvent mouseEvent, Controller controller, int i, int j) {
    if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
      controller.toggleShaded(i, j);
    }
    if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
      controller.toggleEliminated(i, j);
    }
  }

  String black = "-fx-background-color: #000000; ";
  String red = "-fx-background-color: #FF0000; ";
  String green = "-fx-background-color: #00FF00; ";

  public void findLocation(Button button) {
    int row = GridPane.getRowIndex(button);
  }

  @Override
  public Parent render() {
    VBox vbox = new VBox();
    vbox.setSpacing(10);
    // Rectangle rect = new Rectangle(200,50);
    // rect.setFill(Color.WHITE);
    Text text = new Text("Congrats! This puzzle is solved!");
    text.setStyle("-fx-font: 20 arial;");
    text.setSelectionFill(Color.YELLOW);
    Text text2 = new Text("");
    text2.setStyle("-fx-font: 20 arial;");
    text2.setSelectionFill(Color.RED);
    gridpane = new GridPane();
    gridpane.setHgap(20);
    gridpane.setVgap(20);
    // Text text = new Text("Congratulations! You solved the puzzle");
    Button textBut = new Button();

    // vbox.getChildren().add(textBut);
    boolean solve = _controller.getModel().isSolved();
    if (solve) {
      vbox.getChildren().add(text);

      vbox.setAlignment(Pos.CENTER);
    } else {
      vbox.getChildren().add(text2);
    }
    // System.out.println(solve);
    // stackpane.getChildren().addAll(rect,text);
    int rowBorder = _controller.getModel().getRowCluesLength();
    int colBorder = _controller.getModel().getColCluesLength();
    // System.out.println(rowBorder + " " + colBorder);
    // System.out.println("Rerender");
    // AtomicBoolean bool = new AtomicBoolean(false);
    // System.out.println(bool.get());
    // vbox.getChildren().add(textBut);
    for (int i = 0; i < _controller.getModel().getHeight() + colBorder; i++) {
      for (int j = 0; j < _controller.getModel().getWidth() + rowBorder; j++) {
        // Button button = new Button();
        finalJ = j;
        finalI = i;
        // System.out.println(finalI);
        // System.out.println(i + " " + j);
        if (i < colBorder && j < rowBorder) {
          Button button = new Button();
          // System.out.println("Blank Cell " + i + " " + j);
          // gridpane.add(button,j,i);
        } else if (i < colBorder && j >= rowBorder) {
          // Set Col clues here/
          Button button = new Button();
          Rectangle rectangle = new Rectangle(25, 25);
          rectangle.setArcHeight(10.0d);
          rectangle.setArcWidth(10.0d);
          rectangle.setFill(Color.MEDIUMPURPLE);
          Text clue = new Text("");
          // Rectangle rect1 = new Rectangle(25,25   );
          // System.out.println("Col Clues " + i + " " + j);
          clue.setText(String.valueOf(_controller.getModel().getColClues(j - rowBorder)[i]));
          clue.setStyle("-fx-font: 14 arial;");
          StackPane stackPane = new StackPane();
          stackPane.getChildren().addAll(rectangle, clue);
          gridpane.add(stackPane, j, i);
        } else if (i >= colBorder && j < rowBorder) {
          // set Row clues here
          Button button = new Button();
          Rectangle rectangle = new Rectangle(25, 25);
          rectangle.setArcHeight(10.0d);
          rectangle.setArcWidth(10.0d);
          rectangle.setFill(Color.MEDIUMPURPLE);
          Text clue = new Text("");
          // System.out.println("Row clues " + i + " " + j);
          clue.setText(String.valueOf(_controller.getModel().getRowClues(i - colBorder)[j]));
          clue.setStyle("-fx-font: 14 arial;");
          StackPane stackPane = new StackPane();
          stackPane.getChildren().addAll(rectangle, clue);
          gridpane.add(stackPane, j, i);
        } else {
          // Set all buttons here
          // Button button = new Button();
          StackPane rectangle = new StackPane();
          rectangle.getStyleClass().add("border-pane-fill");
          // System.out.println("Board " + i + " " + j);
          Button button = new Button();
          // Rectangle rectangle1 = new Rectangle(25,25);

          gridpane.add(rectangle, j, i);
          Text xMark = new Text("x");
          Rectangle fillValue = new Rectangle();
          xMark.setFill(Color.WHITE);
          fillValue.setFill(Color.WHITE);
          rectangle.setAlignment(Pos.CENTER);
          rectangle.getChildren().addAll(fillValue, xMark);
          rectangle.setOnMouseClicked(
              (MouseEvent event) -> {
                int row = GridPane.getRowIndex(rectangle);
                int col = GridPane.getColumnIndex(rectangle);
                if (event.getButton() == MouseButton.PRIMARY) {
                  _controller.getModel().toggleCellShaded(row - colBorder, col - rowBorder);
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                  _controller.getModel().toggleCellEliminated(row - colBorder, col - rowBorder);
                }
              });

          int row = GridPane.getRowIndex(rectangle);
          int col = GridPane.getColumnIndex(rectangle);
          if (_controller.getModel().isShaded(row - colBorder, col - rowBorder)) {
            // fillValue.setFill(Color.GREEN);
            // rectangle1.setFill(Color.GREEN);
            // button.setStyle(green);
            xMark.setFill(Color.GREEN);
            xMark.setStyle("-fx-font: 20 arial;");
          }
          if (_controller.getModel().isEliminated(row - colBorder, col - rowBorder)) {
            // button.setStyle(red);
            // rectangle1.setFill(Color.RED);
            xMark.setFill(Color.RED);
            xMark.setStyle("-fx-font: 16 arial;");
          }
          if (_controller.getModel().isSpace(row - colBorder, col - rowBorder)) {
            // rectangle1.setFill(Color.BLACK);
            // button.setStyle(black);

          }
        }
      }
    }
    /*
            gridpane.setOnMouseClicked(event->
            {
                if (event.getButton() == MouseButton.PRIMARY){
                    //System.out.println("Grid pane left clicked");
                    if (_controller.isSolved()){
                        //System.out.println("Solved!");
                        //textBut.setTextFill(Color.BLACK);
                    }
                }
            });
            textBut.setText("Congratulations! You solved the puzzle");
            if (_controller.isSolved()){
                vbox.getChildren().add(textBut);
            }


            StackPane stack = new StackPane();
            Text text = new Text("Solved!");
            Rectangle rect1 = new Rectangle(100,25);
            rect1.setFill(Color.WHITE);
    */
    gridpane.setAlignment(Pos.CENTER);
    vbox.getChildren().add(gridpane);
    return vbox;
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
