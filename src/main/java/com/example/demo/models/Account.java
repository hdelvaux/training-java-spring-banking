package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;

@Data
class Account {

  public enum AccountType{
    EQUITY,
    ASSET
  }

  private Long id;
  private String name;
  private AccountType type;
  private Customer customer;
  private List<Transaction> creditTransactions = new ArrayList<Transaction>();
  private List<Transaction> debitTransactions = new ArrayList<Transaction>();

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
