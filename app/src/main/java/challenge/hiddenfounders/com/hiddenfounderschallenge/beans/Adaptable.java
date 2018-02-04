package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.view.LayoutInflater;
import android.view.View;

import challenge.hiddenfounders.com.hiddenfounderschallenge.R;

/*
Interface that any object you make that should be put in a ListView should implement
*/
public interface Adaptable {

    String KEY_AUTHOR = "owner.login";
    String KEY_DESCRIPTION = "description";
    String KEY_RATING = "score";
    String KEY_PHOTO = "owner.avatar_url";
    String RATING_SUFFIX = "k";
    int RATING_PREFIX = R.string.fa_star;


    View buildView(View v, LayoutInflater inflater);

    int getLayoutId();

}