package com.link_intersystems.es.chess.adapter.ui;

import com.link_intersystems.es.chess.Board;
import com.link_intersystems.es.chess.Piece;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsciiBoard {

    public static final String PLAIN_BOARD = """
            ╔═╤══╤══╤══╤══╤══╤══╤══╤══╤═╗
            ║ │A │B │C │D │E │F │G │H │ ║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║1│  │  │  │  │  │  │  │  │1║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║2│  │  │  │  │  │  │  │  │2║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║3│  │  │  │  │  │  │  │  │3║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║4│  │  │  │  │  │  │  │  │4║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║5│  │  │  │  │  │  │  │  │5║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║6│  │  │  │  │  │  │  │  │6║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║7│  │  │  │  │  │  │  │  │7║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║8│  │  │  │  │  │  │  │  │8║
            ╟─┼──┼──┼──┼──┼──┼──┼──┼──┼─╢
            ║ │A │B │C │D │E │F │G │H │ ║
            ╚═╧══╧══╧══╧══╧══╧══╧══╧══╧═╝
            """;
    public static final String THIN_SPACE = "\u2009";

    private Pattern cellDevider = Pattern.compile("│[^A-H0-8│]+│");
    private String asciiBoard = PLAIN_BOARD;;

    public void set(Board.Row row, Board.Column column, Piece piece) {
        int rowIndex = row.ordinal();
        int columnIndex = column.ordinal();
        setCell(rowIndex, columnIndex, piece);

    }

    private void setCell(int rowIndex, int columnIndex, Piece piece) {
        int cellIndex = rowIndex + (columnIndex * 8);

        Matcher matcher = cellDevider.matcher(asciiBoard);
        int nextSearchIndex = 0;
        for (int i = 0; i <= cellIndex; i++) {
            if (matcher.find(nextSearchIndex)) {
                nextSearchIndex = matcher.start() + 1;
            }
        }
        int start = matcher.start();
        String before = asciiBoard.substring(0, start + 1);
        String after = asciiBoard.substring(matcher.end() - 1);
        asciiBoard = before + toAscii(piece) + THIN_SPACE + after;
    }

    private String toAscii(Piece piece) {
        return switch (piece.getType()) {
            case KING -> switch (piece.getColor()) {
                case WHITE -> "♚";
                case BLACK -> "♔";
            };
            case QUEEN -> switch (piece.getColor()) {
                case WHITE -> "♛";
                case BLACK -> "♕";
            };
            case ROOK -> switch (piece.getColor()) {
                case WHITE -> "♜";
                case BLACK -> "♖";
            };
            case BISHOP -> switch (piece.getColor()) {
                case WHITE -> "♝";
                case BLACK -> "♗";
            };
            case KNIGHT -> switch (piece.getColor()) {
                case WHITE -> "♞";
                case BLACK -> "♘";
            };
            case PAWN -> switch (piece.getColor()) {
                case WHITE -> "♟";
                case BLACK -> "♙";
            };
        };
    }

    @Override
    public String toString() {
        return asciiBoard;
    }

    public void apply(Board board) {
        asciiBoard = PLAIN_BOARD;
        Arrays.stream(Board.Row.values()).forEach(row -> {
            Arrays.stream(Board.Column.values()).forEach(column -> {
                Optional<Piece> field = board.getPiece(row, column);
                field.ifPresent(piece -> set(row, column, piece));
            });
        });
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void print(PrintStream out) {
        clearScreen();
        out.println(asciiBoard);
    }
}
