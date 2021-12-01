package CoffeeMachine;

public class CoffeeMachine {
    MachineState state = new OFF();
    public void switchPower(){
        state.switchPower(this);
    }
    public void pressEspresso(){
        state.pressEspresso(this);
    }
    public void pressCappuccino(){
        state.pressCappuccino(this);
    }
}
