package br.com.bfelix.todo.services;

import br.com.bfelix.todo.mappers.TodoMapper;
import br.com.bfelix.todo.model.dto.TodoDTO;
import br.com.bfelix.todo.repositories.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .map(todoMapper::todoToTodoDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TodoDTO> getTodoById(Long id) {
        return Optional.ofNullable(todoMapper.todoToTodoDto(todoRepository.findById(id).orElse(null)));
    }
}
