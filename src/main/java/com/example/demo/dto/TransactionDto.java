package banking;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Data;

@Data
class TransactionDto {

  private Long id;
  private LocalDateTime datetime;
  private AccountPlainDto creditAccountPlainDto;
  private AccountPlainDto debitAccountPlainDto;
  private Double amount;

  public static TransactionDto from(Transaction transaction){
    TransactionDto transactionDto = new TransactionDto();
    transactionDto.setId(transaction.getId());
    transactionDto.setDatetime(transaction.getDatetime());
    transactionDto.setAmount(transaction.getAmount());
    transactionDto.setCreditAccountPlainDto(AccountPlainDto.from(transaction.getCreditAccount()));
    transactionDto.setDebitAccountPlainDto(AccountPlainDto.from(transaction.getDebitAccount()));
    return transactionDto;
  }

}
