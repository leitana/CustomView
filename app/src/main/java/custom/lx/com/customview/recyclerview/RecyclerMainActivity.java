package custom.lx.com.customview.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;
import custom.lx.com.customview.beans.HomeBean;

/**
 * Created by 11300 on 2017/7/19.
 */

public class RecyclerMainActivity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Context mContext = RecyclerMainActivity.this;
    private List<HomeBean> recyclerList;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("RecyclerView Samples");
        initData();
        rvList.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(R.layout.list_item, recyclerList);
        rvList.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    Intent intent = new Intent(mContext, NativeRecyclerActicity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initData() {
        recyclerList = new ArrayList<>();
        HomeBean homeBean = new HomeBean();
        homeBean.setTitle("Native base");
        homeBean.setContent("Native base recycler");
        recyclerList.add(homeBean);
    }

    public class RecyclerAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder>{

        public RecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<HomeBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeBean item) {
            helper.setText(R.id.tvName, item.getTitle());
            helper.setText(R.id.tvDesc, item.getContent());
        }
    }
}
