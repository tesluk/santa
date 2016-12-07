package tab.os.yana.santa.service;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author andrew.tesliuk
 */
public class ServiceResponse {

    @SerializedName("s") private int isCorrect;

    @SerializedName("d") private String date;

    @SerializedName("g") private int isMan;

    public boolean isCorrect() {
        return isCorrect==1;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.parse(date);
    }

    public boolean isMan() {
        return isMan==1;
    }

    @Override public String toString() {
        try {
            return String.format("Date = %s   Is man = %s   Is correct = %s", getDate(), isMan(), isCorrect());
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
