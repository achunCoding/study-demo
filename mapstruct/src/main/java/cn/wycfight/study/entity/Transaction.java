package cn.wycfight.study.entity;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@ToString
public class Transaction {

    private Long id;
    private String uuid = UUID.randomUUID().toString();
    private BigDecimal total;
}
