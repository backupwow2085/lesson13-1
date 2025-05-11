import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSimpleTaskByQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");
        Epic epic = new Epic(2, new String[]{"Купить молоко", "Сварить суп"});
        Meeting meeting = new Meeting(3, "Обсуждение релиза", "Проект X", "Завтра");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask };
        Task[] actual = todos.search("маме");  // точное совпадение с регистром

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindEpicBySubtaskQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");
        Epic epic = new Epic(2, new String[]{"Купить молоко", "Сварить суп"});
        Meeting meeting = new Meeting(3, "Обсуждение релиза", "Проект X", "Завтра");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { epic };
        Task[] actual = todos.search("молоко");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMeetingByTopicQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");
        Epic epic = new Epic(2, new String[]{"Купить молоко", "Сварить суп"});
        Meeting meeting = new Meeting(3, "Релиз версии 2.0", "Проект X", "Четверг");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { meeting };
        Task[] actual = todos.search("версии");  // подстрока "версии" в topic

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMeetingByProjectQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");
        Epic epic = new Epic(2, new String[]{"Купить молоко", "Сварить суп"});
        Meeting meeting = new Meeting(3, "Обсуждение бюджета", "Проект Альфа", "Пятница");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { meeting };
        Task[] actual = todos.search("Альфа");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindMultipleTasksByQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Сделать отчёт по Проекту");
        Epic epic = new Epic(2, new String[]{"Закупить материалы", "Проектировать схему"});
        Meeting meeting = new Meeting(3, "Статус проекта", "Проект Альфа", "Понедельник");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.search("Проект");  // Все содержат "Проект" (с заглавной буквы)

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyIfNoTaskMatchesQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Сделать зарядку");
        Epic epic = new Epic(2, new String[]{"Погладить кошку", "Сходить в магазин"});
        Meeting meeting = new Meeting(3, "Звонок с командой", "Проект Бета", "Вторник");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {};
        Task[] actual = todos.search("собака");  // ничего не содержит "собака"

        Assertions.assertArrayEquals(expected, actual);
    }
}
