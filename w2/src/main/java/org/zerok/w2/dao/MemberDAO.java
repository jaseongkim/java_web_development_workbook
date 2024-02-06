package org.zerok.w2.dao;

import lombok.Cleanup;
import org.zerok.w2.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

    public MemberVO getWithPassword(String mid, String mpw) throws Exception {

        String sql = "SELECT mid, mpw, mname FROM tbl_member WHERE mid = ? AND mpw = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1, mid);
        ptmt.setString(2, mpw);

        @Cleanup ResultSet rs = ptmt.executeQuery();

        rs.next();

        MemberVO memberVO = MemberVO.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .mname(rs.getString("mname"))
                .build();

        return memberVO;
    }

    public void updateUuid(String uuid, String mid) throws SQLException {
        String sql ="UPDATE tbl_member set uuid=? where mid = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1,uuid);
        ptmt.setString(2,mid);

        ptmt.executeUpdate();

    }

    public MemberVO selectUuid(String uuid) throws SQLException {
        String sql = "Select mid, mpw, mname, uuid from tbl_member where uuid = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1,uuid);

        @Cleanup ResultSet rs = ptmt.executeQuery();

        rs.next();

        MemberVO memberVO = MemberVO.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .mname(rs.getString("mname"))
                .uuid(rs.getString("uuid"))
                .build();

        return memberVO;

    }
}