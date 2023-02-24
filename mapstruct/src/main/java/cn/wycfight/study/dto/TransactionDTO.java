package cn.wycfight.study.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransactionDTO {

    private String uuid;
    private Long totalInCents;
}
