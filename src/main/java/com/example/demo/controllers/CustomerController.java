package banking;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class CustomerController {

  private final CustomerService service;

  CustomerController(CustomerService service) {
    this.service = service;
  }

  // ------- Additional routes --------

  @PostMapping("/customers/{id}/account/create")
  CustomerDto newAccount(@PathVariable Long id, @RequestBody NewAccountAccountDto newAccountDto){
    Customer customer = this.service.newAccount(id, newAccountDto);
    return CustomerDto.from(customer);
  }
}
