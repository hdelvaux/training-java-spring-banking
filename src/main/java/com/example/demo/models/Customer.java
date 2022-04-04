package banking;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
class Customer {

  private @Id @GeneratedValue Long id;
  private String name;
  private String surname;
  private @OneToMany(mappedBy="customer") List<Account> accounts = new ArrayList<Account>();

  public Customer(){}

  public Customer(String name, String surname){
    this.name = name;
    this.surname = surname;
  }

  public void addAccount(Account account){
    this.accounts.add(account);
  }

  public void removeAccount(Account account){
    this.accounts.remove(account);
  }

  public List<Account> getAccountsType(Account.AccountType type){
    return this.accounts.stream().filter(account -> account.getType() == type).collect(Collectors.toList());
  }

  public Double getBalance(){
    return this.getAccountsType(Account.AccountType.ASSET).stream().map(account -> account.getBalance()).reduce(0.0, Double::sum);
  }

}
