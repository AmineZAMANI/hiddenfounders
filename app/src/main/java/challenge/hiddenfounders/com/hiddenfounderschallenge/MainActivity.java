package challenge.hiddenfounders.com.hiddenfounderschallenge;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.Adaptable;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.Callback;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.DynaMap;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.GenericAdapter;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.GenericAsyncTask;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.GitRepository;
import challenge.hiddenfounders.com.hiddenfounderschallenge.utils.JsonUtils;

public class MainActivity extends AppCompatActivity implements Callback {


    private LinearLayout parentLayout;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        setupUI();
        new GenericAsyncTask(this).execute(SEARCH_URI);
    }


    public void setupUI() {
        parentLayout = findViewById(R.id.parent_layout);
    }

    @Override
    public void onResult(String result) {
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) JsonUtils.parse(result).get(KEY_ITEMS);
        List<Adaptable> mAdaptableList = new ArrayList<>();
        for (Map<String, Object> mMap : listMap) {
            DynaMap<String, Object> dynaMap = new DynaMap<>();
            dynaMap.putAll(mMap);
            GitRepository adaptable = new GitRepository(dynaMap);
            mAdaptableList.add(adaptable);
        }
        ListView lView = new ListView(context);
        GenericAdapter adapter = new GenericAdapter(mAdaptableList, context);
        lView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        parentLayout.addView(lView);
    }
}
