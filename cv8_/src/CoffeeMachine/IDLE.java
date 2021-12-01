package CoffeeMachine;

public class IDLE implements MachineState{
    @Override
    public void switchPower(CoffeeMachine machine) {
        machine.state = new OFF();
    }

    @Override
    public void pressEspresso(CoffeeMachine machine) {
        machine.state = new MAKING_ESPRESSO();
    }

    @Override
    public void pressCappuccino(CoffeeMachine machine) {
        machine.state = new MAKING_CAPPUCCINO();
    }
}
