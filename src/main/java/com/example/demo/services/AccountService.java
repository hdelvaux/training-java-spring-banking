package banking;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;

import banking.Account.AccountType;

@Service
public class AccountService {

  private AccountRepository repository;
  private CustomerService customerService;
  private TransactionService transactionService;

  public AccountService(AccountRepository repository, @Lazy CustomerService customerService, TransactionService transactionService) {
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

    Account dstAccount = new Account();
    dstAccount.setName(newAccountDto.getName());
    dstAccount.setType(AccountType.ASSET);

    Customer customer = this.customerService.getCustomer(newAccountDto.getCustomerId());
    dstAccount.setCustomer(customer);
    this.addAccount(dstAccount);

    if(newAccountDto.getInitialCredit() != null && newAccountDto.getInitialCredit() != 0.0){
        this.customerService.initialTransaction(customer, dstAccount, newAccountDto.getInitialCredit());
    }
    return dstAccount;
  }

}
