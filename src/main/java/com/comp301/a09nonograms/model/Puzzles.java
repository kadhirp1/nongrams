package com.comp301.a09nonograms.model;

import java.util.*;

public class Puzzles {
    private Clues _clues;
    private Board _board;
    private Map<Clues,Board> _map;

    public Puzzles(List<Clues> clues){
        _map = new HashMap<>();
        for (int i=0; i<clues.size();i++){
            Board tempBoard = new BoardImpl(clues.get(i).getWidth(),clues.get(i).getHeight());
            _map.put(clues.get(i),tempBoard);
        }
    }

    public Board getBoard(Clues clues){
        return _map.get(clues);
    }

    public Clues getClues(Clues clues){
        Clues retClue = null;
        for (Clues i:_map.keySet()){
            if (i == clues){
                retClue = i;
            }
        }
        return retClue;
    }

}
