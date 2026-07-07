package dev.hudson.validator;

import java.util.List;

public record Report(int totalRows, int passCount, int failCount, List<ValidationFailure> failures){}
