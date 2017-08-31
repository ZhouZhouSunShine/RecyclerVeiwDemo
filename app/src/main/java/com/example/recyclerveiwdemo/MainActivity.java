package com.example.recyclerveiwdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.recyclerveiwdemo.adapter.MyAdapter;

/**
 * RecyclerView
 *
 * 先导一个jar包 然后更改版本号 要与自己App中的版本号一致
 * 建一个adapter包  创建adapter类
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示当前页面布局
        setContentView(R.layout.activity_main);
        //获取资源ID
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //最初始的布局  如：listview  向下排列
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);*/

        //第二个布局  横向排列
        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);*/

        //第三个布局形式  GridView   第二个参数表示显示几列
        /*GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);*/

        linearLayoutManager = new LinearLayoutManager(this);

        gridLayoutManager = new GridLayoutManager(this,3);
        //设置条目的样式
        /*gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //看跨度   第二个参数是几  就是几个跨度
                return 3 - position % 3;
            }
        });*/

        //瀑布流的形式
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //瀑布流的形式
        //recyclerView.setLayoutManager(staggeredGridLayoutManager);

        //做点击监听
        recyclerView.setLayoutManager(linearLayoutManager);

        //设置适配器
        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);

        //设置点击
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                /*//调用添加数据的方法
                myAdapter.add(position);*/
                //调用全选的方法
                myAdapter.selectedAll();
//                Toast.makeText(MainActivity.this, "图片的点击监听" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //设置长按点击
        myAdapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int position) {
                //删除和修改的方法
                /*//myAdapter.remove(position);
                myAdapter.update(position,"修改的数据");*/
//                Toast.makeText(MainActivity.this, "图片的长按监听" + position, Toast.LENGTH_SHORT).show();
                //反选的方法
                myAdapter.revertSelected();
                return true;
            }
        });

        //给ListView形式的添加线条
        recyclerView.addItemDecoration(new MyDecoration(this,LinearLayoutManager.VERTICAL));
        //给GridView形式的添加线条
        recyclerView.addItemDecoration(new MyDecoration(this,GridLayoutManager.HORIZONTAL));
    }

    //点击监听的修饰符一定是公共的
    public void onClick(View view){
        //切换布局
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            return;
        }
        //if和elseif是有先后顺序的,先判断范围小的,然后再判断范围大的,
        //因为GridLayoutManager 是继承 LinearLayoutManager ,所以他本质上也是LinearLayoutManager,
        //所以不能先判断是否是LinearLayoutManager (LinearLayoutManager范围大)
        if (layoutManager instanceof GridLayoutManager) {
            //初始的
            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else if (layoutManager instanceof LinearLayoutManager) {
            //初始的
            //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        myAdapter.notifyDataSetChanged();
    }
}
