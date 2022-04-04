package banking;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class AccountController {

  private final AccountService service;

  AccountController(AccountService service) {
    this.service = service;
  }

  // ------- CRUD routes ------

  @PostMapping("/accounts")
  AccountDto newAccount(@RequestBody Account newAccount) {
    return AccountDto.from(this.service.addAccount(newAccount));
  }

  @GetMapping("/accounts")
  List<AccountDto> all() {
    return this.service.getAccounts().stream().map(AccountDto::from).collect(Collectors.toList());
  }

  @GetMapping("/accounts/{id}")
  AccountDto one(@PathVariable Long id) {
    return AccountDto.from(this.service.getAccount(id));
  }

}
