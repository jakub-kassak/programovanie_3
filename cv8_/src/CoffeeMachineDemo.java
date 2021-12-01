import CoffeeMachine.CoffeeMachine;

import java.util.concurrent.TimeUnit;

public class CoffeeMachineDemo {
    public static void main(String[] args) throws InterruptedException {
        CoffeeMachine machine = new CoffeeMachine();
        machine.switchPower();
        machine.pressCappuccino();
        machine.pressEspresso();
        TimeUnit.SECONDS.sleep(3);
        machine.switchPower();
        machine.pressEspresso();
    }
}
