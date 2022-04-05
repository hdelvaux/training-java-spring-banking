package banking;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private CustomerRepository repository;
  private AccountService accountService;

  public CustomerService(CustomerRepository repository, AccountService accountService) {
    this.repository = repository;
    this.accountService = accountService;
  }

  // --------- CRUD methods ---------

  public Customer addCustomer(Customer customer){
    Account openingAccount = new Account(customer, "Equity", Account.AccountType.EQUITY);
    customer.addAccount(openingAccount);
    Customer savedCustomer = this.repository.save(customer);
    this.accountService.addAccount(openingAccount);
    return savedCustomer;
  }

  public List<Customer> getCustomers(){
    return this.repository.findAll();
  }

  public Customer getCustomer(Long id){
    if (id == null){
      throw new CustomerNotFoundException((long)0);
    }
    return this.repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
  }
}
