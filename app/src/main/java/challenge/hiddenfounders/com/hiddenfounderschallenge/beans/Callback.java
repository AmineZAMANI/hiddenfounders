package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

/**
 * Created by azamani on 27/12/2017.
 */

public interface Callback {
    public static final String KEY_ITEMS = "items";
    public static final String SEARCH_URI = "https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc";
    void onResult(String result);
}
