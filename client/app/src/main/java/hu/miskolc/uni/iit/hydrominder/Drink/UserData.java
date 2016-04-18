package hu.miskolc.uni.iit.hydrominder.Drink;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the actual user's data, who uses this application.
 * This class hold the user's name, drink list, etc.
 *
 * Created by Patrik on 2016.04.18..
 */
public class UserData {

    /**
     * This field hold the name of the user.
     */
    private String name = "DefaultBéla";

    /**
     * This field is the last 10 datepoints, when the user drinked.
     */
    private List<DrinkTime> drinks = new LinkedList<DrinkTime>();

    /**
     * This field is used to determine what kind of mode the user uses.
     * Modes: - fastDrink (1 hours)
     *        - mediumDrink (2 hours)
     *        - slowDrink (4 hours)
     *        - unique (x hours depends on the user)
     *        - default (2 hours) if none of above them of is chosen.
     */
    private String drinkingMode = "default";

    private double drinkFrequency = 2.0;

    public UserData() {

    }

    public UserData(String name, String drinkingMode) {
        this.name = name;
        this.drinks = drinks;
        this.drinkingMode = drinkingMode;
        switch (drinkingMode) {
            case "fastDrink" :
                drinkFrequency = 1.0;
                break;
            case "mediumDrink":
                this.drinkFrequency = 2.0;
                break;
            case "slowDrink" :
                this.drinkFrequency = 4.0;
                break;
            default:
                this.drinkingMode = "default";
                this.drinkFrequency = 2.0;
                break;
        }
    }

    public UserData(String name, List<DrinkTime> drinks, String drinkingMode) {
        this.name = name;
        this.drinks = drinks;
        this.drinkingMode = drinkingMode;
        switch (drinkingMode) {
            case "fastDrink" :
                drinkFrequency = 1.0;
                break;
            case "mediumDrink":
                this.drinkFrequency = 2.0;
                break;
            case "slowDrink" :
                this.drinkFrequency = 4.0;
                break;
            default:
                this.drinkingMode = "default";
                this.drinkFrequency = 2.0;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DrinkTime> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkTime> drinks) {
        this.drinks = drinks;
    }

    public String getDrinkingMode() {
        return drinkingMode;
    }

    public void setDrinkingMode(String drinkingMode) {
        this.drinkingMode = drinkingMode;
    }

    public double getDrinkFrequency() {
        return drinkFrequency;
    }

    public void setDrinkFrequency(double drinkFrequency) {
        this.drinkFrequency = drinkFrequency;
    }

    /**
     * This methode gives back the given element of the array.
     * @param number The number-1. element of the drinklist array.
     * @return
     */
    public DrinkTime getDrinkTime(int number) {
        return drinks.get(number);
    }

    public void setlastDrink(DrinkTime drink) {
        drinks.remove(0);
        drinks.add(drink);
    }
    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", drinks=" + drinks +
                ", drinkingMode='" + drinkingMode + '\'' +
                ", drinkFrequency=" + drinkFrequency +
                '}';
    }
}
