package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import challenge.hiddenfounders.com.hiddenfounderschallenge.utils.JsonUtils;

/**
 * Created by azamani on 11/01/2018.
 */

public class DynaMap<String, Object> extends HashMap {


    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean containsKey(java.lang.Object o) {
        return super.containsKey(o);
    }

    @Override
    public boolean containsValue(java.lang.Object o) {
        return super.containsValue(o);
    }

    @Override
    public java.lang.Object get(java.lang.Object o) {
        return super.get(o);
    }

    @Override
    public java.lang.Object put(java.lang.Object o, java.lang.Object o2) {
        return super.put(o, o2);
    }

    @Override
    public java.lang.Object remove(java.lang.Object o) {
        return super.remove(o);
    }

    @Override
    public void putAll(@NonNull Map map) {
        super.putAll(map);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @NonNull
    @Override
    public Set keySet() {
        return super.keySet();
    }

    @NonNull
    @Override
    public Collection values() {
        return super.values();
    }

    @NonNull
    @Override
    public Set<Entry> entrySet() {
        return super.entrySet();
    }


    public java.lang.Object getByPath(java.lang.String path) {
        return JsonUtils.findByPath(this, path);
    }


}
