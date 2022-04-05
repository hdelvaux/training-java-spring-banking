package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Data;

import banking.Account.AccountType;

@Data
class CustomerInfoAccountDto {

  private Long id;
  private String name;
  private Double balance;
  private AccountType type;
  private List<TransactionPlainDto> creditTransactionsPlainDto;
  private List<TransactionPlainDto> debitTransactionsPlainDto;

  public static CustomerInfoAccountDto from(Account account){
    CustomerInfoAccountDto accountDto = new CustomerInfoAccountDto();
    accountDto.setId(account.getId());
    accountDto.setName(account.getName());
    accountDto.setCreditTransactionsPlainDto(account.getCreditTransactions().stream().map(TransactionPlainDto::from).collect(Collectors.toList()));
    accountDto.setDebitTransactionsPlainDto(account.getDebitTransactions().stream().map(TransactionPlainDto::from).collect(Collectors.toList()));
    accountDto.setBalance(account.getBalance());
    accountDto.setType(account.getType());
    return accountDto;
  }

}
