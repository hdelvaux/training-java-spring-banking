package banking;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
class Transaction {

  private @Id @GeneratedValue Long id;
  private LocalDateTime datetime = LocalDateTime.now();
  private @ManyToOne @JoinColumn(name="credit_account_id") Account creditAccount;
  private @ManyToOne @JoinColumn(name="debit_account_id") Account debitAccount;
  private Double amount;

  Transaction(){}

  Transaction(Account creditAccount, Account debitAccount, Double amount) {
    this.datetime = LocalDateTime.now();
    this.creditAccount = creditAccount;
    this.debitAccount = debitAccount;
    this.amount = amount;
  }

}
