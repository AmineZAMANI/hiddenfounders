package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.view.LayoutInflater;
import android.view.View;

public interface Adaptable {
    //Interface that any object you make that should be put in a listview
    //should implement
    public View buildView(View v, LayoutInflater inflater);

    public int getLayoutId();

    public long getId();
}