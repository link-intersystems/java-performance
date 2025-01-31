package com.link_intersystems.es.chess;

import java.util.stream.Stream;

public interface MoveRepository {


    public Stream<Move> getMoves(int gameId);
}
