chcp 65001

 ..\gradlew :event-sourcing:clean :event-sourcing:build && java -cp build\classes\java\main;build\resources\main -Dfile.encoding=UTF-8 com.link_intersystems.es.chess.adapter.app.ChessApp