package banking;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository repository;

  public AccountService(AccountRepository repository) {
    this.repository = repository;
  }

}
