package br.com.bfelix.todo.controllers;

import br.com.bfelix.todo.model.dto.TodoDTO;
import br.com.bfelix.todo.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
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

        return ResponseEntity.status(HttpStatus.OK).body(todoDTO);
    }

    @PostMapping(TODO_PATH)
    public ResponseEntity<TodoDTO> criarTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO dto = todoService.SaveTodo(todoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{todoId}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).build();

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Location", TODO_PATH + "/" + dto.getId().toString());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(TODO_PATH_ID)
    public ResponseEntity<Object> deletarTodo(@PathVariable("todoId") Long id) {

        if (!todoService.deleteTodo(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Deletado");
    }

    @PutMapping(TODO_PATH_ID)
    public ResponseEntity<TodoDTO> atualizarTodo(@PathVariable("todoId") Long id, @RequestBody TodoDTO todoDTO) throws ParseException {

        TodoDTO dto = todoService.updateTodo(id, todoDTO);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PatchMapping(TODO_PATH_ID)
    public ResponseEntity<Optional<TodoDTO>> atualizarPatchTodo(@PathVariable("todoId") Long id, @RequestBody TodoDTO todoDTO) {

        Optional<TodoDTO> dto = todoService.updatePatchTodo(id, todoDTO);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
