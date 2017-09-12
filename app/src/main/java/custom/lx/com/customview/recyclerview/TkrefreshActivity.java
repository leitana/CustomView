package custom.lx.com.customview.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;
import custom.lx.com.customview.base.BaseRecyclerAdapter;
import custom.lx.com.customview.base.ViewHolder;

/**
 * Created by lx on 2017/9/12.
 */

public class TkrefreshActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;

    private MyAdapter myAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkrefresh);
        ButterKnife.bind(this);

        initData();

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(new DefaultItemAnimator());//设置动画
        myAdapter = new MyAdapter(TkrefreshActivity.this, R.layout.text_item, mData);
        recyclerview.setAdapter(myAdapter);
        refreshLayout.setHeaderView(new SinaRefreshView(this));
        refreshLayout.setBottomView(new LoadingView(this));
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                },2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                },2000);
            }
        });
    }

    private class MyAdapter extends BaseRecyclerAdapter<String> {

        public MyAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        public void convert(ViewHolder holder, String s) {
            TextView tv_text = holder.getView(R.id.tv_text);
            tv_text.setText(s);
        }
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mData.add("item" + i);
        }
    }
}
