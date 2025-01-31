package com.link_intersystems.es.chess;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class Board {

    public static enum Row {
        A, B, C, D, E, F, G, H;

    }

    public static enum Column {
        _1, _2, _3, _4, _5, _6, _7, _8;

    }

    private Map<Piece, Square> piecePositions = new IdentityHashMap<>();

    public Board() {
    }

    public void clear() {
        piecePositions.clear();
    }

    public Optional<Piece> setPiece(Row row, Column column, Piece piece) {
        Square toSetPosition = new Square(row, column);
        Optional<Piece> removedPiece = removePieceAt(toSetPosition);
        piecePositions.put(piece, toSetPosition);
        return removedPiece;
    }

    private Optional<Piece> removePieceAt(Square square) {
        Optional<Piece> piece = getPiece(square);
        piece.ifPresent(piecePositions::remove);
        return piece;
    }

    public Optional<Piece> getPiece(Row row, Column column) {
        Square square = new Square(row, column);
        return getPiece(square);
    }

    public Optional<Piece> getPiece(Square square) {
        Iterator<Map.Entry<Piece, Square>> iterator = piecePositions.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Piece, Square> pieceSquareEntry = iterator.next();
            if (pieceSquareEntry.getValue().equals(square)) {
                return Optional.of(pieceSquareEntry.getKey());
            }
        }
        return Optional.empty();
    }
}
