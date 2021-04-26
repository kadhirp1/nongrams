package com.comp301.a09nonograms;

import com.comp301.a09nonograms.model.*;
import org.junit.Test;

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



}
