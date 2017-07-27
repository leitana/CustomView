package custom.lx.com.customview.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;
import custom.lx.com.customview.base.BaseRecyclerAdapter;
import custom.lx.com.customview.base.ViewHolder;
import custom.lx.com.customview.utils.SizeUtils;

/**
 * Created by 11300 on 2017/7/26.
 */

public class RecyclerWithBaseAdapter extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Context mContext = RecyclerWithBaseAdapter.this;
    private List<String> mData;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBackBtn();
//        initView();
        setTitle("RecyclerView With BaseAdapter");
        initData();

        rvList.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rvList.setItemAnimator(new DefaultItemAnimator());//设置动画
        myAdapter = new MyAdapter(mContext, R.layout.text_item, mData);
        rvList.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Snackbar.make(view, mData.get(position), Snackbar.LENGTH_SHORT).show();
            }
        });
    }



    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add("item" + i);
        }
    }

    private class MyAdapter extends BaseRecyclerAdapter<String> {

        public MyAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);

        }

        @Override
        public void convert(ViewHolder holder, final String s) {
            final TextView tv_text = holder.getView(R.id.tv_text);
            CardView cardView = holder.getView(R.id.cardview);
            tv_text.setText(s);
            cardView.setMinimumHeight(SizeUtils.dp2px(mContext, getRandom(70, 150)));
//            holder.setOnClickListener(R.id.cardview, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(tv_text, s, Snackbar.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    public static int getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }
}
