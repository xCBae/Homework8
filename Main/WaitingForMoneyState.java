package Main;

public class WaitingForMoneyState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public WaitingForMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Cannot select another snack. Waiting for money.");
    }

    @Override
    public void insertMoney(double amount) {
        Snack selectedSnack = vendingMachine.getSnack(vendingMachine.getSelectedSnack());
        if (selectedSnack != null) {
            if (amount >= selectedSnack.getPrice()) {
                System.out.println("Going from Waiting For Money to Dispensing Snack state");
                vendingMachine.setInsertedMoney(amount);
                vendingMachine.changeState(new DispensingSnackState(vendingMachine));
            } else {
                System.out.println("Insufficient money inserted.");
            }
        } else {
            System.out.println("Please select a snack first.");
        }
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Money not inserted yet.");
    }
}
