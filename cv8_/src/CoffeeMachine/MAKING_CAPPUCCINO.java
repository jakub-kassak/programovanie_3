package CoffeeMachine;

import java.util.concurrent.TimeUnit;

public class MAKING_CAPPUCCINO implements MachineState{

    public MAKING_CAPPUCCINO(){
        System.out.println("Errrrrr!");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Take your cappuccino");
    }
    @Override
    public void switchPower(CoffeeMachine machine) {
        machine.state = new OFF();
    }

    @Override
    public void pressEspresso(CoffeeMachine machine) {}

    @Override
    public void pressCappuccino(CoffeeMachine machine) {}
}
