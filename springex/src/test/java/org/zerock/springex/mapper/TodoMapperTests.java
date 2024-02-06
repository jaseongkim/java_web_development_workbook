package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert() {
        TodoVO todoVO = TodoVO.builder()
                .title("스프링테스트")
                .dueDate(LocalDate.of(2021, 10, 21))
                .writer("user00")
                .build();

        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll(){

        List<TodoVO> list = todoMapper.selectAll();

        list.forEach(vo ->{log.info(vo);});

    }

    @Test
    public void testSelectOne(){
        Long tno = 21L;
        TodoVO todoVO = todoMapper.selectOne(tno);
        log.info(todoVO);
    }

    @Test
    public void testDelete(){
        Long tno = 21L;
        todoMapper.delete(tno);
    }

    @Test
    public void testUpdate(){
        TodoVO todoVO = TodoVO.builder()
                .tno(21L)
                .title("안녕")
                .dueDate(LocalDate.of(2023,11,23))
                .finished(true)
                .build();

        todoMapper.update(todoVO);
    }

    @Test
    public void testSelectList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .list(10)
                .build();

        List<TodoVO> list = todoMapper.selectList(pageRequestDTO);
    }

   @Test
    public void testSelectSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .list(10)
                .types(new String[]{"t", "w"})
                .keyword("AAA")
//                .finished(true)
                .from(LocalDate.of(2022,10,11))
                .to(LocalDate.of(2023,12,13))
                .build();

//        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
//
//        voList.forEach(vo->log.info(vo));

        log.info(todoMapper.getCount(pageRequestDTO));


   }


}
