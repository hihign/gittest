package com.powernode.spring6.dao;

import java.util.List;
import java.util.Set;

public class Student {
    private List<String> pens;
    private Set<String> books;

    @Override
    public String toString() {
        return "Student{" +
                "pens=" + pens +
                ", books=" + books +
                '}';
    }

    public void setPens(List<String> pens) {
        this.pens = pens;
    }

    public void setBooks(Set<String> books) {
        this.books = books;
    }
}
