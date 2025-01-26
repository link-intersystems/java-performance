This repository contains automated jmeter tests that show how different things in java perform.

Since my beginning in software development I'm facing a lot of discussions about performance.
Sometimes these discussions are quite useful, but often a lot of discussions are only based
on assumptions, not facts. Thus, I saw a lot of so-called performance improvements that were
not helpful. Most of this "Improvements" made the code more complex, less readable, and usually did
not lead to a measurable performance improvement. In my opinion one should measure, not guess. 
Everything else is not professional. 

Thus, I always go through 4 steps when dealing with performance issues:
**Measure -> Analyse -> Improve -> Measure again**.
Beside these steps you also have to make sure that no other actions infer with your tests and
make your measurements inaccurate.

I created this repository to 

- shorten discussions about performance in Java. Run the tests, look at the results and make a decision.
- have a basis for implementing further performance tests to shorten discussions.


## Run the benchmark tests

### Command Line

Use maven to build all `benchmarks.jar` archives.

```shell
./mvnw package
```

Run some `benchmarks.jar` with the options you like: 

```shell
java -jar mapping/target/benchmarks.jar -f 1 -i 2 -r 5 -wi 2 -w 5
java -jar serialization/target/benchmarks.jar -f 1 -i 2 -r 5 -wi 2 -w 5
```

or just run

```shell
./mvnw integration-test
```

### IntelliJ IDEA

Run configurations are stored in the root directory `.run`. So
IDEA should automatically detect them.

Otherwise, you can either use IDEA run shell script feature to directly
execute th