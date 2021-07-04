package dercochenko.com.Garage;

import dercochenko.com.Vehicle.Vehicle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MechanicService implements Fixer {
    private final String PATH = "D:\\AutoparkNewVersion\\src\\dercochenko\\com\\db\\orders.csv";
    public static final String[] details = {"Фильт", "Втулка", "Вал", "Ось", "Свеча", "Масло", "ГРМ", "ШРУС"};

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        File file = new File(PATH);
        Map<String, Integer> vehicleDetailsBreaking = new HashMap<>();

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            int count = (int) (Math.random() * 8);

            if (count > 0) {
                fileWriter.append(String.valueOf(vehicle.getId())).append(",");
                for (int i = 0; i < count; i++) {
                    String detail = details[(int) (Math.random() * 8)];
                    int number = (int) (Math.random() * 4);
                    if (number > 0) {
                        fileWriter.append(detail).append(",").append(String.valueOf(number)).append(',');
                        vehicleDetailsBreaking.put(detail, number);
                    }
                }

                vehicle.setVehicleDetailsBreaking(vehicleDetailsBreaking);
                fileWriter.append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return vehicleDetailsBreaking;
    }

    @Override
    public void repair(Vehicle vehicle) {
        File file = new File(PATH);

        try (Scanner scanner = new Scanner(file)) {
            List<String> arrayList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }

            arrayList = arrayList.stream().filter(x -> Integer.parseInt(String.valueOf(x.charAt(0))) != vehicle.getId()).collect(Collectors.toList());

            FileWriter fileWriter = new FileWriter(file, false);

            for (String s : arrayList) {
                fileWriter.append(s).append("\n");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        File file = new File(PATH);

        try (Scanner scanner = new Scanner(file)) {
            String line;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (vehicle.getId() == Integer.parseInt(String.valueOf(line.charAt(0)))) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return false;
    }
}
