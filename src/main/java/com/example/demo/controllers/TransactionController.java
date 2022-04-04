package banking;

import org.springframework.web.bind.annotation.RestController;

@RestController
class TransactionController {

  private final TransactionService service;

  TransactionController(TransactionService service) {
    this.service = service;
  }

}
