package org.zerok.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerok.w2.dao.MemberDAO;
import org.zerok.w2.domain.MemberVO;

@Log4j2
public class MemberServiceTests {


    private MemberDAO memberDAO;

    @BeforeEach
    public void ready(){

        memberDAO = new MemberDAO();
    }
    @Test
    public void testTime() throws Exception {

        MemberVO memberVO = memberDAO.getWithPassword("user00", "1111");
        log.info(memberVO);
    }


}
