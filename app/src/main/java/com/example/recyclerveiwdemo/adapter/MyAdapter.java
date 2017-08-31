package com.example.recyclerveiwdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerveiwdemo.R;

import java.security.PolicySpi;
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
//        View recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, null);
        View recyclerViewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent,false);
        return new MyViewHolder(recyclerViewItem);
    }

    //添加一条数据的方法
    public void add(int position) {
        list.add(position + 1, "新添加一条数据");

        //刷新界面
        notifyItemRangeChanged(position + 1, getItemCount());
    }

    //长按删除一条数据
    public void remove(int position) {
        list.remove(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    //修改一条数据
    public void update(int position, String content) {
        list.remove(position);
        list.add(position, content);
        notifyItemChanged(position);
    }

    //单击事件的接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    //长按事件的接口
    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, int position);
    }

    //使用两个接口
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    //定义两个方法
    //点击监听
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    //长按监听
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        mItemLongClickListener = itemLongClickListener;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(list.get(position));
        //做点击监听  先注释掉   用默认的图片
        /*if (position % 2 == 1) {
            holder.icon.setImageResource(R.drawable.a);
        }*/

        //整个item条目的点击
        //holder.itemView.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //  public void onClick(View v) {
        ////// TODO: 2017/8/31 暴露一个单击回调接口
        //  if (mItemClickListener != null) {
        //      mItemClickListener.onItemClick(v, position);
        //          }
        //      }
        //  });

        //图片的点击监听
        holder.icon.setOnClickListener(new View.OnClickListener() {
            // TODO: 2017/8/31 0031   要暴露一个接口出去
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });

        //图片的长按监听
        holder.icon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickListener != null) {
                    mItemLongClickListener.onItemLongClick(v, position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //定义一个View 做整个子条目的监听
        View itemView;

        //定义控件的全局变量
        private final TextView title;
        private final ImageView icon;

        //寻找控件
        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = (TextView) itemView.findViewById(R.id.recyclerView_title);
            icon = (ImageView) itemView.findViewById(R.id.recyclerView_icon);
        }
    }
}
