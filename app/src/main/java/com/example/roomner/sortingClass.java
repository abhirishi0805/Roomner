package com.example.roomner;

import java.util.Comparator;

public class sortingClass implements Comparator<matchHelperModel> {
    @Override
    public int compare(matchHelperModel o1, matchHelperModel o2) {
        return o2.getPercentageMatch() - o1.getPercentageMatch();
    }
}
