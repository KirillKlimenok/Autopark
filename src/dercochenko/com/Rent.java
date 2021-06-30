package dercochenko.com;

import java.util.Date;

public class Rent {
    private Date rentalDate;
    private float rentalPrice;

    public Rent(Date rentalDate, float rentalPrice) {
        this.rentalDate = rentalDate;
        this.rentalPrice = rentalPrice;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}
