package com.link_intersystems.es.chess;

import java.util.Arrays;

import static com.link_intersystems.es.chess.Board.Column.*;
import static com.link_intersystems.es.chess.Board.Row.*;
import static com.link_intersystems.es.chess.Piece.Color.BLACK;
import static com.link_intersystems.es.chess.Piece.Color.WHITE;
import static com.link_intersystems.es.chess.Piece.Type.*;

public class ChessBoardSetup {

    public void apply(Board board) {
        initWhite(board);
        initBlack(board);
    }

    private void initWhite(Board board) {
        setMainRow(board, _1, WHITE);
        setPawnRow(board, _2, WHITE);
    }

    private void initBlack(Board board) {
        setMainRow(board, _8, BLACK);
        setPawnRow(board, _7, BLACK);
    }

    private void setMainRow(Board board, Board.Column column, Piece.Color color) {
        board.setPiece(A, column, new Piece(color, ROOK));
        board.setPiece(B, column, new Piece(color, KNIGHT));
        board.setPiece(C, column, new Piece(color, BISHOP));
        board.setPiece(D, column, new Piece(color, QUEEN));
        board.setPiece(E, column, new Piece(color, KING));
        board.setPiece(F, column, new Piece(color, BISHOP));
        board.setPiece(G, column, new Piece(color, KNIGHT));
        board.setPiece(H, column, new Piece(color, ROOK));
    }

    private void setPawnRow(Board board, Board.Column column, Piece.Color color) {
        Arrays.stream(Board.Row.values()).forEach(row -> board.setPiece(row, column, new Piece(color, PAWN)));
    }
}
