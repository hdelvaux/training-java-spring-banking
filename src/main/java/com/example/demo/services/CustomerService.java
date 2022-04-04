package banking;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private CustomerRepository repository;

  public CustomerService(CustomerRepository repository) {
    this.repository = repository;
  }

  // --------- CRUD methods ---------

  public Customer addCustomer(Customer customer){
    return this.repository.save(customer);
  }

  public List<Customer> getCustomers(){
    return this.repository.findAll();
  }

  public Customer getCustomer(Long id){
    return this.repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
  }
}
