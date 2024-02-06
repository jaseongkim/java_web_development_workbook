package org.zerock.springex.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired(required = false)
    private TodoService todoService;

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testRegister(){

        TodoDTO todoDTO = TodoDTO.builder()
                .title("test......")
                .dueDate(LocalDate.now())
                .writer("user1")
                .build();

        todoService.register(todoDTO);
    }

    @Test
    public void testPaging(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).list(10).build();

        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.getList(pageRequestDTO);

        log.info(pageResponseDTO);

        pageResponseDTO.getDtoList().forEach(dto->log.info(dto));

    }
}
