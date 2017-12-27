package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by azamani on 27/12/2017.
 */

public class GenericAsyncTask extends AsyncTask<String, Void, String> {

    public static final String JSON_EMPTY_MAP = "{}";
    public static final String JSON_EMPTY_LIST = "[]";

    Callback callback;

    public GenericAsyncTask(Callback callback) {
        this.callback = callback;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {
        String result = JSON_EMPTY_MAP;
        try {
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
        } catch (Exception e) {

        }
        return result;
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        callback.onResult(result);
    }
}
