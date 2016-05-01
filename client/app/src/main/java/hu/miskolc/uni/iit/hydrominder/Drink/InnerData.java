package hu.miskolc.uni.iit.hydrominder.Drink;

/**
 * Created by Patrik on 2016.05.01..
 */
public class InnerData {

    private static UserData userData;

    private static boolean ml;

    public static void setMetrics(boolean met) {
        ml = met;
    }

    public static boolean getMetrics() {
        return ml;
    }
    public static UserData  getUserData() {
        return InnerData.userData;
    }

    public static void setData(UserData data) {
        userData = data;
    }
}
