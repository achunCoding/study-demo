package cn.wycfight.study.mapper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import cn.wycfight.study.dto.TransactionDTO;
import cn.wycfight.study.entity.Transaction;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
abstract class TransactionMapper {

    public TransactionDTO toTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setUuid(transaction.getUuid());
        transactionDTO.setTotalInCents(transaction.getTotal().multiply(new BigDecimal("100")).longValue());
        return transactionDTO;
    }

    public abstract List<TransactionDTO> toTransactionDTO(Collection<Transaction> transactions);
}
