package banking;

import java.util.List;

import org.springframework.stereotype.Service;

import banking.Account.AccountType;

@Service
public class CustomerService {

  private CustomerRepository repository;
  private AccountService accountService;
  private TransactionService transactionService;

  public CustomerService(CustomerRepository repository, AccountService accountService, TransactionService transactionService) {
    this.repository = repository;
    this.accountService = accountService;
    this.transactionService = transactionService;
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
    return this.repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
  }

  // ------ Additional methods ------

  public Customer newAccount(Long id, NewAccountAccountDto newAccountDto){

    Account account = new Account();
    account.setName(newAccountDto.getName());
    account.setType(AccountType.ASSET);

    Customer customer = this.getCustomer(id);
    customer.addAccount(account);
    account.setCustomer(customer);
    this.accountService.addAccount(account);

    if(newAccountDto.getInitialCredit() != null && newAccountDto.getInitialCredit() != 0.0){
        Transaction transaction = new Transaction(customer.getAccountsType(AccountType.EQUITY).get(0), account, newAccountDto.getInitialCredit());
        this.transactionService.addTransaction(transaction);
    }
    return repository.save(customer);
  }
}
