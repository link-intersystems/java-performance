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
 */
public class ChessApp {
    public static void main(String[] args) {

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

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
