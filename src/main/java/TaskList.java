import java.util.ArrayList;
import java.util.List;

/** Owns Jarvis' tasks and the operations that change the task list. */
public class TaskList {
    private final ArrayList<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /** Creates a task list containing the supplied tasks. */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /** Adds a task to the end of the list. */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Returns the task at a one-based position, or an input error if it is absent. */
    public Task get(int oneBasedPosition) {
        return tasks.get(toIndex(oneBasedPosition));
    }

    /** Removes and returns the task at a one-based position. */
    public Task remove(int oneBasedPosition) {
        return tasks.remove(toIndex(oneBasedPosition));
    }

    /** Returns the number of tasks currently stored. */
    public int size() {
        return tasks.size();
    }

    /** Returns a read-only view for display and persistence. */
    public List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    private int toIndex(int oneBasedPosition) {
        if (oneBasedPosition < 1 || oneBasedPosition > tasks.size()) {
            throw new JarvisException("That task number does not exist.");
        }
        return oneBasedPosition - 1;
    }
}
