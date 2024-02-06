package org.zerock.b01.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Reply", indexes = {@Index(name="idx_reply_board_bno", columnList = "board_bno")})
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;

    private String replyer;

    public void ChangeText(String replyText){
        this.replyText = replyText;
    }

}
