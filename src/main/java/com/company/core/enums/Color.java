package com.company.core.enums;

public enum Color {
    BLACK("Черный"), WHITE("Белый"), RED("Красный"), GRAY("Серый");

    private String rusColorName;

    Color(String rusColorName) {
        this.rusColorName = rusColorName;
    }

    @Override
    public String toString() {
        return rusColorName;
    }
}
