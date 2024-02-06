package org.zerok.w2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class MemberDTO {
    private String mid;
    private String mpw;
    private String mname;
    private String uuid;
}
