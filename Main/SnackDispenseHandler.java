package Main;

public class SnackDispenseHandler {
    private SnackDispenseHandler nextHandler;

    public void setNextHandler(SnackDispenseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(String snackName, VendingMachine vendingMachine) {
        Snack selectedSnack = vendingMachine.getSnack(snackName);
        if (selectedSnack != null && selectedSnack.getQuantity() > 0) {
            vendingMachine.changeState(new WaitingForMoneyState(vendingMachine));
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(snackName, vendingMachine);
            } else {
                System.out.println("Selected snack not available.");
                vendingMachine.changeState(new IdleState(vendingMachine));
            }
        }
    }
}
