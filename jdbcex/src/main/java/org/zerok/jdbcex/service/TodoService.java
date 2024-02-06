package org.zerok.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerok.jdbcex.dao.TodoDAO;
import org.zerok.jdbcex.domain.TodoVO;
import org.zerok.jdbcex.dto.TodoDTO;
import org.zerok.jdbcex.util.MapperUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INTANCE;

    private TodoDAO todoDAO;
    private ModelMapper modelMapper;

    TodoService(){
        todoDAO = new TodoDAO();
        modelMapper = MapperUtil.INTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws Exception {

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        log.info("todoVo: " + todoVO);

        todoDAO.insert(todoVO);
    }

    public List<TodoDTO> listAll() throws SQLException {

        List<TodoVO> list = todoDAO.selectAll();

        log.info("todoVO.............................");
        log.info(list);

        List<TodoDTO> dtoList = list.stream()
                .map(vo-> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public TodoDTO get(Long tno) throws SQLException {
        log.info("tno: "+ tno);
        TodoVO todoVO = todoDAO.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    public void remove(Long tno) throws SQLException {
        log.info("tno" + tno);

        todoDAO.deleteOne(tno);
    }

    public void modify(TodoDTO todoDTO) throws Exception {

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoDAO.updateOne(todoVO);
    }

}