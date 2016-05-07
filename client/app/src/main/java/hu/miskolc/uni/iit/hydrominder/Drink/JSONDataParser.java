/*
package hu.miskolc.uni.iit.hydrominder.Drink;

import android.util.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

*/
/**
 * JSON PATTERN:
 * {
 *     "name" : "user name",
 *     "mode" : "drinkMode",
 *     "frequency" : DecimalNumber in hours// example: "frequency" : 2.5
 *     "drinks" : {
 *              drink0 : "2016-05-04T13:43:12",
 *              drink1 : "date1",
 *              drink2 : "date2",
 *              drink3 : "date3",
 *              drink4 : "date4",
 *              drink5 : "date5",
 *              drink5 : "date5",
 *              drink6 : "date6",
 *              drink7 : "date7",
 *              drink8 : "date8",
 *              drink9 : "date9",
 *     }
 * }
 * Created by Patrik on 2016.04.18..
 *//*

public class JSONDataParser {

    public static UserData parseTheUser(InputStream is) {
        JsonReader reader = null;
        try {
             reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
            return returnParsedUserData(reader);
        } catch (UnsupportedEncodingException e) {

        }
        finally {
            try {
                reader.close();
            } catch (Exception e) {

            }
        }
        return null;
    }

    private static UserData returnParsedUserData(JsonReader reader) {

        UserData ud = new UserData();
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String key = reader.nextName();
                switch (key) {
                    case "name":
                        ud.setName(reader.nextString());
                        break;
                    case "mode":
                        ud.setDrinkingMode(reader.nextString());
                        break;
                    case "frequency":
                        ud.setDrinkFrequency(reader.nextDouble());
                        break;
                    case "drinks":
                        ud.setDrinks(readTimes(reader));
                        break;
                    default:
                }
            }
            reader.endObject();
        } catch (Exception e) {

        }
    return null;
    }

    private static List<Reminder> readTimes(JsonReader reader) {
        List<Reminder> list = new LinkedList<Reminder>();
        try {
            reader.beginObject();
            while(reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("drink0") || name.equals("drink1") ||name.equals("drink2") || name.equals("drink2")
                        || name.equals("drink3") || name.equals("drink4") || name.equals("drink5") || name.equals("drink6")
                || name.equals("drink7") || name.equals("drink8") || name.equals("drink9")) {
                    list.add(new Reminder(reader.nextString()));
                }
            }
            reader.endObject();
        } catch(Exception e) {

        }

        return list;
    }
}
*/
