package Main;

public class Driver {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // Setting up chain of responsibility
        SnackDispenseHandler cokeHandler = new SnackDispenseHandler();
        SnackDispenseHandler pepsiHandler = new SnackDispenseHandler();
        SnackDispenseHandler cheetosHandler = new SnackDispenseHandler();
        SnackDispenseHandler doritosHandler = new SnackDispenseHandler();
        SnackDispenseHandler kitkatHandler = new SnackDispenseHandler();
        SnackDispenseHandler snickersHandler = new SnackDispenseHandler();

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);

        // Setting up vending machine with chain of responsibility
        vendingMachine.changeState(new IdleState(vendingMachine));
        snickersHandler.setNextHandler(null); // End of chain

        // Test cases
        vendingMachine.selectSnack("Snickers"); // Snickers with low quantity
        vendingMachine.insertMoney(1.25);
        vendingMachine.dispenseSnack();
        vendingMachine.selectSnack("Snickers"); // Snickers with low quantity
        vendingMachine.selectSnack("Coke"); // Coke available
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack(); // Should display "Dispensing Coke"
        vendingMachine.selectSnack("KitKat"); // KitKat available
        vendingMachine.insertMoney(1.25);
        vendingMachine.dispenseSnack();
        vendingMachine.selectSnack("Coke"); // Coke available
        vendingMachine.insertMoney(2.0);
        vendingMachine.dispenseSnack(); // Should display "Dispensing Coke"
    }
}

