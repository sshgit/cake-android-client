package com.waracle.androidtest.cakes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.waracle.androidtest.R;
import com.waracle.androidtest.data.Cake;
import com.waracle.androidtest.ui.NetworkImageView;

import java.util.List;

public class CakeAdapter extends BaseAdapter {

    private List<Cake> cakes;
    private final LayoutInflater mInflater;

    public CakeAdapter(Context context) {
        mInflater =  LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cakes != null ? cakes.size() : 0;
    }

    @Override
    public Cake getItem(int position) {
        return cakes != null ? cakes.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CakeViewHolder cakeViewHolder;
        if(convertView != null){
            cakeViewHolder = (CakeViewHolder)convertView.getTag();
        }else {
            convertView = mInflater.inflate(R.layout.list_item_layout, parent, false);
            cakeViewHolder = new CakeViewHolder(convertView);
            convertView.setTag(cakeViewHolder);
        }
        final Cake cake = getItem(position);
        cakeViewHolder.setCake(cake);
        return convertView;
    }

    public void setItems(List<Cake> cakes) {
        this.cakes = cakes;
        notifyDataSetChanged();
    }

    private static class CakeViewHolder{
        final TextView textViewTitle;
        final TextView textViewDesc;
        final ImageView imageView;

        private CakeViewHolder(View itemView){
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDesc = itemView.findViewById(R.id.desc);
            imageView = itemView.findViewById(R.id.image);
        }
        private void setCake(Cake cake){
            textViewTitle.setText(cake.getTitle());
            textViewDesc.setText(cake.getDescription());
            imageView.setImageResource(R.drawable.ic_default_cake);
            imageView.setImageURI(Uri.parse(cake.getImageUrl()));
        }
    }
}