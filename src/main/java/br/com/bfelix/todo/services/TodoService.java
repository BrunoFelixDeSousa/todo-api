package br.com.bfelix.todo.services;

import br.com.bfelix.todo.model.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    List<TodoDTO> listTodo();
}
