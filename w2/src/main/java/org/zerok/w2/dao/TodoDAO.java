package org.zerok.w2.dao;

import lombok.Cleanup;
import org.zerok.w2.domain.TodoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    public String getTime() {

        String now = null;

        try (Connection conn = ConnectionUtil.INSTANCE.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
             ResultSet rs = pstmt.executeQuery();) {

            while (rs.next()) {
                now = rs.getString("SYSDATE");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception {

        String now = null;

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement("SELECT SYSDATE FROM DUAL");
        @Cleanup ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            now = rs.getString("SYSDATE");
        }
        return now;
    }

    public void insert(TodoVO todoVo) throws SQLException {

        String sql = "insert into tbl_todo (title, dueDate, finished) values (?,?,?)";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, todoVo.getTitle());
        pstmt.setDate(2, Date.valueOf(todoVo.getDueDate()));
        pstmt.setBoolean(3, todoVo.isFinished());

        pstmt.executeUpdate();
    }

    public List<TodoVO> selectAll() throws SQLException {

        String sql = "SELECT * FROM tbl_todo";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (rs.next()) {
            TodoVO todoVO = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
            list.add(todoVO);
        }

        return list;
    }

    public TodoVO selectOne(long tno) throws SQLException {

        String sql = "SELECT * FROM tbl_todo WHERE tno = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, tno);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();

        TodoVO todoVO = TodoVO.builder()
                .tno(rs.getLong("tno"))
                .title(rs.getString("title"))
                .dueDate(rs.getDate("dueDate").toLocalDate())
                .finished(rs.getBoolean("finished"))
                .build();

        return todoVO;
    }

    public int deleteOne(long tno) throws SQLException {
        String sql = "DELETE FROM tbl_todo WHERE tno = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setLong(1,tno);

        int no = ptmt.executeUpdate();

        return no;
    }

    public int updateOne(TodoVO todoVO) throws Exception {

        String sql = "UPDATE tbl_todo SET title = ?, dueDate=?, finished=? WHERE tno = ? ";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1,todoVO.getTitle());
        ptmt.setDate(2,Date.valueOf(todoVO.getDueDate()));
        ptmt.setBoolean(3, todoVO.isFinished());
        ptmt.setLong(4,todoVO.getTno());

        int no = ptmt.executeUpdate();

        return no;
    }

}