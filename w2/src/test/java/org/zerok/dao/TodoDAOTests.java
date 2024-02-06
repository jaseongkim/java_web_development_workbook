package org.zerok.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerok.w2.dao.TodoDAO;
import org.zerok.w2.domain.TodoVO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {

    private TodoDAO todoDAO;

    @BeforeEach
    public void ready(){
        todoDAO = new TodoDAO();
    }
    @Test
    public void testTime() throws Exception {
        System.out.println(todoDAO.getTime2());
    }

    @Test
    public void testInsert() throws SQLException {
        TodoVO todoVo = TodoVO.builder()
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .build();

        todoDAO.insert(todoVo);
    }

    @Test
    public void testSelectAll() throws SQLException {

        List<TodoVO> list = todoDAO.selectAll();

        list.forEach(todo-> System.out.println(todo));
    }

    @Test
    public void testDelete() throws Exception {
        long tno = 9L;

        int no = todoDAO.deleteOne(tno);

        System.out.println(no);
    }

    @Test
    public void testUpdate() throws Exception {
        TodoVO todoVo = TodoVO.builder()
                .tno(4)
                .title("TEST")
                .dueDate(LocalDate.of(2023,11,1))
                .finished(true)
                .build();

        int no = todoDAO.updateOne(todoVo);

        System.out.println(no);
    }

}