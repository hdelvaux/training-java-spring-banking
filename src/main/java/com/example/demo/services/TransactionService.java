package banking;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TransactionRepository repository;

  public TransactionService(TransactionRepository repository) {
    this.repository = repository;
  }

  // --------- CRUD methods ---------

  public Transaction addTransaction(Transaction transaction){
    return this.repository.save(transaction);
  }

  public List<Transaction> getTransactions(){
    return this.repository.findAll();
  }

  public Transaction getTransaction(Long id){
    if (id == null){
      throw new TransactionNotFoundException((long)0);
    }
    return this.repository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
  }

}
