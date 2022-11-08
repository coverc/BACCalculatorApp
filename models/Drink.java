

package edu.uncc.hw04.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Drink {
    public int drinkSize, alcoholPercentage;
    public String addedOn;
    public Date dateAdded;


    public Drink(int drinkSize, int alcoholPercentage, String addedOn, Date dateAdded) {
        this.drinkSize = drinkSize;
        this.alcoholPercentage = alcoholPercentage;
        this.addedOn = addedOn;
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "addedOn=" + addedOn +
                ", drinkSize=" + drinkSize +
                ", alcoholPercentage=" + alcoholPercentage + dateAdded +
                '}';
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public double getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(int drinkSize) {
        this.drinkSize = drinkSize;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(int alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setAddedOn(Date dateAdded) {
        this.dateAdded = dateAdded;
    }


}
