package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.view.LayoutInflater;
import android.view.View;

public interface Adaptable {

    public static final String KEY_REPONAME = "name";
    public static final String KEY_AUTHOR = "owner.login";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_RATING = "score";
    public static final String KEY_PHOTO = "owner.avatar_url";

    //Interface that any object you make that should be put in a listview
    //should implement
    public View buildView(View v, LayoutInflater inflater);

    public int getLayoutId();

}