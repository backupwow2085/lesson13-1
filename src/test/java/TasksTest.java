import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {

    @Test
    public void simpleTaskShouldMatchQuery() {
        SimpleTask task = new SimpleTask(1, "Позвонить маме");
        Assertions.assertTrue(task.matches("маме"));
    }

    @Test
    public void simpleTaskShouldNotMatchQuery() {
        SimpleTask task = new SimpleTask(1, "Позвонить маме");
        Assertions.assertFalse(task.matches("папе"));
    }

    @Test
    public void epicShouldMatchIfSubtaskMatches() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб", "Сыр"});
        Assertions.assertTrue(epic.matches("Хлеб"));
    }

    @Test
    public void epicShouldNotMatchIfNoSubtaskMatches() {
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб", "Сыр"});
        Assertions.assertFalse(epic.matches("Мясо"));
    }

    @Test
    public void meetingShouldMatchIfTopicMatches() {
        Meeting meeting = new Meeting(3, "Обсуждение релиза", "Проект X", "Завтра");
        Assertions.assertTrue(meeting.matches("релиза"));
    }

    @Test
    public void meetingShouldMatchIfProjectMatches() {
        Meeting meeting = new Meeting(3, "Обсуждение релиза", "Проект X", "Завтра");
        Assertions.assertTrue(meeting.matches("Проект"));
    }

    @Test
    public void meetingShouldNotMatchIfQueryNotFound() {
        Meeting meeting = new Meeting(3, "Обсуждение релиза", "Проект X", "Завтра");
        Assertions.assertFalse(meeting.matches("Кофе"));
    }
}
