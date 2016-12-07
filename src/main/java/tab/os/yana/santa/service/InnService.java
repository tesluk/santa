package tab.os.yana.santa.service;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author andrew.tesliuk
 */
public class InnService {

    private static final String API_URL = "http://ur.biz.ua/api/inn_api.php";

    private InnService(){}

    public static ServiceResponse analizeINN(String inn) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        String params = "inn="+inn;

        con.setDoOutput(true);
        DataOutputStream dw = new DataOutputStream(con.getOutputStream());
        dw.writeBytes(params);
        dw.flush();
        dw.close();

        String res = IOUtils.toString(con.getInputStream());

        return new Gson().fromJson(res, ServiceResponse.class);
    }

}
