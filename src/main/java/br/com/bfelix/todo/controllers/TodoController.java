package br.com.bfelix.todo.controllers;

import br.com.bfelix.todo.model.dto.TodoDTO;
import br.com.bfelix.todo.model.entities.Todo;
import br.com.bfelix.todo.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping
public class TodoController {

    private static final String TODO_PATH = "/api/v1/todo";
    private static final String TODO_PATH_OPEN = TODO_PATH + "/open";
    private static final String TODO_PATH_CLOSE = TODO_PATH + "/close";
    private static final String TODO_PATH_ID = TODO_PATH + "/{todoId}";

    private final TodoService todoService;

    @GetMapping(TODO_PATH)
    public ResponseEntity<List<TodoDTO>> listarTodos() {

        List<TodoDTO> todoDTOList = todoService.listTodo();

        if (todoDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(todoDTOList);
    }

    @GetMapping(TODO_PATH_OPEN)
    public ResponseEntity<List<TodoDTO>> listarTodosAberto() {
        List<TodoDTO> todoDTOList = todoService.listTodoOpen();
        return ResponseEntity.status(HttpStatus.OK).body(todoDTOList);
    }

    @GetMapping(TODO_PATH_CLOSE)
    public ResponseEntity<List<TodoDTO>> listarTodosFechado() {
        List<TodoDTO> todoDTOList = todoService.listTodoClose();
        return ResponseEntity.status(HttpStatus.OK).body(todoDTOList);
    }

    @GetMapping(TODO_PATH_ID)
    public ResponseEntity<Optional<TodoDTO>> buscarTodoPorId(@PathVariable("todoId") Long id) {

        Optional<TodoDTO> todoDTO = todoService.getTodoById(id);

        if (todoDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(todoDTO);
    }
}
