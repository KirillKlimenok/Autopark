package dercochenko.com.Vehicle;

import java.util.Date;

public class Rent {
    private final int idVehicle;
    private Date rentalDate;
    private double rentalPrice;

    public Rent(int idVehicle, Date rentalDate, float rentalPrice) {
        this.idVehicle = idVehicle;
        this.rentalDate = rentalDate;
        this.rentalPrice = rentalPrice;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public int getIdVehicle() {
        return idVehicle;
    }
}
