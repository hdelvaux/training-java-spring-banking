package banking;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository repository;

  public AccountService(AccountRepository repository) {
    this.repository = repository;
  }

  // --------- CRUD methods ---------

  public Account addAccount(Account account){
    return this.repository.save(account);
  }

  public List<Account> getAccounts(){
    return this.repository.findAll();
  }

  public Account getAccount(Long id){
    if (id == null){
      throw new AccountNotFoundException((long)0);
    }
    return this.repository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
  }

}
