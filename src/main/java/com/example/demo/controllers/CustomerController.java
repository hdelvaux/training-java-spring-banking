package banking;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class CustomerController {

  private final CustomerService service;

  CustomerController(CustomerService service) {
    this.service = service;
  }

  // ------- Additional routes --------

  @GetMapping("/customers/{id}/info")
  CustomerInfoCustomerDto customerInfo(@PathVariable Long id){
    Customer customer = this.service.getCustomer(id);
    return CustomerInfoCustomerDto.from(customer);

  @PostMapping("/customers/{id}/account/create")
  CustomerDto newAccount(@PathVariable Long id, @RequestBody NewAccountAccountDto newAccountDto){
    Customer customer = this.service.newAccount(id, newAccountDto);
    return CustomerDto.from(customer);

  }

  // ------- CRUD routes ------

  @PostMapping("/customers")
  CustomerDto addCustomer(@RequestBody Customer newCustomer) {
    return CustomerDto.from(this.service.addCustomer(newCustomer));
  }

  @GetMapping("/customers")
  List<CustomerDto> getCustomers() {
    return this.service.getCustomers().stream().map(CustomerDto::from).collect(Collectors.toList());
  }

  @GetMapping("/customers/{id}")
  CustomerDto getCustomer(@PathVariable Long id) {
    return CustomerDto.from(service.getCustomer(id));
  }

}
