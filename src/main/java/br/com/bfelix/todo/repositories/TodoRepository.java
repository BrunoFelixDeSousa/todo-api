package br.com.bfelix.todo.repositories;

import br.com.bfelix.todo.model.dto.TodoDTO;
import br.com.bfelix.todo.model.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("SELECT obj FROM Todo obj WHERE obj.finalizado = :false order by obj.dataParaFinalizar")
    List<TodoDTO> listTodoOpen();
}
