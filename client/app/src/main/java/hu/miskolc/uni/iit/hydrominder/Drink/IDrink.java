package hu.miskolc.uni.iit.hydrominder.Drink;

import java.util.*;

/**
 * Created by Patrik on 2016.03.04..
 *
 * Az adott ivasi idopontokat eltarolo kozos interface
 * Majd meg megalmodom hogy nezzen ki
 */
public interface IDrink {

    /**
     * Egy adott idopont hozzaadasa
     * @Param time egy adott ora
     * @Param minute az adott perc
     */
    void addDrink(int hour, int minute);

    void addDrink(Date time);

    Date getDrinkTime();

    /**
     * Egy adott idopont lekerese
     */
    Date getDrinkTime(Date time);

}
