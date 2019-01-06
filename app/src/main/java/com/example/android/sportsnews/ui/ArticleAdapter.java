package com.example.android.sportsnews.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.sportsnews.R;
import com.example.android.sportsnews.pojo.Article;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {

    private List<Article> list;
    private LayoutInflater LInflater;

    public ArticleAdapter(List<Article> list, Context context) {
        this.list = list;
        LInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Article getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ArticleAdapter.ViewHolder holder;

        View v = convertView;

        if (v == null || v.getTag() == null){
            holder = new ArticleAdapter.ViewHolder();
            v = LInflater.inflate(R.layout.item_info, parent, false);
            holder.header =  v.findViewById(R.id.header);
            holder.text =  v.findViewById(R.id.text);


            v.setTag(holder);
        }

        holder = (ArticleAdapter.ViewHolder) v.getTag();

        Article article = getItem(position);

        if (article.getHeader() == null || article.getHeader().equals("")) {
            holder.header.setVisibility(View.GONE);
        } else {
            holder.header.setText(article.getHeader());
        }
        holder.text.setText(article.getText());


        return v;
    }

    private static class ViewHolder {
        private TextView header;
        private TextView text;

    }
}
