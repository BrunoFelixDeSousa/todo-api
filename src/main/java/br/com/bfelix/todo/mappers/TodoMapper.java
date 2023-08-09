package br.com.bfelix.todo.mappers;

import br.com.bfelix.todo.model.dto.TodoDTO;
import br.com.bfelix.todo.model.entities.Todo;
import org.mapstruct.Mapper;

@Mapper
public interface TodoMapper {

    Todo todoDtoToTodo(TodoDTO dto);
    TodoDTO todoToTodoDto(Todo todo);
}
