package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues {
  private int[][] _rowClues;
  private int[][] _colClues;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    if (rowClues == null || colClues == null) {
      throw new IllegalArgumentException();
    }
    _rowClues = rowClues;
    _colClues = colClues;
  }

  @Override
  public int getWidth() {
    return _colClues.length;
  }

  @Override
  public int getHeight() {
    return _rowClues.length;
  }

  @Override
  public int[] getRowClues(int index) {
    return _rowClues[index];
  }

  @Override
  public int[] getColClues(int index) {
    return _colClues[index];
  }

  @Override
  public int getRowCluesLength() {

    return _rowClues[0].length;
  }

  @Override
  public int getColCluesLength() {
    return _colClues[0].length;
  }
}
