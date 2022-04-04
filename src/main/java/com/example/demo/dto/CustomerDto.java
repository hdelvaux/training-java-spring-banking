package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Data;

import banking.Account.AccountType;

@Data
class CustomerDto {

  private Long id;
  private String name;
  private String surname;
  private List<AccountPlainDto> accountsPlainDto;

  public static CustomerDto from(Customer customer){
    CustomerDto customerDto = new CustomerDto();
    customerDto.setId(customer.getId());
    customerDto.setName(customer.getName());
    customerDto.setSurname(customer.getSurname());
    customerDto.setAccountsPlainDto(customer.getAccountsType(AccountType.ASSET).stream().map(AccountPlainDto::from).collect(Collectors.toList()));
    return customerDto;
  }
}
