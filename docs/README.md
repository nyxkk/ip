# Jarvis User Guide

Jarvis is a desktop chatbot that helps you record and manage tasks.

## Finding tasks

Use `find <terms>` to display tasks whose descriptions contain every search
term. Matching is case-insensitive, and each term can match part of a word.
The terms may appear in any order in the task description.

For example:

```text
find BOO urn
```

This finds a task such as `return book` because `BOO` partially matches
`book`, and `urn` partially matches `return`. Matching tasks retain their
original task numbers. Jarvis reports `No matching tasks found.` when no task
contains all the terms.
