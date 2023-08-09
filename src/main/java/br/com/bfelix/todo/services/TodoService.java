package br.com.bfelix.todo.services;

import br.com.bfelix.todo.model.dto.TodoDTO;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<TodoDTO> listTodo();

    Optional<TodoDTO> getTodoById(Long id);
}
