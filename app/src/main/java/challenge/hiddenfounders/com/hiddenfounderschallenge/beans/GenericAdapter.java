package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by azamani on 27/12/2017.
 */

public class GenericAdapter extends BaseAdapter {


    private LayoutInflater inflater;
    private List<Adaptable> items;


    public GenericAdapter(List<Adaptable> items, Context c) {
        this.items = items;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    //Now, using polymorphism, it should return a correctly built
    //view for whatever object type you've passed in.
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        return items.get(pos).buildView(convertView, inflater);
    }
}
