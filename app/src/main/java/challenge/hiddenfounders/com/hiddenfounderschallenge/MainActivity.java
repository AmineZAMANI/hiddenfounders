package challenge.hiddenfounders.com.hiddenfounderschallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.Callback;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.GenericAsyncTask;
import challenge.hiddenfounders.com.hiddenfounderschallenge.utils.JsonUtils;

public class MainActivity extends AppCompatActivity implements Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GenericAsyncTask(this).execute("https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc");
    }

    @Override
    public void onResult(String result) {
        System.out.print(JsonUtils.parse(result).get("items"));
    }
}
