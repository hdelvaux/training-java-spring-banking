package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Data;

import banking.Account.AccountType;

@Data
class AccountDto {

  private Long id;
  private String name;
  private CustomerPlainDto customerPlainDto;
  private Double balance;
  private AccountType type;
  private List<TransactionPlainDto> creditTransactionsPlainDto;
  private List<TransactionPlainDto> debitTransactionsPlainDto;

  public static AccountDto from(Account account){
    AccountDto accountDto = new AccountDto();
    accountDto.setId(account.getId());
    accountDto.setName(account.getName());
    accountDto.setCustomerPlainDto(CustomerPlainDto.from(account.getCustomer()));
    accountDto.setCreditTransactionsPlainDto(account.getCreditTransactions().stream().map(TransactionPlainDto::from).collect(Collectors.toList()));
    accountDto.setDebitTransactionsPlainDto(account.getDebitTransactions().stream().map(TransactionPlainDto::from).collect(Collectors.toList()));
    accountDto.setBalance(account.getBalance());
    accountDto.setType(account.getType());
    return accountDto;
  }

}
