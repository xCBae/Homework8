package Test;

import Main.DispensingSnackState;
import Main.Driver;
import Main.IdleState;
import Main.Snack;
import Main.SnackDispenseHandler;
import Main.StateOfVendingMachine;
import Main.VendingMachine;
import Main.WaitingForMoneyState;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine();
    }

    // Tests for VendingMachine class methods

    @Test
    public void selectSnack_ValidSnack_ShouldChangeStateToWaitingForMoney() {
        vendingMachine.selectSnack("Coke");
        assertTrue(vendingMachine.getCurrentState() instanceof WaitingForMoneyState);
    }

    @Test
    public void selectSnack_InvalidSnack_ShouldRemainInIdleState() {
        vendingMachine.selectSnack("Nonexistent Snack");
        assertTrue(vendingMachine.getCurrentState() instanceof IdleState);
    }

    @Test
    public void insertMoney_EnoughMoneyInserted_ShouldChangeStateToDispensingSnack() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.0);
        assertTrue(vendingMachine.getCurrentState() instanceof DispensingSnackState);
    }

    @Test
    public void insertMoney_InsufficientMoneyInserted_ShouldNotChangeState() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.0);
        assertTrue(vendingMachine.getCurrentState() instanceof WaitingForMoneyState);
    }

    @Test
    public void dispenseSnack_ValidSnackAndEnoughMoney_ShouldDispenseSnackAndChangeStateToIdle() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();
        assertTrue(vendingMachine.getCurrentState() instanceof IdleState);
    }

    @Test
    public void dispenseSnack_ValidSnackButInsufficientMoney_ShouldNotDispenseSnackAndRemainInWaitingForMoneyState() {
        vendingMachine.selectSnack("Coke");
        vendingMachine.insertMoney(1.0);
        vendingMachine.dispenseSnack();
        assertTrue(vendingMachine.getCurrentState() instanceof WaitingForMoneyState);
    }

    @Test
    public void dispenseSnack_InvalidSnack_ShouldNotDispenseSnackAndRemainInIdleState() {
        vendingMachine.selectSnack("Nonexistent Snack");
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack();
        assertTrue(vendingMachine.getCurrentState() instanceof IdleState);
    }

    @Test
    public void dispenseSnack_SnackQuantityZero_ShouldRemainInIdleState() {
        vendingMachine.selectSnack("Snickers"); // Only 1 Snickers available, already dispensed in previous tests
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack(); // Quantity of Snickers becomes 0
        vendingMachine.selectSnack("Snickers"); // Try selecting Snickers again
        assertTrue(vendingMachine.getCurrentState() instanceof IdleState);
    }

    // Tests for SnackDispenseHandler class methods

    @Test
    public void handleRequest_ValidSnackWithEnoughQuantity_ShouldChangeStateToWaitingForMoney() {
        SnackDispenseHandler handler = new SnackDispenseHandler();
        VendingMachine vendingMachine = new VendingMachine();
        handler.handleRequest("Coke", vendingMachine);
        assertTrue(vendingMachine.getCurrentState() instanceof WaitingForMoneyState);
    }

    @Test
    public void handleRequest_InvalidSnack_ShouldNotChangeState() {
        SnackDispenseHandler handler = new SnackDispenseHandler();
        VendingMachine vendingMachine = new VendingMachine();
        handler.handleRequest("Nonexistent Snack", vendingMachine);
        assertTrue(vendingMachine.getCurrentState() instanceof IdleState);
    }

    // Tests for State classes

    @Test
    public void idleState_SelectSnack_ValidSnack_ShouldChangeStateToWaitingForMoney() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.changeState(new IdleState(vendingMachine));
        vendingMachine.selectSnack("Coke");
        assertTrue(vendingMachine.getCurrentState() instanceof WaitingForMoneyState);
    }
}
