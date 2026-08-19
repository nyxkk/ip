# UI Test Plan

This file is the source of truth for console UI tests run with the `$test-ui` skill. Each test case is one program command with its input and expected output. Add concrete test cases before running the skill. Expected output is matched exactly, except that Windows and Unix line endings are treated as equivalent.

## Test cases

### Test case: Add and list typed tasks

**Aim:** Verify that ToDos, Deadlines, and Events are created with the correct type marker and details, and that all three appear in the task list.

**Command:**

```text
java -Dstdout.encoding=UTF-8 -Dfile.encoding=UTF-8 -cp out Jarvis
```

**Input:**

```text
todo borrow book
deadline submit report /by Sunday
event project meeting /from Monday 2pm /to 4pm
deadline do homework /by no idea :-p
list
bye
```

**Expected output:**

```text
____________________________________________________________
    ██╗ █████╗ ██████╗ ██╗   ██╗██╗███████╗
     ██║██╔══██╗██╔══██╗██║   ██║██║██╔════╝
     ██║███████║██████╔╝██║   ██║██║███████╗
██   ██║██╔══██║██╔══██╗╚██╗ ██╔╝██║╚════██║
╚█████╔╝██║  ██║██║  ██║ ╚████╔╝ ██║███████║
 ╚════╝ ╚═╝  ╚═╝╚═╝  ╚═╝  ╚═══╝  ╚═╝╚══════╝
Hello! I'm Jarvis.
What can I do for you?
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] borrow book
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] submit report (by: Sunday)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Monday 2pm to: 4pm)
 Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] do homework (by: no idea :-p)
 Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] borrow book
 2.[D][ ] submit report (by: Sunday)
 3.[E][ ] project meeting (from: Monday 2pm to: 4pm)
 4.[D][ ] do homework (by: no idea :-p)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Test case: Handle invalid input without crashing

**Aim:** Verify that empty, unknown, malformed, and out-of-range commands produce user-friendly errors and that Jarvis continues accepting commands.

**Command:**

```text
java -Dstdout.encoding=UTF-8 -Dfile.encoding=UTF-8 -cp out Jarvis
```

**Input:**

```text
todo
blah
deadline return book
event meeting /from Monday
mark 1
todo buy milk
mark 2
bye
```

**Expected output:**

```text
____________________________________________________________
    ██╗ █████╗ ██████╗ ██╗   ██╗██╗███████╗
     ██║██╔══██╗██╔══██╗██║   ██║██║██╔════╝
     ██║███████║██████╔╝██║   ██║██║███████╗
██   ██║██╔══██║██╔══██╗╚██╗ ██╔╝██║╚════██║
╚█████╔╝██║  ██║██║  ██║ ╚████╔╝ ██║███████║
 ╚════╝ ╚═╝  ╚═╝╚═╝  ╚═╝  ╚═══╝  ╚═╝╚══════╝
Hello! I'm Jarvis.
What can I do for you?
____________________________________________________________
____________________________________________________________
 OOPS!!! The description of a todo cannot be empty.
____________________________________________________________
____________________________________________________________
 OOPS!!! I'm sorry, but I don't know what that means.
____________________________________________________________
____________________________________________________________
 OOPS!!! A deadline must include /by followed by a date or time.
____________________________________________________________
____________________________________________________________
 OOPS!!! An event must include /from and /to times.
____________________________________________________________
____________________________________________________________
 OOPS!!! That task number does not exist.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [T][ ] buy milk
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 OOPS!!! That task number does not exist.
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

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
