package custom.lx.com.customview.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import custom.lx.com.customview.R;
import custom.lx.com.customview.base.BaseActivity;

/**
 * @author linxiao
 * @title：DragRecyclerActivity
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/22
 */
public class DragRecyclerActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private Context mContext = DragRecyclerActivity.this;
    private List<String> mData;
    private RecyclerDragAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBackBtn();
        initView();
        setTitle("Native RecyclerView");
        initData();
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        rvList.setItemAnimator(new DefaultItemAnimator());//设置动画
        //item分割线
//        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myAdapter = new RecyclerDragAdapter(mContext, mData);
        rvList.setAdapter(myAdapter);

        ItemTouchHelper.Callback callback = new RecyclerItemTouchHelp(myAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvList);
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
}
