package br.com.bfelix.todo.bootstrap;

import br.com.bfelix.todo.model.entities.Todo;
import br.com.bfelix.todo.repositories.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final TodoRepository todoRepository;

    @Override
    public void run(String... args) throws Exception {
        carregarDadosTodo();
    }

    private void carregarDadosTodo() throws ParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (todoRepository.count() == 0) {
            Todo todo1 = Todo.builder()
                    .titulo("Estudar java")
                    .descricao("finalizar projeto todo")
                    .dataParaFinalizar(sdf.parse("10/08/2023"))
                    .finalizado(false)
                    .build();

            Todo todo2 = Todo.builder()
                    .titulo("Estudar Spring")
                    .descricao("finalizar projeto todo")
                    .dataParaFinalizar(sdf.parse("11/08/2023"))
                    .finalizado(false)
                    .build();

            Todo todo3 = Todo.builder()
                    .titulo("Estudar Angular")
                    .descricao("finalizar frontend do projeto todo")
                    .dataParaFinalizar(sdf.parse("13/08/2023"))
                    .finalizado(false)
                    .build();

            todoRepository.saveAll(Arrays.asList(todo1, todo2, todo3));
        }
    }
}
