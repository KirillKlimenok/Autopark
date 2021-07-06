package dercochenko.com;

import dercochenko.com.Garage.MechanicService;
import dercochenko.com.Vehicle.DefectedVehicleException;
import dercochenko.com.Vehicle.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentAuto {
    MechanicService mechanicService;

    public RentAuto(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    public boolean rentAuto(Vehicle v, double money) throws DefectedVehicleException {
        if (mechanicService.isBroken(v)) {
            throw new DefectedVehicleException("This auto detected, have detected: " + v.getCountDetected());
        } else {
            try (FileWriter fileWriter = new FileWriter("D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\rents.csv", true)) {
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern("dd.MM.yyyy");

                fileWriter.append(String.valueOf(v.getId())).append(",").append(format.format(new Date())).append(",").append(String.valueOf(money)).append('\n');
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
    }

    public boolean rentAuto(Vehicle v, double money, String date) throws DefectedVehicleException {
        if (mechanicService.isBroken(v)) {
            throw new DefectedVehicleException("This car has detected: " + v.getCountDetected());
        } else {
            try (FileWriter fileWriter = new FileWriter("D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\rents.csv", true)) {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                date = format.format(date);

                fileWriter.append(String.valueOf(v.getId())).append(",").append(date).append(",").append(String.valueOf(money)).append('\n');
            } catch (IOException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
    }
}
