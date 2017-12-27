package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.view.LayoutInflater;
import android.view.View;

import java.util.Map;

import challenge.hiddenfounders.com.hiddenfounderschallenge.R;

public class GitRepository implements Adaptable {

    private long id;
    private Map<String, String> data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    //Just hardcode your layout for this type of object
    public int getLayoutId() {
        return R.layout.repository_item;
    }

    //getView() will pass the recycled view to this method
    //which will handle building the view per this object
    public View buildView(View v, LayoutInflater inflater) {
        if (v == null) {
            v = inflater.inflate(getLayoutId(), null);
        }
        return v;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
