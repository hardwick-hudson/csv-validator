# csv-validator

A small Java CLI for validating CSV files against per-column rules. 
Point it at a file, declare what each column should contain, and get a
row-by-row failure report with script-friendly exit codes.

## Usage

```
csv-validator <file.csv> <colIndex>:<rule>[:<param>...] [more rule specs...]
```

Columns are zero-indexed. The first row is treated as a header and is not 
validated. Multiple rules can target the same column.

### Rules

| Rule | Spec | Passes when |
|---|---|---|
| notnull | `0:notnull` | cell is present and not blank |
| int | `1:int` | cell parses as an integer |
| range | `1:range:0:120` | cell is an integer between min and max (inclusive) |
| regex | `2:regex:^[A-Z].*` | cell matches the pattern |

### Example
```
$ csv-validator invalid.csv 0:notnull 1:int 1:range:0:120

row 2, col 1: "abc" failed IsIntegerRule
row 2, col 1: "abc" failed InRangeRule
row 3, col 0: "" failed NotNullRule
row 4, col 1: "150" failed InRangeRule
2/5 rows passed
```

Rows in the report are data-row indices (row 1 being the first row after the 
header). A row with multiple failing cells counts once toward the fail count
but every failing cell is listed.

### Exit codes

| Code | Meaning |
|---|---|
| 0 | all rows passed |
| 1 | validation failures found |
| 2 | user error — bad arguments, unknown rule, unreadable file |

Nonzero-on-failure means it drops into scripts and CI directly:

```
csv-validator export.csv 0:notnull 2:int || echo "bad data, stopping"
```

## Building and running

Requires Java 21+

```
./gradlew test          # run the test suite
./gradlew run --args="src/test/resources/test.cvs 1:int"
./gradlew installDist   # build a standalone launcher
./build/install/csv-validator/bin/csv-validator <file.csv> <rules...>
```

## Design notes

- Rules implement a single method `Rule` interface (`boolean check(String value)`), 
  so adding a rule type is one class plus its tests
- Rules fail fast on bad config: so something like  `range:10:2` throws 
  at construction
- Missing cells (short rows) are passed to rules as `null`, and every rule
  treats `null` as a failure.
- The CSV reader is deliberately minimal: it splits on commas and does not 
  handle quoted fields containing commas. Fine for machine-generated CSVs; 
  not a general-purpose parser. See Below.

## Status and future work

This is v1 which is complete for its scope: rule-based validation of simple CSVs
with script-friendly output.

Planned for v1.1:
- Quote-aware parsing in `CsvReader` (fields like `"Smith, John"`), handled by 
  a small state machine rather than an external parsing library. The `read()` 
  signature won't change, so nothing downstream is affected. 
