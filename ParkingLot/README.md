# Parking Lot LLD Demo

This project uses Java packages, so run with fully qualified class names.

## Compile

From project root:

```bash
javac $(find src -name "*.java")
```

## Run demo

From project root:

```bash
java src.com.example.parking_lots.Application
```

## Why your previous commands failed

- `java $(find . -name "*.java")` passes file names to `java`, but `java` expects a class name (or `-jar`), not source files.
- `java Application.java` uses single-file source mode, which does not work for this multi-file packaged project.
