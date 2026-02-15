package com.link_intersystems.es.chess;

import com.link_intersystems.es.chess.adapter.presistence.ResourceMoveRepository;
import org.openjdk.jmh.annotations.*;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ChessEventsBenchmark {

    @State(Scope.Benchmark)
    public static class EventState {
        private List<Move> moves;

        @Setup(Level.Iteration)
        public void setUp() {
            MoveRepository moveRepository = new ResourceMoveRepository();
            moves = moveRepository.getMoves(2).toList();
        }
    }

    @Benchmark
    @Fork(value = 1)
    @Warmup(iterations = 2, time = 5, timeUnit = SECONDS)
    @Measurement(iterations = 2, time = 5, timeUnit = SECONDS)
    public Board reconstitueBoard(EventState state) {
        Board board = new Board();
        state.moves.forEach(move -> move.apply(board));
        return board;
    }
}
