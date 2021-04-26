package com.comp301.a09nonograms.model;

public class CluesImpl implements Clues{
    private int[][] _rowClues;
    private int[][] _colClues;

    public CluesImpl(int[][] rowClues, int[][] colClues){
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
        int sum =0;
        for (int i=0; i<_rowClues.length;i++){
            for (int j=0; j<_rowClues[0].length;j++){
                sum += _rowClues[i][j];
            }
        }
        return sum;
    }

    @Override
    public int getColCluesLength() {
        int sum =0;
        for (int i=0; i<_colClues.length;i++){
            for (int j=0; j<_colClues[0].length;j++){
                sum += _rowClues[i][j];
            }
        }
        return sum;
    }
}
