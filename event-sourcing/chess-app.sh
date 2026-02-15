#!/usr/bin/env bash

export LANG=en_US.UTF-8

 ../gradlew :event-sourcing:clean :event-sourcing:build

java -cp build/classes/java/main/:build/resources/main/ -Dfile.encoding=UTF-8 com.link_intersystems.es.chess.adapter.app.ChessApp