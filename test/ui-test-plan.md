# UI Test Plan

This file is the source of truth for console UI tests run with the `$test-ui` skill. Each test case is one program command with its input and expected output. Add concrete test cases before running the skill. Expected output is matched exactly, except that Windows and Unix line endings are treated as equivalent.

## Test cases

### Test case: Add and list typed tasks

**Aim:** Verify that ToDos, Deadlines, and Events are created with the correct type marker and details, and that all three appear in the task list.

**Command:**

```text
sh -c 'rm -f _temp/ui-test-data.txt && exec ./gradlew --quiet --no-daemon --console=plain run -Djarvis.storage=_temp/ui-test-data.txt'
```

**Input:**

```text
todo borrow book
deadline submit report /by 2019-12-08
event project meeting /from 2019-12-09 1400 /to 2019-12-09 1600
deadline do homework /by 2/12/2019 1800
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
   [D][ ] submit report (by: Dec 08 2019)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Dec 09 2019 14:00 to: Dec 09 2019 16:00)
 Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] do homework (by: Dec 02 2019 18:00)
 Now you have 4 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] borrow book
 2.[D][ ] submit report (by: Dec 08 2019)
 3.[E][ ] project meeting (from: Dec 09 2019 14:00 to: Dec 09 2019 16:00)
 4.[D][ ] do homework (by: Dec 02 2019 18:00)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Test case: Parse and display dates and times

**Aim:** Verify that ISO dates, day/month/year dates, and date-time inputs are stored as typed values and displayed in a readable format.

**Command:**

```text
sh -c 'rm -f _temp/level8-test-data.txt && exec ./gradlew --quiet --no-daemon --console=plain run -Djarvis.storage=_temp/level8-test-data.txt'
```

**Input:**

```text
deadline return book /by 2/12/2019 1800
event project meeting /from 2019-10-15 1400 /to 2019-10-15 1600
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
   [D][ ] return book (by: Dec 02 2019 18:00)
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Oct 15 2019 14:00 to: Oct 15 2019 16:00)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[D][ ] return book (by: Dec 02 2019 18:00)
 2.[E][ ] project meeting (from: Oct 15 2019 14:00 to: Oct 15 2019 16:00)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Test case: Save tasks and load them after restarting

**Aim:** Verify that tasks and completion status are written to disk and restored when Jarvis starts again. The command creates a clean temporary save file, runs a first session, then starts a second session using that file.

**Command:**

```text
sh -c 'rm -f _temp/level7-test-data.txt; printf "todo persisted task\\ndeadline submit report /by 2019-12-06\\nevent planning /from 2019-12-07 0900 /to 2019-12-07 1000\\nmark 2\\nbye\\n" | ./gradlew --quiet --no-daemon --console=plain run -Djarvis.storage=_temp/level7-test-data.txt >/dev/null; exec ./gradlew --quiet --no-daemon --console=plain run -Djarvis.storage=_temp/level7-test-data.txt'
```

**Input:**

```text
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
 Here are the tasks in your list:
 1.[T][ ] persisted task
 2.[D][X] submit report (by: Dec 06 2019)
 3.[E][ ] planning (from: Dec 07 2019 09:00 to: Dec 07 2019 10:00)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Test case: Handle invalid input without crashing

**Aim:** Verify that empty, unknown, malformed, and out-of-range commands produce user-friendly errors and that Jarvis continues accepting commands.

**Command:**

```text
sh -c 'rm -f _temp/ui-test-data.txt && exec ./gradlew --quiet --no-daemon --console=plain run -Djarvis.storage=_temp/ui-test-data.txt'
```

**Input:**

```text
todo
blah
deadline return book
event meeting /from Monday
deadline invalid date /by 2019-02-30
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
 OOPS!!! Use a date such as 2019-10-15 or a date and time such as 2/12/2019 1800.
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

### Test case: Delete a task and keep list numbering contiguous

**Aim:** Verify that `delete` removes the selected task, reports the removed task and new count, and shifts later tasks into contiguous list positions. This also checks that tasks stored in the `ArrayList` retain their types and done status.

**Command:**

```text
sh -c 'rm -f _temp/ui-test-data.txt && exec ./gradlew --quiet --no-daemon --console=plain run -Djarvis.storage=_temp/ui-test-data.txt'
```

**Input:**

```text
todo first task
deadline second task /by 2019-12-13
event third task /from 2019-12-14 1400 /to 2019-12-14 1600
mark 2
delete 2
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
   [T][ ] first task
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] second task (by: Dec 13 2019)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] third task (from: Dec 14 2019 14:00 to: Dec 14 2019 16:00)
 Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Nice! I've marked this task as done:
   [D][X] second task (by: Dec 13 2019)
____________________________________________________________
____________________________________________________________
 Noted. I've removed this task:
   [D][X] second task (by: Dec 13 2019)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] first task
 2.[E][ ] third task (from: Dec 14 2019 14:00 to: Dec 14 2019 16:00)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Test case: Find tasks by keyword

**Aim:** Verify that `find` matches partial descriptions without regard to case, preserves the original task numbers, and reports when there are no matches.

**Command:**

```text
sh -c 'rm -f _temp/level9-test-data.txt && exec ./gradlew --quiet --no-daemon --console=plain run -Djarvis.storage=_temp/level9-test-data.txt'
```

**Input:**

```text
todo read book
deadline return book /by 2019-12-06
event planning /from 2019-12-07 0900 /to 2019-12-07 1000
find BOOK
find xyz
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
   [T][ ] read book
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Dec 06 2019)
 Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
 Got it. I've added this task:
   [E][ ] planning (from: Dec 07 2019 09:00 to: Dec 07 2019 10:00)
 Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][ ] read book
 2.[D][ ] return book (by: Dec 06 2019)
____________________________________________________________
____________________________________________________________
 Here are the matching tasks in your list:
 No matching tasks found.
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

### Test case: Run the executable JAR

**Aim:** Verify that Gradle creates an executable JAR with Jarvis configured as its entry point, and that the packaged application still accepts console commands.

**Command:**

```text
sh -c 'rm -f _temp/jar-test-data.txt && ./gradlew --quiet --no-daemon --console=plain jar >/dev/null && exec java -Dstdout.encoding=UTF-8 -Dfile.encoding=UTF-8 -Djarvis.storage=_temp/jar-test-data.txt -jar build/libs/jarvis.jar'
```

**Input:**

```text
todo packaged task
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
   [T][ ] packaged task
 Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] packaged task
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```
