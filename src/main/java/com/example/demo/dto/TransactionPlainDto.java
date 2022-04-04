package banking;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Data;

@Data
class TransactionPlainDto {

  private Long id;
  private LocalDateTime datetime;
  private Double amount;

  public static TransactionPlainDto from(Transaction transaction){
    TransactionPlainDto transactionPlainDto = new TransactionPlainDto();
    transactionPlainDto.setId(transaction.getId());
    transactionPlainDto.setDatetime(transaction.getDatetime());
    transactionPlainDto.setAmount(transaction.getAmount());
    return transactionPlainDto;
  }

}
