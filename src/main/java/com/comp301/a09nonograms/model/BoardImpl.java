package com.comp301.a09nonograms.model;

public class BoardImpl implements Board{
    public State[][] _board;
    private int _rowLength;
    private int _colLength;

    public BoardImpl(int rowLength, int colLength){
        _rowLength = rowLength;
        _colLength = colLength;
        _board = new State[_rowLength][_colLength];
        for (int i=0; i<rowLength; i++){
            for (int j=0; j<colLength; j++){
                _board[i][j] = State.SPACE;
            }
        }
    }
    @Override
    public boolean isShaded(int row, int col) {
        if (row > _rowLength || col > _colLength){
            throw new RuntimeException();
        }
        return _board[row][col] ==  State.SHADED;
    }

    @Override
    public boolean isEliminated(int row, int col) {
        if (row > _rowLength || col > _colLength){
            throw new RuntimeException();
        }
        return _board[row][col] ==  State.ELIMINATED;
    }

    @Override
    public boolean isSpace(int row, int col) {
        if (row > _rowLength || col > _colLength){
            throw new RuntimeException();
        }
        return _board[row][col] ==  State.SPACE;
    }

    @Override
    public void toggleCellShaded(int row, int col) {
        if (row > _rowLength || col > _colLength){
            throw new RuntimeException();
        }
        if (_board[row][col] == State.SPACE){
            _board[row][col] = State.SHADED;
        }
        else{
            _board[row][col] = State.SPACE;
        }
    }

    @Override
    public void toggleCellEliminated(int row, int col) {
        if (row > _rowLength || col > _colLength){
            throw new RuntimeException();
        }
        if (_board[row][col] == State.ELIMINATED){
            _board[row][col] = State.SPACE;
        }
        else{
            _board[row][col] = State.ELIMINATED;
        }

    }

    @Override
    public void clear() {
        for (int i=0; i<_rowLength; i++){
            for (int j=0; j<_colLength; j++){
                _board[i][j] = State.SPACE;
            }
        }
    }
    public State[][] getBoard(){
        return _board;
    }
}
