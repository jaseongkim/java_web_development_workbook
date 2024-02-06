package org.zerok.w2.dto;

import lombok.*;

import java.time.LocalDate;

@Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class TodoDTO {
    private long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}