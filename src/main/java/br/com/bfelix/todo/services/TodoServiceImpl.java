package br.com.bfelix.todo.services;

import br.com.bfelix.todo.mappers.TodoMapper;
import br.com.bfelix.todo.model.dto.TodoDTO;
import br.com.bfelix.todo.model.entities.Todo;
import br.com.bfelix.todo.model.exceptions.TodoNotFoundException;
import br.com.bfelix.todo.repositories.TodoRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Override
    public List<TodoDTO> listTodo() {
        return todoRepository.findAll()
                .stream()
                .map(todoMapper::toTodoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDTO> listTodoOpen() {
        return todoRepository.findTodosByFinalizadoIsFalseOrderByDataParaFinalizarDesc()
                .stream()
                .map(todoMapper::toTodoDTO)
                .collect(Collectors.toList());

//        return todoRepository.findTodosByFinalizado()
//                .stream()
//                .map(todoMapper::todoToTodoDto)
//                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDTO> listTodoClose() {
        return todoRepository.findTodosByFinalizadoIsTrueOrderByDataParaFinalizarDesc()
                .stream()
                .map(todoMapper::toTodoDTO)
                .collect(Collectors.toList());

//        return todoRepository.findTodosByFinalizado()
//                .stream()
//                .map(todoMapper::todoToTodoDto)
//                .collect(Collectors.toList());
    }

    @Override
    public Optional<TodoDTO> getTodoById(Long id) {
        return Optional.ofNullable(todoMapper.toTodoDTO(todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("Todo não encontrado! " + id + ", tipo: " + Todo.class.getName()
                ))));
    }

    @Override
    public TodoDTO SaveTodo(TodoDTO todoDTO) {


        return todoMapper.toTodoDTO(todoRepository.save(todoMapper.toTodo(todoDTO)));
    }

    @Override
    public Boolean deleteTodo(Long id) {

        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {

        Optional<Todo> todoOptional = Optional.ofNullable(todoRepository.findById(id).orElseThrow(
                () -> new TodoNotFoundException("Todo não encontrado! " + id + ", tipo: " + Todo.class.getName())
        ));

        Todo todo = new Todo();
        BeanUtils.copyProperties(todoDTO, todo);
        todo.setId(todoOptional.get().getId());
        todoRepository.save(todo);

        return todoMapper.toTodoDTO(todo);
    }

    @Override
    public Optional<TodoDTO> updatePatchTodo(Long id, TodoDTO todoDTO) {

        AtomicReference<Optional<TodoDTO>> atomicReference =  new AtomicReference<>();

        todoRepository.findById(id).ifPresentOrElse(foundTodo -> {

            if (StringUtils.hasText(todoDTO.getTitulo())) {
                foundTodo.setTitulo(todoDTO.getTitulo());
            }
            if (StringUtils.hasText(todoDTO.getDescricao())) {
                foundTodo.setDescricao(todoDTO.getDescricao());
            }
            if (todoDTO.getFinalizado() != null) {
                foundTodo.setFinalizado(todoDTO.getFinalizado());
            }
            if (todoDTO.getDataParaFinalizar() != null) {
                foundTodo.setDataParaFinalizar(todoDTO.getDataParaFinalizar());
            }

            atomicReference.set(Optional.of(todoMapper.toTodoDTO(todoRepository.save(foundTodo))));
        }, () -> new TodoNotFoundException("Todo não encontrado! " + id + ", tipo: " + Todo.class.getName()));

        return atomicReference.get();
    }
}