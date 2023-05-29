package com.powernode.spring6.dao;

public class Drink {
    private String name;
    private int id;

    public Drink() {
    }

    public Drink(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

}
