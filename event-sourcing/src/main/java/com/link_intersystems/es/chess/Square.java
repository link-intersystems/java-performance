package com.link_intersystems.es.chess;

import java.util.Objects;

public class Square {

    private Board.Row row;
    private Board.Column column;
    
    Square(Board.Row row, Board.Column column) {
        this.row = Objects.requireNonNull(row);
        this.column = Objects.requireNonNull(column);
    }

    public Board.Column getColumn() {
        return column;
    }

    public Board.Row getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return row == square.row && column == square.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
