package tab.os.yana.santa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author andrew.tesliuk
 */
public class INNValidator {

    private static final int[] coefs = new int[] {-1, 5, 7, 9, 4, 6, 10, 5, 7};
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy.MM.dd");


    private INNValidator() {
    }

    public static boolean isValidINN(String num) {
        if (num.length() != 10) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += coefs[i] * Integer.parseInt(num.substring(i, i + 1));
        }

        sum = sum % 11 % 10;

        return sum == Integer.parseInt(num.substring(9));
    }

    public static boolean isValidCorrespondingDate(String num, String dateStr) {
        try {
            Date d = FORMAT.parse(dateStr);
            return isValidCorrespondingDate(num, d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isValidCorrespondingDate(String num, Date date) {
        int days = Integer.parseInt(num.substring(0, 5)) - 2;

        long diff = 0;
        try {
            diff = date.getTime() - FORMAT.parse("1900.01.01").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long res = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println(days + " - " + res);
        return (days - res < 3) && (days - res > -3);
    }

}
