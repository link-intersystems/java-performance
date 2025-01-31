package com.link_intersystems.es.chess.adapter.presistence;

import com.link_intersystems.es.chess.Board;
import com.link_intersystems.es.chess.adapter.app.ChessApp;
import com.link_intersystems.es.chess.Move;
import com.link_intersystems.es.chess.MoveRepository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.text.MessageFormat;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ResourceMoveRepository implements MoveRepository {
    @Override
    public Stream<Move> getMoves(int gameId) {
        String resource = MessageFormat.format("com/link_intersystems/es/chess/game{0}.csv", gameId);
        ResourceLineIterator resourceLineIterator = new ResourceLineIterator(ChessApp.class.getClassLoader(), resource);
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(resourceLineIterator, Spliterator.ORDERED),
                false).onClose(() -> {
            try {
                resourceLineIterator.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }).map(this::toMove);
    }

    private Move toMove(String moveLine) {
        String[] moveParts = moveLine.split(Pattern.quote(","));
        return Move.from(Board.Row.valueOf(moveParts[0]), Board.Column.valueOf("_" + moveParts[1]))
                .to(Board.Row.valueOf(moveParts[2]), Board.Column.valueOf("_" + moveParts[3]));
    }
}
