package controller;

import java.time.LocalDate;
import model.Contract;
import model.ContractRepository;
import model.Item;
import model.ItemRepository;
import model.Member;
import model.MemberRepository;
import model.ModelExceptions;
import view.ContractView;
import view.ItemView;
import view.MainView;
import view.MemberView;

/**
 * Main application class for setting up and starting the system.
 */
public class App {
  /**
   * The main method serves as the entry point for the application.
   * All resources like scanners are properly closed.
   *
   * @param args command-line arguments passed to the application (not used in
   *             this application)
   */
  public static void main(String[] args) {
    // Create views
    MemberView memberView = new MemberView();
    ItemView itemView = new ItemView();
    ContractView contractView = new ContractView();
    MainView mainView = new MainView();

    // Create repositories (Ensure these are the same instances passed everywhere)
    MemberRepository memberRepository = new MemberRepository();
    ItemRepository itemRepository = new ItemRepository();
    ContractRepository contractRepository = new ContractRepository();

    // Setup initial test data
    setupTestData(memberRepository, itemRepository, contractRepository, memberView, itemView, contractView);

    // Create controllers using the same repository instances
    MemberController memberController = new MemberController(memberRepository, memberView);
    ItemController itemController = new ItemController(itemRepository, itemView, contractRepository, memberRepository);
    ContractController contractController = new ContractController(contractRepository, itemRepository, memberRepository,
        contractView);

    // Create and start the main controller
    MainController mainController = new MainController(memberController, itemController, contractController, mainView);
    mainController.start();

    // Close scanners
    memberView.close();
    itemView.close();
    contractView.close();
    mainView.close();
  }

  /**
   * Sets up initial test data for the application.
   *
   * @param memberRepository   the member repository
   * @param itemRepository     the item repository
   * @param contractRepository the contract repository
   * @param memberView         the member view
   * @param itemView           the item view
   * @param contractView       the contract view
   */
  private static void setupTestData(MemberRepository memberRepository, ItemRepository itemRepository,
      ContractRepository contractRepository, MemberView memberView, ItemView itemView, ContractView contractView) {
    try {
      // Retrieve validation patterns from the view
      String emailPattern = memberView.getEmailRegexPattern();
      String phonePattern = memberView.getPhoneRegexPattern();

      // Create Member M1
      Member m1 = new Member("M1", "Member One", "m1@example.com", "1234567890", 500, emailPattern, phonePattern);
      memberRepository.addMember(m1);

      // Create Items for M1
      Item i1 = new Item(m1, "Item One", 50);
      Item i2 = new Item(m1, "Item Two", 10);
      itemRepository.addItem(i1);
      itemRepository.addItem(i2);

      // Create Member M2
      Member m2 = new Member("M2", "Member Two", "m2@example.com", "0987654321", 100, emailPattern, phonePattern);
      memberRepository.addMember(m2);

      // Create Member M3
      Member m3 = new Member("M3", "Member Three", "m3@example.com", "1122334455", 100, emailPattern, phonePattern);
      memberRepository.addMember(m3);

      // Create Item I3 for M3
      Item i3ForM3 = new Item(m3, "Item Three for M3", 10);
      itemRepository.addItem(i3ForM3);

      // Create Contract for M3 for Item I3
      Contract contract = new Contract("C1", i3ForM3, m2, LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 7));
      contractRepository.addContract(contract);

    } catch (ModelExceptions.NegativeCreditsException e) {
      memberView.displayNegativeCreditsMessage();
    } catch (ModelExceptions.IdExistsException e) {
      memberView.displayIdExistsMessage();
    } catch (ModelExceptions.NegativeCostException e) {
      itemView.displayNegativeCostMessage();
    } catch (ModelExceptions.InvalidEndDateException e) {
      contractView.displayInvalidEndDateMessage();
    } catch (ModelExceptions.ItemNotFoundException e) {
      contractView.displayItemNotFoundMessage();
    } catch (ModelExceptions.BorrowerNotFoundException e) {
      contractView.displayBorrowerNotFoundMessage();
    } catch (Exception e) {
      contractView.displayUnexpectedErrorMessage(e.getMessage());
    }
  }
}
