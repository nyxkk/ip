# UI Test Plan

This file is the source of truth for console UI tests run with the `$test-ui` skill. Each test case is one program command with its input and expected output. Add concrete test cases before running the skill. Expected output is matched exactly, except that Windows and Unix line endings are treated as equivalent.

## Test case template

### Test case: Replace with a short name

**Aim:** State the UI behavior this test verifies.

**Command:**

```text
# Command that launches the program
```

**Input:**

```text
# Exact text entered into the console, including each line
```

**Expected output:**

```text
# Exact console output produced by the program
```

## Test-session record format

When the skill is run, it should show this information in its response for every completed test case:

```text
Test case: <name>
Aim: <aim>
Console input:
<input>
Console output:
<output>
Result: PASS | FAIL
```

For a failure, the record must also include the expected output and the session must end without running remaining test cases.
