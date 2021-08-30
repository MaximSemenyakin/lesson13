package ru.geekbarins;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {

    protected ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stage) {
        this.stages = new ArrayList<>(Arrays.asList(stage));
    }
}
