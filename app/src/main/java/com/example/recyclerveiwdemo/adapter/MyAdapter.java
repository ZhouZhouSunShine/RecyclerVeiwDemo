package com.example.recyclerveiwdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerveiwdemo.R;

import java.util.ArrayList;

/**
 * Created by 范晋炜 on 2017/8/30 0030.
 * com.example.recyclerveiwdemo.adapter
 * MyAdapter
 * <p>
 * 创建好布局
 * 创建一个MyViewHolder
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //创建一个集合
    ArrayList<String> list;

    public MyAdapter() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("条目" + i);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, null);
        return new MyViewHolder(recyclerViewItem);
    }

    //绑定数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(list.get(position));
        if (position % 2 == 1) {
            holder.icon.setImageResource(R.drawable.a);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //定义控件的全局变量
        private final TextView title;
        private final ImageView icon;

        //寻找控件
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.recyclerView_title);
            icon = (ImageView) itemView.findViewById(R.id.recyclerView_icon);
        }
    }
}
