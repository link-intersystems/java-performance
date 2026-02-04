# Run the benchmark tests

## Command Line

Use maven to build the `benchmarks.jar`

```shell
../mvnw package
```

Run the `benchmarks.jar` with the options you like

```shell
java -jar target/benchmarks.jar -f 1 -i 2 -r 5 -wi 2 -w 5
```

or just run

```shell
../mvnw integration-test
```

## IntelliJ IDEA

Run configurations are stored in the root directory `.run`. So
IDEA should automatically detect them.

Otherwise, you can either use IDEA run shell script feature to directly
execute the command lines above from the markdown preview.