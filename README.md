This repository contains performance tests for Java using Java Microbenchmark Harness (JMH).

Since my beginning in software development I'm facing a lot of discussions about performance.
Sometimes these discussions are quite useful, but often a lot of discussions are only based
on assumptions, not facts. Thus, I saw a lot of so-called performance improvements that were
not helpful. Most of these "improvements" made the code more complex, less readable, and usually did
not lead to a measurable performance improvement. In my opinion, one should measure, not guess.
Everything else is not professional.

Thus, I always go through 4 steps when dealing with performance issues:
**Measure -> Analyse -> Improve -> Measure again**.
Besides these steps, you also have to make sure that no other actions that infer with your tests and
make your measurements inaccurate.

I created this repository to

- shorten discussions about performance in Java. Run the tests, look at the results and make a decision.
- have a basis for implementing further performance tests to shorten discussions.

## Run the benchmark tests

### Command Line

Run all benchmarks:

```shell
./gradlew jmh
```

Build benchmark jar archives:

```shell
./gradlw jmhJar
``` 

The jar archives are stored in the `build/libs` directories.

Run the benchmark archives with other options:

```shell
java -jar mapping/build/libs/java-performance-mapping-0.0.1-SNAPSHOT-jmh.jar -f 1 -i 2 -r 5 -wi 2 -w 5
```

### IntelliJ IDEA

Run configurations are stored in the root directory `.run`. So
IDEA should automatically detect them. You need
the https://plugins.jetbrains.com/plugin/7529-jmh-java-microbenchmark-harness
plugin installed.