package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.CluesImpl;
import com.comp301.a09nonograms.model.Model;

import java.util.Random;

public class ControllerImpl implements Controller{
    private Model _model;

    public ControllerImpl(Model model){
        _model = model;
    }
    @Override
    public Model getModel(){
        return _model;
    }
    @Override
    public Clues getClues() {
        int[][] newRow = null;
        int[][] newCol = null;
        for (int i=0; i<_model.getWidth(); i++){
            newRow[i] = _model.getRowClues(i);
        }
        for (int i=0; i<_model.getHeight(); i++){
            newCol[i] = _model.getColClues(i);
        }
        Clues retClues = new CluesImpl(newRow,newCol);
        return retClues;
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isShaded(int row, int col) {
        return _model.isShaded(row,col);
    }

    @Override
    public boolean isEliminated(int row, int col) {
        return _model.isEliminated(row,col);
    }

    @Override
    public void toggleShaded(int row, int col) {
        _model.toggleCellShaded(row,col);
    }

    @Override
    public void toggleEliminated(int row, int col) {
        _model.toggleCellEliminated(row,col);
    }

    @Override
    public void nextPuzzle() {
        if(_model.getPuzzleIndex() < _model.getPuzzleCount()){
            _model.setPuzzleIndex(_model.getPuzzleIndex() + 1);
        }
    }

    @Override
    public void prevPuzzle() {
        if(_model.getPuzzleIndex() > 0){
            _model.setPuzzleIndex(_model.getPuzzleIndex() - 1);
        }
    }

    @Override
    public void randPuzzle() {
        Random randomObj = new Random();
        int randPuzzleIndex = randomObj.nextInt(_model.getPuzzleCount());
        _model.setPuzzleIndex(randPuzzleIndex);
    }

    @Override
    public void clearBoard() {
        _model.clear();
    }

    @Override
    public int getPuzzleIndex() {
        return _model.getPuzzleIndex();
    }

    @Override
    public int getPuzzleCount() {
        return _model.getPuzzleCount();
    }
}
