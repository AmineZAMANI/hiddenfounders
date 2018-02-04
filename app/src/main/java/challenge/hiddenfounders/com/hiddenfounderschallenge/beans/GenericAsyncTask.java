package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import challenge.hiddenfounders.com.hiddenfounderschallenge.R;

/**
 * Created by azamani on 27/12/2017.
 */

public class GenericAsyncTask extends AsyncTask<String, Void, String> {

    public static final String TAG = "GenericAsyncTask";

    public static final String JSON_EMPTY_MAP = "{}";
    public static final String PAGE_PARAM = "$page";

    Callback callback;
    private int page;

    public GenericAsyncTask(Callback callback, int page) {
        this.callback = callback;
        this.page = page;
    }

    protected void onPreExecute() {
        this.callback.onPreProcess();
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {
        String result = JSON_EMPTY_MAP;
        try {
            URL url = new URL(params[0].replace(PAGE_PARAM, String.valueOf(page)));
            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            result = buffer.toString();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            this.callback.onError(R.string.loading_data_problem_occurs);
        }
        return result;
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (!result.equals(JSON_EMPTY_MAP)) {
            Log.d(TAG, result);
            callback.onResult(result);
        }
    }
}
