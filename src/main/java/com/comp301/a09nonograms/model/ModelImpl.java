package com.comp301.a09nonograms.model;

import java.util.*;

public class ModelImpl implements Model{
    private List<Clues> _clues;
    private Puzzles _puzzleMap;
    private int _puzzleIndex;
    private List<ModelObserver> _activeObservers;

    public ModelImpl(List<Clues> clues){
        _puzzleMap = new Puzzles(clues);
        _clues = clues;
        _puzzleIndex = 0;
        _activeObservers = new ArrayList<>();
    }

    @Override
    public int getPuzzleCount() {
        return _clues.size();
    }

    @Override
    public int getPuzzleIndex() {
        return _puzzleIndex;
    }

    @Override
    public void setPuzzleIndex(int index) {
        if (index < 0 || index > this.getPuzzleCount()){
            throw new IllegalArgumentException();
        }
        _puzzleIndex = index;
        for (ModelObserver o: _activeObservers){
            o.update(this);
        }
    }

    public CluesImpl getClues(){
        return (CluesImpl) _puzzleMap.getClues(_clues.get(this.getPuzzleIndex()));
    }
    @Override
    public void addObserver(ModelObserver observer) {
        _activeObservers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        _activeObservers.remove(observer);
    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isShaded(int row, int col) {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return _puzzleMap.getBoard(clue).isShaded(row,col);
    }

    @Override
    public boolean isEliminated(int row, int col) {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return _puzzleMap.getBoard(clue).isEliminated(row,col);
    }

    @Override
    public boolean isSpace(int row, int col) {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return _puzzleMap.getBoard(clue).isSpace(row,col);
    }

    @Override
    public void toggleCellShaded(int row, int col) {
        Clues clue = _clues.get(this.getPuzzleIndex());
        _puzzleMap.getBoard(clue).toggleCellShaded(row,col);
        for (ModelObserver o: _activeObservers){
            o.update(this);
        }
    }

    @Override
    public void toggleCellEliminated(int row, int col) {
        Clues clue = _clues.get(this.getPuzzleIndex());
        _puzzleMap.getBoard(clue).toggleCellEliminated(row,col);
        for (ModelObserver o: _activeObservers){
            o.update(this);
        }
    }

    @Override
    public void clear() {
        Clues clue = _clues.get(this.getPuzzleIndex());
        _puzzleMap.getBoard(clue).clear();
        for (ModelObserver o: _activeObservers){
            o.update(this);
        }
    }

    @Override
    public int getWidth() {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return clue.getWidth();
    }

    @Override
    public int getHeight() {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return clue.getHeight();
    }

    @Override
    public int[] getRowClues(int index) {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return clue.getRowClues(index);
    }

    @Override
    public int[] getColClues(int index) {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return clue.getColClues(index);
    }

    @Override
    public int getRowCluesLength() {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return clue.getRowCluesLength();
    }

    @Override
    public int getColCluesLength() {
        Clues clue = _clues.get(this.getPuzzleIndex());
        return clue.getColCluesLength();
    }
}
