import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;
import springboot.service.TodoService;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;


public class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;


    private TodoService todoService;



    @Before
    public void setUp() {
        //this.todoRepository = new TodoRepository();
        MockitoAnnotations.initMocks(this);
        this.todoService = new TodoService(this.todoRepository);
    }

    @After
    public void tearDown() {
        BDDMockito.then(this.todoRepository);
    }

    @Test
    public void getAllTest() throws Exception {

        ArrayList<Todo> todos = new ArrayList<Todo>();
        todos.add(new Todo("todo", TodoPriority.LOW));
        BDDMockito.given(todoRepository.getAll()).willReturn(todos);

        //todoRepository.store(new Todo ("todo", TodoPriority.LOW));

        //TodoService todoService = new TodoService(new TodoRepository());
        //todoService.saveTodo("todo", TodoPriority.LOW);
        List<Todo> listResult = todoService.getAll();

        Assert.assertThat(listResult, Matchers.notNullValue());


    }

    public void saveTodoTest() {
        Todo newTodo = new Todo("Another Todo", TodoPriority.MEDIUM);
        BDDMockito.given(this.todoRepository.store(newTodo)).willReturn(true);
        boolean success = todoService.saveTodo(newTodo.getName(), newTodo.getPriority());
        Assert.assertTrue(success);
    }
}
