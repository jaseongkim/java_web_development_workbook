package org.zerok.w2.service;

import org.modelmapper.ModelMapper;
import org.zerok.w2.dao.MemberDAO;
import org.zerok.w2.domain.MemberVO;
import org.zerok.w2.dto.MemberDTO;
import org.zerok.w2.util.MapperUtil;

import java.sql.SQLException;

public enum MemberService {

    INSTANCE;

    private MemberDAO memberDAO;
    private ModelMapper modelMapper;

    MemberService(){
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INTANCE.get();
    }

    public MemberDTO Login(String mid, String mpw) throws Exception {

        MemberVO memberVO = memberDAO.getWithPassword(mid, mpw);

        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);

        return memberDTO;
    }

    public void updateUuid(String uuid, String mid) throws SQLException {
        memberDAO.updateUuid(uuid, mid);
    }

    public MemberDTO getByUuid(String uuid) throws SQLException {
        MemberVO memberVO = memberDAO.selectUuid(uuid);

        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);

        return memberDTO;

    }

}