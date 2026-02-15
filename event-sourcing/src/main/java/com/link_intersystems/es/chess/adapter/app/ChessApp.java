package com.link_intersystems.es.chess.adapter.app;

import com.link_intersystems.es.chess.Board;
import com.link_intersystems.es.chess.ChessBoardSetup;
import com.link_intersystems.es.chess.MoveRepository;
import com.link_intersystems.es.chess.Piece;
import com.link_intersystems.es.chess.adapter.presistence.ResourceMoveRepository;
import com.link_intersystems.es.chess.adapter.ui.AsciiBoard;

import java.util.Optional;

/**
 * Run with java -Dfile.encoding=UTF-8 in a shell that supports UTF-8.
 * <p>
 * You can also use the chess-app scripts in this project.
 */
public class ChessApp {
    public static void main(String[] args) {

        System.out.println("""
                When running this application you must ensure that your terminal supports UTF-8!
                
                On Windows CMD: chcp 65001
                On Linux: export LANG=en_US.UTF-8
                """);

        pressEnterToContinue();

        int gameId = 1;
        if (args.length > 0) {
            gameId = Integer.parseInt(args[0]);
        }


        Board board = new Board();
        ChessBoardSetup chessBoardSetup = new ChessBoardSetup();
        chessBoardSetup.apply(board);

        AsciiBoard asciiBoard = new AsciiBoard();
        asciiBoard.apply(board);
        asciiBoard.print(System.out);

        MoveRepository moveRepository = new ResourceMoveRepository();

        moveRepository.getMoves(gameId).forEach(move -> {
            sleep();

            Optional<Piece> removedPiece = move.apply(board);


            asciiBoard.apply(board);
            asciiBoard.print(System.out);

            removedPiece.ifPresent(piece -> {
                System.out.println("Removed: " + piece);
                if (piece.getType().equals(Piece.Type.KING)) {
                    throw new RuntimeException("GAME OVER!");
                }
            });


        });

    }

    private static void pressEnterToContinue() {
        System.out.println("Press Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
