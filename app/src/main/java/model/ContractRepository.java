package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repository class to manage contract data.
 */
public class ContractRepository {
  private List<Contract> contracts;

  /**
   * Constructs a ContractRepository with an empty list of contracts.
   */
  public ContractRepository() {
    this.contracts = new ArrayList<>();
  }

  /**
   * Copy constructor for creating a new instance of ContractRepository
   * by copying the contents of another ContractRepository instance.
   *
   * @param other the ContractRepository instance to copy from
   */
  public ContractRepository(ContractRepository other) {
    this.contracts = new ArrayList<>(other.contracts);
  }

  /**
   * Adds a new contract to the repository.
   *
   * @param contract the contract to add
   * @throws ModelExceptions.IdExistsException if a contract with the same ID
   *                                           already exists
   */
  public void addContract(Contract contract) {
    if (contracts.stream().anyMatch(c -> c.getId().equals(contract.getId()))) {
      throw new ModelExceptions.IdExistsException();
    }
    contracts.add(contract);
  }

  /**
   * Deletes a contract from the repository.
   *
   * @param contract the contract to delete
   */
  public void deleteContract(Contract contract) {
    contracts.remove(contract);
  }

  /**
   * Retrieves a contract from the repository by its ID.
   *
   * @param contractId the ID of the contract to retrieve
   * @return the contract with the specified ID, or null if not found
   */
  public Contract getContract(String contractId) {
    return contracts.stream()
        .filter(existingContract -> existingContract.getId().equals(contractId))
        .findFirst()
        .orElse(null);
  }

  /**
   * Retrieves all contracts from the repository.
   *
   * @return a list of all contracts
   */
  public List<Contract> getAllContracts() {
    return Collections.unmodifiableList(new ArrayList<>(contracts));
  }

  /**
   * Cancels all active contracts for a specific item.
   *
   * @param item the item for which to cancel contracts
   */
  public void cancelContractsForItem(Item item) {
    for (Contract contract : contracts) {
      if (contract.getItem().equals(item) && contract.isActive()) {
        contract.setActive(false);
      }
    }
  }
}
