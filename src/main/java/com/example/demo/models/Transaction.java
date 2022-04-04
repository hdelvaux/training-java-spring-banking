package banking;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.Data;

@Data
class Transaction {

  private Long id;
  private LocalDateTime datetime = LocalDateTime.now();
  private Account creditAccount;
  private Account debitAccount;
  private Double amount;

  Transaction(){}

  Transaction(Account creditAccount, Account debitAccount, Double amount) {
    this.datetime = LocalDateTime.now();
    this.creditAccount = creditAccount;
    this.debitAccount = debitAccount;
    this.amount = amount;
  }

}
