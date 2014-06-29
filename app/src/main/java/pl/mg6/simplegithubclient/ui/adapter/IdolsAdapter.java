package pl.mg6.simplegithubclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pl.mg6.simplegithubclient.R;
import pl.mg6.simplegithubclient.data.User;

public final class IdolsAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private final List<User> idols;

    public IdolsAdapter(Context context, List<User> idols) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.idols = idols;
    }

    @Override
    public int getCount() {
        return idols.size();
    }

    @Override
    public User getItem(int position) {
        return idols.get(position);
    }

    @Override
    public long getItemId(int position) {
        return idols.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.idol_item, parent, false);
        }
        ImageView avatar = (ImageView) convertView.findViewById(R.id.idol_avatar);
        TextView name = (TextView) convertView.findViewById(R.id.idol_name);
        User idol = getItem(position);
        Picasso.with(context)
                .load(idol.getAvatarUrl())
                .into(avatar);
        name.setText(idol.getLoginUppercase());
        return convertView;
    }
}
