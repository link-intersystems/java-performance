package com.link_intersystems.es.chess;

import static java.util.Objects.requireNonNull;

public class Piece {

    public static enum Type {
        KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN;
    }

    public static enum Color {
        WHITE, BLACK;
    }

    private final Color color;
    private final Type type;

    public Piece(Color color, Type type) {
        this.color = requireNonNull(color);
        this.type = requireNonNull(type);
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Piece{" + color + " " + type + '}';
    }
}
