package com.example.android.sportsnews.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.sportsnews.R;
import com.example.android.sportsnews.pojo.Event;
import com.example.android.sportsnews.pojo.EventsList;

public class EventsAdapter extends BaseAdapter {

    private EventsList list;
    private LayoutInflater LInflater;

    public EventsAdapter(EventsList list, Context context) {
        this.list = list;
        LInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.getEvents().size();
    }

    @Override
    public Event getItem(int position) {
        return list.getEvents().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        View v = convertView;

        if (v == null || v.getTag() == null){
            holder = new ViewHolder();
            v = LInflater.inflate(R.layout.item_events, parent, false);
            holder.title =  v.findViewById(R.id.title);
            holder.time =  v.findViewById(R.id.time);
            holder.coefficient =  v.findViewById(R.id.coefficient);
            holder.place =  v.findViewById(R.id.place);
            holder.preview = v.findViewById(R.id.preview);

            v.setTag(holder);
        }

        holder = (ViewHolder) v.getTag();

        Event event = getItem(position);

        holder.title.setText(event.getTitle());
        holder.time.setText(event.getTime());
        holder.coefficient.setText(event.getCoefficient());
        holder.place.setText(event.getPlace());
        holder.preview.setText(event.getPreview());

        return v;
    }

    private static class ViewHolder {
        private TextView title;
        private TextView time;
        private TextView coefficient;
        private TextView place;
        private TextView preview;
    }
}
