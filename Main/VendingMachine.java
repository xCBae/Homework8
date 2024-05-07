package Main;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private StateOfVendingMachine currentState;
    private Map<String, Snack> snacks = new HashMap<>();
    private double insertedMoney;
    private String selectedSnack;

    public VendingMachine() {
        currentState = new IdleState(this);
        initializeSnacks();
    }

    private void initializeSnacks() {
        snacks.put("Coke", new Snack("Coke", 1.5, 10));
        snacks.put("Pepsi", new Snack("Pepsi", 1.5, 10));
        snacks.put("Cheetos", new Snack("Cheetos", 1.0, 10));
        snacks.put("Doritos", new Snack("Doritos", 1.0, 10));
        snacks.put("KitKat", new Snack("KitKat", 1.25, 10));
        snacks.put("Snickers", new Snack("Snickers", 1.25, 1)); // Only 1 Snickers available
    }

    public void changeState(StateOfVendingMachine newState) {
        this.currentState = newState;
    }

    public void selectSnack(String snackName) {
        currentState.selectSnack(snackName);
    }

    public void insertMoney(double amount) {
        currentState.insertMoney(amount);
    }

    public void dispenseSnack() {
        currentState.dispenseSnack();
    }

    public Snack getSnack(String snackName) {
        return snacks.get(snackName);
    }

    public void setInsertedMoney(double insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public double getInsertedMoney() {
        return insertedMoney;
    }

    public void setSelectedSnack(String selectedSnack) {
        this.selectedSnack = selectedSnack;
    }

    public String getSelectedSnack() {
        return selectedSnack;
    }

    public StateOfVendingMachine getCurrentState() {
        return currentState;
    }
}
