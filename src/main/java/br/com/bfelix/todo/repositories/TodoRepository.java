package br.com.bfelix.todo.repositories;

import br.com.bfelix.todo.model.dto.TodoDTO;
import br.com.bfelix.todo.model.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

//    @Query(nativeQuery = true, value = "SELECT * FROM todos WHERE finalizado = false order by data_para_finalizar desc ")
//    List<Todo> findTodosByFinalizado();
//    Ou
    List<Todo> findTodosByFinalizadoIsFalseOrderByDataParaFinalizarDesc();

//    @Query(nativeQuery = true, value = "SELECT * FROM todos WHERE finalizado = true order by data_para_finalizar desc ")
//    List<Todo> findTodosByAberto();
//    Ou
    List<Todo> findTodosByFinalizadoIsTrueOrderByDataParaFinalizarDesc();

}
