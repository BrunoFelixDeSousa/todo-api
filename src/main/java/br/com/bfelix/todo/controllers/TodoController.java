package br.com.bfelix.todo.controllers;

import br.com.bfelix.todo.model.dto.TodoDTO;
import br.com.bfelix.todo.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping
public class TodoController {

    private static final String TODO_PATH = "/api/v1/todo";
    private static final String TODO_PATH_ID = TODO_PATH + "/{todoId}";

    private final TodoService todoService;

    @GetMapping(TODO_PATH)
    public ResponseEntity<List<TodoDTO>> listarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.listTodo());
    }

}
