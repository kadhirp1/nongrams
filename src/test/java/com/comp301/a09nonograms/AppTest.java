package com.comp301.a09nonograms;

import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.*;
import org.junit.Test;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/** Unit test for simple App. */
public class AppTest {
  int[][] rowClues =
          new int[][] {
                  new int[] {0, 2},
                  new int[] {1, 2},
                  new int[] {0, 3},
                  new int[] {0, 3},
                  new int[] {1, 1},
          };
  int[][] colClues =
          new int[][] {
                  new int[] {1, 1},
                  new int[] {0, 1},
                  new int[] {0, 3},
                  new int[] {0, 3},
                  new int[] {3, 1},
          };
  Clues example = new CluesImpl(rowClues,colClues);
  /** Rigorous Test :-) */
/*
//Clues Tests

  @Test
  public void heightTest() {

    Clues example = new CluesImpl(rowClues,colClues);
    assertEquals(5,example.getHeight());
  }

  @Test
  public void widthTest(){
    Clues example = new CluesImpl(rowClues,colClues);
    assertEquals(5,example.getWidth());
  }

  @Test
  public void getRowTest(){
    Clues example = new CluesImpl(rowClues,colClues);
    int[] actual1 = {0, 2};
    int[] actual5 = {1,1};
    assertArrayEquals(actual1,example.getRowClues(0));
    assertArrayEquals(actual5,example.getRowClues(4));
  }

  @Test
  public void getColTest(){
    Clues example = new CluesImpl(rowClues,colClues);
    int[] actual1 = {1,1};
    int[] actual5 = {3,1};
    assertArrayEquals(actual1,example.getColClues(0));
    assertArrayEquals(actual5,example.getColClues(4));
  }

  @Test
  public void testRowCluesLength(){
    Clues example = new CluesImpl(rowClues,colClues);
    assertEquals(13,example.getRowCluesLength());
  }

  @Test
  public void testColCluesLength(){
    Clues example = new CluesImpl(rowClues,colClues);
    assertEquals(13,example.getColCluesLength());
  }
*/

  //Board Tests
  @Test
  public void testBlank(){
    Board board1 = new BoardImpl(example.getWidth(),example.getHeight());
    for (int i=0; i<example.getWidth();i++){
      for (int j=0; j<example.getHeight();j++){
        assertTrue(board1.isSpace(i,j));
      }
    }
  }
  @Test
  public void assertEliminated(){
    Board board1 = new BoardImpl(example.getWidth(),example.getHeight());
    assertTrue(board1.isSpace(2,2));
    board1.toggleCellEliminated(2,2);
    assertTrue(board1.isEliminated(2,2));
    board1.toggleCellEliminated(2,2);
    assertFalse(board1.isEliminated(2,2));
  }
  @Test
  public void assertShaded(){
    Board board1 = new BoardImpl(example.getWidth(),example.getHeight());
      assertTrue(board1.isSpace(2,2));
    board1.toggleCellShaded(1,4);
    assertTrue(board1.isShaded(1,4));
    board1.toggleCellShaded(1,4);
    assertFalse(board1.isShaded(1,4));
  }
  @Test
  public void testClear(){
    Board board1 = new BoardImpl(example.getWidth(),example.getHeight());
    board1.toggleCellShaded(1,4);
    board1.toggleCellEliminated(2,2);
    board1.clear();
    for (int i=0; i<example.getWidth();i++){
      for (int j=0; j<example.getHeight();j++){
        assertTrue(board1.isSpace(i,j));
      }
    }
  }

  @Test
  public void testHeight(){
    Board board1 = new BoardImpl(example.getWidth(), example.getHeight());
    int height = example.getHeight();
    assertEquals(5,height);
  }
  @Test
  public void testShit(){
    List<Clues> list = new ArrayList<>();
    list.add(example);
    Model _model = new ModelImpl(list);
    _model.toggleCellShaded(0,0);
    assertTrue(_model.isShaded(0,0));
  }
  @Test
  public void testSolve(){
    List<Clues> list = new ArrayList<>();
    list.add(example);
    Model _model = new ModelImpl(list);
    _model.toggleCellShaded(0,3);
    _model.toggleCellShaded(0,4);

    _model.toggleCellShaded(1,0);
    _model.toggleCellShaded(1,3);
    _model.toggleCellShaded(1,4);

    _model.toggleCellShaded(2,2);
    _model.toggleCellShaded(2,3);
    _model.toggleCellShaded(2,4);

    _model.toggleCellShaded(3,0);
    _model.toggleCellShaded(3,1);
    _model.toggleCellShaded(3,2);

    _model.toggleCellShaded(4,2);
    _model.toggleCellShaded(4,4);
    assertTrue(_model.isSolved());
  }




  int[][] r1 =
          new int[][] {
                  new int[] {0, 2},
                  new int[] {1, 2},
                  new int[] {0, 3},
                  new int[] {0, 3},
                  new int[] {1, 1},
                  new int[] {0, 3},
                  new int[] {1, 1},
                  new int[] {0, 3},
                  new int[] {1, 1},
          };
  int[][] c1 =
          new int[][] {
                  new int[] {1, 1},
                  new int[] {0, 1},
                  new int[] {0, 3},
                  new int[] {0, 3},
                  new int[] {3, 1},
                  new int[] {0, 3},
                  new int[] {1, 1},
                  new int[] {0, 3},
                  new int[] {1, 1},
          };
  Clues e1 = new CluesImpl(r1,c1);

  int[][] r2 =
          new int[][] {
                  new int[] {0, 2},
                  new int[] {1, 2},
                  new int[] {0, 3}
          };
  int[][] c2 =
          new int[][] {
                  new int[] {1, 1},
                  new int[] {0, 1},
                  new int[] {0, 3}
          };
  Clues e2 = new CluesImpl(r2,c2);

  @Test
  public void testArray(){
    PuzzleLibrary list1 =  new PuzzleLibrary();
    List<Clues> list = list1.create();
    Model model = new ModelImpl(list);
    assertEquals(8,model.getWidth());
    assertEquals(11,model.getHeight());

  }
  @Test
  public void testToggle(){
    PuzzleLibrary list1 =  new PuzzleLibrary();
    List<Clues> list = list1.create();
    Model model = new ModelImpl(list);
    assertTrue(model.isSpace(0,0));
    model.toggleCellShaded(0,0);
    assertTrue(model.isShaded(0,0));
    model.toggleCellEliminated(0,0);
    assertTrue(model.isEliminated(0,0));
    model.toggleCellEliminated(0,0);
    assertTrue(model.isSpace(0,0));
  }

  @Test
  public void testThrow(){
    PuzzleLibrary list1 =  new PuzzleLibrary();
    List<Clues> list = list1.create();
    Model model = new ModelImpl(list);
    boolean bool = false;
    try{
      model.toggleCellEliminated(10,10);
    }
    catch(RuntimeException e){
      bool = true;
    }
    assertTrue(bool);
  }
  @Test
  public void assertToggle(){
    PuzzleLibrary list1 =  new PuzzleLibrary();
    List<Clues> list = list1.create();
    Model model = new ModelImpl(list);
    Controller controller = new ControllerImpl(model);
    controller.getModel().toggleCellShaded(0,0);
    assertTrue(controller.isShaded(0,0));
  }
}
