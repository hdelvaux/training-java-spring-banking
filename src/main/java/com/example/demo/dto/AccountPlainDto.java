package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;

import banking.Account.AccountType;

@Data
class AccountPlainDto {

  private Long id;
  private String name;
  private AccountType type;

  public static AccountPlainDto from(Account account){
    AccountPlainDto accountPlainDto = new AccountPlainDto();
    accountPlainDto.setId(account.getId());
    accountPlainDto.setName(account.getName());
    accountPlainDto.setType(account.getType());
    return accountPlainDto;
  }
}
