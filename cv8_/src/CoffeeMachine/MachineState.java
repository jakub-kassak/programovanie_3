package CoffeeMachine;

public interface MachineState {
    void switchPower(CoffeeMachine machine);
    void pressEspresso(CoffeeMachine machine);
    void pressCappuccino(CoffeeMachine machine);
}
