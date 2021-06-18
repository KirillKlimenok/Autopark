package dercochenko.com;


public class Main {

    public static void main(String[] args) {
        VehicleType[] types = new VehicleType[]{
                new VehicleType("Bus", 1.2),
                new VehicleType("Car", 1),
                new VehicleType("Rink", 1.5),
                new VehicleType("Tractor", 1.2)};

        display(types);

        //set tax coefficient in last object
        types[types.length - 1].setTaxCoefficient(1.3);

        display(types);

        VehicleType v = types[0];

        for (VehicleType t :
                types) {
            if (t.getTaxCoefficient() > v.getTaxCoefficient()) {
                v = t;
            }
        }

        System.out.println(v);

        double averageTaxCoefficient = 0;
        for (VehicleType t :
                types) {
            averageTaxCoefficient += t.getTaxCoefficient();
        }

        System.out.println(averageTaxCoefficient / types.length);

        for (VehicleType t :
                types) {
            if (t.getTaxCoefficient() > v.getTaxCoefficient()) {
                v = t;
            }

            averageTaxCoefficient += t.getTaxCoefficient();
        }

        System.out.println(v + "\n" + averageTaxCoefficient);

    }

    public static void display(VehicleType[] types) {
        for (VehicleType e :
                types) {
            System.out.println(e);
        }
    }


}
