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

  public void initialTransaction(Customer customer, Account dstAccount, Double amount){
    List<Account> equityAccounts = customer.getAccountsType(AccountType.EQUITY);
    Account srcAccount = null;
    if (equityAccounts.size() == 0){
      srcAccount = new Account(customer, "DefaultEquity", Account.AccountType.EQUITY);;
      this.accountService.addAccount(srcAccount);
    } else {
      srcAccount = equityAccounts.get(0);
    }
    Transaction transaction = new Transaction(srcAccount, dstAccount, amount);
    this.transactionService.addTransaction(transaction);
  }

  public Customer newAccount(Long id, NewAccountAccountDto newAccountDto){

    Account dstAccount = new Account();
    dstAccount.setName(newAccountDto.getName());
    dstAccount.setType(AccountType.ASSET);

    Customer customer = this.getCustomer(id);
    dstAccount.setCustomer(customer);
    this.accountService.addAccount(dstAccount);

    if(newAccountDto.getInitialCredit() != null && newAccountDto.getInitialCredit() != 0.0){
        initialTransaction(customer, dstAccount, newAccountDto.getInitialCredit());
    }
    return repository.save(customer);
  }

}
