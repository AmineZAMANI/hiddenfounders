package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

/**
 * Created by azamani on 27/12/2017.
 */

public interface Callback {
    String KEY_ITEMS = "items";
    String SEARCH_ENDPOINT = "https://api.github.com/search/repositories?q=created:%3E2017-10-22&sort=stars&order=desc&page=$page";

    void onPreProcess();

    void onError(int messageID);
    void onResult(String result);
}
