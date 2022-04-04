package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;

@Data
class CustomerPlainDto {

  private Long id;
  private String name;
  private String surname;

  public static CustomerPlainDto from(Customer customer){
    CustomerPlainDto customerPlainDto = new CustomerPlainDto();
    customerPlainDto.setId(customer.getId());
    customerPlainDto.setName(customer.getName());
    customerPlainDto.setSurname(customer.getSurname());
    return customerPlainDto;
  }
}
