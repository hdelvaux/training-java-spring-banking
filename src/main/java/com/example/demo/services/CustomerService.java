package banking;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private CustomerRepository repository;

  public CustomerService(CustomerRepository repository) {
    this.repository = repository;
  }

}
