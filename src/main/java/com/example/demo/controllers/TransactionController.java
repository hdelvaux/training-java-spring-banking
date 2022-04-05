package banking;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class TransactionController {

  private final TransactionService service;

  TransactionController(TransactionService service) {
    this.service = service;
  }

  // ------- CRUD routes ------

  @PostMapping("/transactions")
  TransactionDto newTransaction(@RequestBody Transaction newTransaction) {
    return TransactionDto.from(this.service.addTransaction(newTransaction));
  }

  @GetMapping("/transactions")
  List<TransactionDto> all() {
    return this.service.getTransactions().stream().map(TransactionDto::from).collect(Collectors.toList());
  }

  @GetMapping("/transactions/{id}")
  TransactionDto one(@PathVariable Long id) {
    return TransactionDto.from(this.service.getTransaction(id));
  }

}
