package jarvis;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Owns Jarvis' tasks and the operations that change the task list. */
public class TaskList {
    private final ArrayList<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing the supplied tasks.
     *
     * @param tasks the initial tasks to copy into this list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at a one-based position, or an input error if it is absent.
     *
     * @param oneBasedPosition the task position starting at one
     * @return the task at that position
     * @throws JarvisException if the position is outside the list
     */
    public Task get(int oneBasedPosition) {
        return tasks.get(toIndex(oneBasedPosition));
    }

    /**
     * Removes and returns the task at a one-based position.
     *
     * @param oneBasedPosition the task position starting at one
     * @return the removed task
     * @throws JarvisException if the position is outside the list
     */
    public Task remove(int oneBasedPosition) {
        return tasks.remove(toIndex(oneBasedPosition));
    }

    /**
     * Returns the number of tasks currently stored.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a read-only view for display and persistence.
     *
     * @return an unmodifiable snapshot of the tasks
     */
    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    /**
     * Returns one-based positions of tasks whose descriptions contain a keyword.
     *
     * @param keyword the text to search for
     * @return the matching one-based task positions
     */
    public List<Integer> find(String keyword) {
        String normalizedKeyword = keyword.toLowerCase(Locale.ROOT);
        ArrayList<Integer> matchingPositions = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).getDescription().toLowerCase(Locale.ROOT);
            if (description.contains(normalizedKeyword)) {
                matchingPositions.add(i + 1);
            }
        }
        return List.copyOf(matchingPositions);
    }

    private int toIndex(int oneBasedPosition) {
        if (oneBasedPosition < 1 || oneBasedPosition > tasks.size()) {
            throw new JarvisException("That task number does not exist.");
        }
        return oneBasedPosition - 1;
    }
}
