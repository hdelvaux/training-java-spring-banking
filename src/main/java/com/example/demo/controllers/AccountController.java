package banking;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
class AccountController {

  private final AccountService service;

  AccountController(AccountService service) {
    this.service = service;
  }

  // -------- Additional routes ---------

    @PostMapping("/accounts/new")
    AccountDto newAccount(@RequestBody NewAccountAccountDto newAccount) {
     return AccountDto.from(this.service.newAccount(newAccount));
    }
}
