package org.zerok.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerok.w2.dto.TodoDTO;
import org.zerok.w2.service.TodoService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
public class TodoServiceTests {

    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INTANCE;
    }

    @Test
    public void testRegister() throws Exception {

        TodoDTO todoDTO = TodoDTO.builder()
                .title("modelTest")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();

        log.info("----------------------------------------------------------");
        log.info(todoDTO);

        todoService.register(todoDTO);
    }

    @Test
    public void testListAll() throws SQLException {

        List<TodoDTO> list = todoService.listAll();

        log.info("todoDTO...............................");
        log.info(list);

    }
}
