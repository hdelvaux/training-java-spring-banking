package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Data;

import banking.Account.AccountType;

@Data
class NewAccountAccountDto {

  private Long id;
  private String name;
  private Long customerId;
  private Double initialCredit;
  private List<TransactionPlainDto> creditTransactionsPlainDto;
  private List<TransactionPlainDto> debitTransactionsPlainDto;

}
