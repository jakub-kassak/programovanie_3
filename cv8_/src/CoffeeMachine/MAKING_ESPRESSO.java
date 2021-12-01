package CoffeeMachine;

import java.util.concurrent.TimeUnit;

public class MAKING_ESPRESSO implements MachineState{

    public MAKING_ESPRESSO(){
        System.out.println("Errrrrr!");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Take your esspresso");
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
