package banking;

import java.util.List;

import org.springframework.stereotype.Service;

import banking.Account.AccountType;

@Service
public class AccountService {

  private AccountRepository repository;
  private CustomerService customerService;
  private TransactionService transactionService;

  public AccountService(AccountRepository repository, CustomerService customerService, TransactionService transactionService) {
    this.repository = repository;
    this.customerService = customerService;
    this.transactionService = transactionService;
  }

  // --------- CRUD methods ---------

  public Account addAccount(Account account){
    return this.repository.save(account);
  }

  public List<Account> getAccounts(){
    return this.repository.findAll();
  }

  public Account getAccount(Long id){
    return this.repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
  }

  // -------- Additional methods --------------

  public Account newAccount(NewAccountAccountDto newAccountDto){
    Account account = new Account();
    account.setName(newAccountDto.getName());
    account.setType(AccountType.ASSET);

    Customer customer = this.customerService.getCustomer(newAccountDto.getCustomerId());
    customer.addAccount(account);
    account.setCustomer(customer);
    this.addAccount(account);
    this.customerService.addCustomer(customer);

    if(newAccountDto.getInitialCredit() != null && newAccountDto.getInitialCredit() != 0.0){
        Transaction transaction = new Transaction(customer.getAccountsType(AccountType.EQUITY).get(0), account, newAccountDto.getInitialCredit());
        this.transactionService.addTransaction(transaction);
    }
    return account;
  }
}
