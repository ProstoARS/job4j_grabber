package ru.job4j.ood.ocp;


public class Credit {

    public void consumerCredit(ValidateConsumerCredit validate) {
        if (validate.isValid()) {
            System.out.println("Credit approved");
        }
    }
    public void vehicleCredit(ValidateVehicleCredit validate) {
        if (validate.isValid()) {
            System.out.println("Credit approved");
        }
    }

}

class ValidateConsumerCredit {
    public boolean isValid() {
        return true;
    }
}

class ValidateVehicleCredit {
    public boolean isValid() {
        return true;
    }
}


