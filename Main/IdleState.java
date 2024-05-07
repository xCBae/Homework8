package Main;

public class IdleState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        Snack selectedSnack = vendingMachine.getSnack(snackName);
        if (selectedSnack != null && selectedSnack.getQuantity() > 0) {
            System.out.println("Remaining quantity of " + selectedSnack.getName() + ": " + selectedSnack.getQuantity());
            System.out.println("We have: " + snackName);
            System.out.println("\nTransitioning from Idle to Waiting For Money state");
            vendingMachine.setSelectedSnack(snackName);
            vendingMachine.changeState(new WaitingForMoneyState(vendingMachine));
        } else {
            System.out.println("Sorry we are out of: " + snackName + "\n");
        }
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Please select a snack first.");
    }

    @Override
    public void dispenseSnack() {
        System.out.println("Please select a snack first.");
    }
}
