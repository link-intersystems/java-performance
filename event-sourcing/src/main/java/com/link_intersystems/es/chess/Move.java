package com.link_intersystems.es.chess;

import java.util.Optional;

public class Move {

    public static interface MoveFrom {

        Move to(Board.Row toRow, Board.Column toColumn);
    }

    public static MoveFrom from(Board.Row fromRow, Board.Column fromColumn) {
        return new MoveFrom() {
            @Override
            public Move to(Board.Row toRow, Board.Column toColumn) {
                return new Move(fromRow, fromColumn, toRow, toColumn);
            }
        };
    }

    private final Board.Row fromRow;
    private final Board.Column fromColumn;
    private final Board.Row toRow;
    private final Board.Column toColumn;

    public Move(Board.Row fromRow, Board.Column fromColumn, Board.Row toRow, Board.Column toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }

    /**
     * @return the piece that was removed from the board due to this {@link Move}.
     */
    public Optional<Piece> apply(Board board) {
        Optional<Piece> optionalPiece = board.getPiece(fromRow, fromColumn);
        return optionalPiece.map(piece -> board.setPiece(toRow, toColumn, piece).orElse(null));
    }
}
