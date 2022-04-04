package banking;

import org.springframework.web.bind.annotation.RestController;

@RestController
class AccountController {

  private final AccountService service;

  AccountController(AccountService service) {
    this.service = service;
  }

}
