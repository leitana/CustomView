package custom.lx.com.customview.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;
import custom.lx.com.customview.base.BaseRecyclerAdapter;
import custom.lx.com.customview.base.ViewHolder;
import custom.lx.com.customview.utils.SizeUtils;

import static custom.lx.com.customview.recyclerview.NativeRecyclerActicity.getRandom;

/**
 * Created by 11300 on 2017/7/26.
 */

public class RecyclerWithBaseAdapter extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Context mContext = RecyclerWithBaseAdapter.this;
    private List<String> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBackBtn();
//        initView();
        setTitle("Native RecyclerView");
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
            TextView tv_text = holder.getView(R.id.tv_text);
            CardView cardView = holder.getView(R.id.cardview);
            tv_text.setText(s);
            cardView.setMinimumHeight(SizeUtils.dp2px(mContext, getRandom(70, 150)));
        }
    }
}
