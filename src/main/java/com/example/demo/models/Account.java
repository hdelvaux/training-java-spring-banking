package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
class Account {

  public enum AccountType{
    EQUITY,
    ASSET
  }

  private @Id @GeneratedValue Long id;
  private String name;
  private AccountType type;
  private @ManyToOne @JoinColumn(name="customer_id") Customer customer;
  private @OneToMany(mappedBy="creditAccount") List<Transaction> creditTransactions = new ArrayList<Transaction>();
  private @OneToMany(mappedBy="debitAccount") List<Transaction> debitTransactions = new ArrayList<Transaction>();

  Account() {}

  Account(Customer customer, String name) {
    this.creditTransactions = new ArrayList<Transaction>();
    this.debitTransactions = new ArrayList<Transaction>();
    this.customer = customer;
    this.name = name;
    this.type = AccountType.ASSET;
  }

  Account(Customer customer, String name, AccountType type) {
    this.creditTransactions = new ArrayList<Transaction>();
    this.debitTransactions = new ArrayList<Transaction>();
    this.customer = customer;
    this.name = name;
    this.type = type;
  }

  public Double getBalance(){
    Double credits = this.creditTransactions.stream().map(transaction -> transaction.getAmount()).reduce(0.0, Double::sum);
    Double debits = this.debitTransactions.stream().map(transaction -> transaction.getAmount()).reduce(0.0, Double::sum);
    return debits - credits;
  }

}
