package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Data;

@Data
class CustomerInfoCustomerDto {

  private Long id;
  private String name;
  private String surname;
  private Double balance;
  private List<CustomerInfoAccountDto> accountsAssetDto;

  public static CustomerInfoCustomerDto from(Customer customer){
    CustomerInfoCustomerDto customerInfoDto = new CustomerInfoCustomerDto();
    customerInfoDto.setId(customer.getId());
    customerInfoDto.setName(customer.getName());
    customerInfoDto.setSurname(customer.getSurname());
    customerInfoDto.setAccountsAssetDto(customer.getAccountsType(Account.AccountType.ASSET).stream().map(CustomerInfoAccountDto::from).collect(Collectors.toList()));
    customerInfoDto.setBalance(customer.getBalance());
    return customerInfoDto;
  }
}
