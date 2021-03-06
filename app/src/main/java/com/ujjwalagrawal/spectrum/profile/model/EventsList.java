package com.ujjwalagrawal.spectrum.profile.model;

/**
 * Created by nosta on 16-01-2018.
 */

public class EventsList {
    private int type ;
    private int id;
    private String name;
    private int participated;

    public EventsList(int type, int id, String name, int participated) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.participated = participated;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParticipated() {
        return participated;
    }
}
