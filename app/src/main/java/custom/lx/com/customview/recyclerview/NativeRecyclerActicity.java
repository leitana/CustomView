package custom.lx.com.customview.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;
import custom.lx.com.customview.utils.SizeUtils;

/**
 * Created by 11300 on 2017/7/19.
 */

public class NativeRecyclerActicity extends BaseActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Context mContext = NativeRecyclerActicity.this;
    private List<String> mData;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBackBtn();
        initView();
        setTitle("Native RecyclerView");
        initData();
        rvList.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rvList.setItemAnimator(new DefaultItemAnimator());//设置动画
        //item分割线
//        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter = new MyAdapter();
        rvList.setAdapter(myAdapter);
    }

    private void initView() {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.mipmap.ic_add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.add(2, "new item");
//                myAdapter.notifyDataSetChanged();
                myAdapter.notifyItemInserted(2);
            }
        });
        addRightView(imageView);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("item" + i);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mRootView = LayoutInflater.from(mContext).inflate(R.layout.text_item, parent, false);
            MyViewHolder holder = new MyViewHolder(mRootView);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv_text.setText(mData.get(position));
            holder.cardView.setMinimumHeight(SizeUtils.dp2px(mContext, getRandom(70, 150)));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tv_text;
            CardView cardView;
            public MyViewHolder(View itemView) {
                super(itemView);
                cardView = (CardView) itemView.findViewById(R.id.cardview);
                tv_text = (TextView) itemView.findViewById(R.id.tv_text);
            }
        }
    }

    public static int getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }
}