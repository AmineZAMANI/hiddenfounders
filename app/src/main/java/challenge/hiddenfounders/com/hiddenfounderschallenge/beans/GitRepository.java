package challenge.hiddenfounders.com.hiddenfounderschallenge.beans;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import challenge.hiddenfounders.com.hiddenfounderschallenge.R;

public class GitRepository implements Adaptable {

    private DynaMap<String, Object> data;

    public GitRepository(DynaMap<String, Object> data) {
        this.data = data;
    }

    public DynaMap<String, Object> getData() {
        return data;
    }

    public void setData(DynaMap<String, Object> data) {
        this.data = data;
    }

    public int getLayoutId() {
        return R.layout.repository_item;
    }

    public View buildView(View v, LayoutInflater inflater) {
        if (v == null) {
            v = inflater.inflate(getLayoutId(), null);
        }
        TextView author = v.findViewById(R.id.tview_reponame);
        TextView author2 = v.findViewById(R.id.tview_author);
        TextView description = v.findViewById(R.id.tview_description);
        TextView rating = v.findViewById(R.id.tview_rating);
        ImageView photoAuthor = v.findViewById(R.id.imgview_photo);

        author.setText((String) this.getData().getByPath(KEY_AUTHOR));
        author2.setText((String) this.getData().getByPath(KEY_AUTHOR));
        description.setText((String) this.getData().getByPath(KEY_DESCRIPTION));
        rating.setText(String.valueOf(this.getData().getByPath(KEY_RATING)));
        Glide.with(v.getContext()).load(this.getData().getByPath(KEY_PHOTO)).into(photoAuthor);
        return v;
    }



}
