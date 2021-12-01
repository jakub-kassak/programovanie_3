package CoffeeMachine;

public class OFF implements MachineState{
    @Override
    public void switchPower(CoffeeMachine machine) {
        machine.state = new IDLE();
    }

    @Override
    public void pressEspresso(CoffeeMachine machine) {}

    @Override
    public void pressCappuccino(CoffeeMachine machine) {}
}
