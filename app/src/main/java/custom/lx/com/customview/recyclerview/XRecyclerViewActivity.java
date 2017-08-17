package custom.lx.com.customview.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
 * Created by 11300 on 2017/8/11.
 */

public class XRecyclerViewActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    XRecyclerView recyclerview;

    private Context mContext = XRecyclerViewActivity.this;
    private List<String> mData;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xrecycler);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("RecyclerView With BaseAdapter");
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        recyclerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recyclerview.setArrowImageView(R.mipmap.iconfont_downgrey);

        myAdapter = new MyAdapter(mContext, R.layout.text_item, mData);
        recyclerview.setAdapter(myAdapter);

        myAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                Snackbar.make(view, mData.get(position), Snackbar.LENGTH_SHORT).show();
            }
        });
        recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        recyclerview.refreshComplete();
                        myAdapter.notifyDataSetChanged();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                // load more data here
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        recyclerview.loadMoreComplete();
                        myAdapter.notifyDataSetChanged();
                    }
                }, 2000);
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
        public void convert(ViewHolder holder, String s) {
            final TextView tv_text = holder.getView(R.id.tv_text);
            CardView cardView = holder.getView(R.id.cardview);
            tv_text.setText(s);
            cardView.setMinimumHeight(SizeUtils.dp2px(mContext, getRandom(70, 150)));
        }
    }
    public static int getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }
}
