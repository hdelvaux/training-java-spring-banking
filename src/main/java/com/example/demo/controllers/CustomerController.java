package banking;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
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
  }
}
