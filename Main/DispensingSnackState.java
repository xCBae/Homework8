package Main;

public class DispensingSnackState implements StateOfVendingMachine {
    private VendingMachine vendingMachine;

    public DispensingSnackState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectSnack(String snackName) {
        System.out.println("Currently dispensing a snack. Please wait.");
    }

    @Override
    public void insertMoney(double amount) {
        System.out.println("Currently dispensing a snack. Please wait.");
    }

    @Override
    public void dispenseSnack() {
        String selectedSnackName = vendingMachine.getSelectedSnack();
        if (selectedSnackName != null) {
            Snack selectedSnack = vendingMachine.getSnack(selectedSnackName);
            if (selectedSnack != null && selectedSnack.getQuantity() > 0) {
                System.out.println("Going from Dispensing to Idle state");
                System.out.println("Current handler of " + selectedSnack.getName());
                System.out.println("\nDispensing: " + selectedSnack.getName());
                selectedSnack.setQuantity(selectedSnack.getQuantity() - 1);
                double change = vendingMachine.getInsertedMoney() - selectedSnack.getPrice();
                if (change > 0) {
                    System.out.println("Your change is: $" + change + "\n");
                } else {
                    System.out.println("Your change is: $0.0\n");
                }
                vendingMachine.setInsertedMoney(0);
                vendingMachine.setSelectedSnack(null);
                vendingMachine.changeState(new IdleState(vendingMachine));
            } else {
                System.out.println("\nWe do not have " + selectedSnackName);
                vendingMachine.changeState(new IdleState(vendingMachine));
            }
        } else {
            System.out.println("No snack selected. Please select a snack first.");
        }
    }
}
