package banking;

import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

  private final CustomerService service;

  CustomerController(CustomerService service) {
    this.service = service;
  }

}
