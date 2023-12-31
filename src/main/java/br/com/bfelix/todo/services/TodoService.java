package br.com.bfelix.todo.services;

import br.com.bfelix.todo.model.dto.TodoDTO;
import org.springframework.data.jpa.repository.Query;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<TodoDTO> listTodo();

    List<TodoDTO> listTodoOpen();

    List<TodoDTO> listTodoClose();

    Optional<TodoDTO> getTodoById(Long id);

    TodoDTO SaveTodo(TodoDTO todoDTO);

    Boolean deleteTodo(Long id);

    TodoDTO updateTodo(Long id, TodoDTO todoDTO);

    Optional<TodoDTO> updatePatchTodo(Long id, TodoDTO todoDTO);
}
