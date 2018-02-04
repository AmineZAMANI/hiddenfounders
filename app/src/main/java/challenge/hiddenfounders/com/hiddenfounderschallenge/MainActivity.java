package challenge.hiddenfounders.com.hiddenfounderschallenge;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.Adaptable;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.Callback;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.DynaMap;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.GenericAdapter;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.GenericAsyncTask;
import challenge.hiddenfounders.com.hiddenfounderschallenge.beans.GitRepository;
import challenge.hiddenfounders.com.hiddenfounderschallenge.utils.AssetsUtils;
import challenge.hiddenfounders.com.hiddenfounderschallenge.utils.JsonUtils;

public class MainActivity extends AppCompatActivity implements Callback {


    /*
    PAGES_THRESHOLD to specify the max of loaded pages.
     */
    private final int PAGES_THRESHOLD = 1000;
    private LinearLayout parentLayout;
    private ListView lView;
    private List<Adaptable> mAdaptableList = new ArrayList<>();
    private Context context;
    private TextView footerView;
    private TextView textViewLoading;
    private int page = 1;
    private Animation mFadeInAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mFadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        setupUI();
        callWS();
    }

    private void callWS() {
        new GenericAsyncTask(this, MainActivity.this.page).execute(SEARCH_ENDPOINT);
    }

    public void setupUI() {
        parentLayout = findViewById(R.id.parent_layout);
        textViewLoading = findViewById(R.id.loadingTxtView);
        textViewLoading.setText(getString(R.string.please_wait));
        textViewLoading.setAnimation(mFadeInAnimation);
        AssetsUtils.makeAwesome(textViewLoading);
        lView = new ListView(context);
        lView.setOnScrollListener(new ScrollerListener());
        parentLayout.addView(lView);
        /*
            Initializing footer view
         */
        footerView = new TextView(context);
        AssetsUtils.makeAwesome(footerView);
        footerView.setText(getString(R.string.loading_more_elements));
        footerView.setTextSize(20);
        footerView.setPadding(10, 10, 10, 10);
        footerView.setGravity(Gravity.CENTER);
        lView.addFooterView(footerView);
        footerView.setVisibility(View.GONE);
    }

    @Override
    public void onError(final int message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, context.getString(message), Toast.LENGTH_SHORT).show();
                textViewLoading.setText(getString(R.string.try_again));
                textViewLoading.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.this.page = 1;
                        textViewLoading.setText(getString(R.string.please_wait));
                        callWS();
                    }
                });
            }
        });
    }

    @Override
    public void onPreProcess() {
        /*
        Making footer view appear while loading more elements ...
         */
        footerView.setVisibility(View.VISIBLE);
        footerView.startAnimation(mFadeInAnimation);
    }

    @Override
    public void onResult(String result) {
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) JsonUtils.parse(result).get(KEY_ITEMS);
        int perPage = 0;
        for (Map<String, Object> mMap : listMap) {
            DynaMap<String, Object> dynaMap = new DynaMap<>();
            dynaMap.putAll(mMap);
            GitRepository adaptable = new GitRepository(dynaMap);
            mAdaptableList.add(adaptable);
            perPage++;
        }
        GenericAdapter adapter = new GenericAdapter(mAdaptableList, context);
        lView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        lView.setSelection(lView.getCount() - perPage);
        footerView.setVisibility(View.GONE);
        if (footerView.getAnimation() != null) {
            footerView.getAnimation().cancel();
        }
        textViewLoading.setVisibility(View.GONE);
    }


    class ScrollerListener implements AbsListView.OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            int threshold = 1;
            int count = lView.getCount();
            if (scrollState == SCROLL_STATE_IDLE) {
                if (lView.getLastVisiblePosition() >= count - threshold && MainActivity.this.page < PAGES_THRESHOLD) {
                    MainActivity.this.page++;
                    callWS();
                }
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }
}
