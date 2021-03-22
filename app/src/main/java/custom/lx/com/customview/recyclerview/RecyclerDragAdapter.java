package custom.lx.com.customview.recyclerview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import custom.lx.com.customview.R;
import custom.lx.com.customview.utils.SizeUtils;

/**
 * @author linxiao
 * @title：RecyclerDragAdapter
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/22
 */
public class RecyclerDragAdapter extends RecyclerView.Adapter<RecyclerDragAdapter.ViewHolder> implements RecyclerItemTouchHelp.ItemTouchHelperCallback {

    private Context mContext;
    private List<String> mData;

    public RecyclerDragAdapter(Context context, List<String> mData) {
        mContext = context;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRootView = LayoutInflater.from(mContext).inflate(R.layout.text_item, parent, false);
        ViewHolder holder = new ViewHolder(mRootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_text.setText(mData.get(position));
        holder.cardView.setMinimumHeight(SizeUtils.dp2px(mContext, getRandom(70, 150)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onItemDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onMove(int from, int to) {
        Collections.swap(mData,from,to);//交换数据
        notifyItemMoved(from, to);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_text;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
            tv_text = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }

    public static int getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }
}
