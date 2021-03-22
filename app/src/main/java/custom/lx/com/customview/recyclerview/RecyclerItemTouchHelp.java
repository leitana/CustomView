package custom.lx.com.customview.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @author linxiao
 * @title：RecyclerItemTouchHelp
 * @projectName CustomView
 * @description: <Description>
 * @data Created in 2021/03/22
 */
public class RecyclerItemTouchHelp extends ItemTouchHelper.Callback {

    private final ItemTouchHelperCallback itemTouchHelperCallback;

    public RecyclerItemTouchHelp(ItemTouchHelperCallback itemTouchHelperCallback) {
        this.itemTouchHelperCallback = itemTouchHelperCallback;
    }

    /**
     * 设置滑动类型标记
     * @param recyclerView
     * @param viewHolder
     * @return 返回一个整数类型的标识，用于判断item的哪种移动是允许的。
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        /**
         * START  右向左
         * END    左向右
         * LEFT   向左
         * RIGHT  向右
         * UP     向上
         * DOWN   向下
         */
        return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.END);
    }

    /**
     * item是否支持长按拖动
     *
     * @return
     *  true 支持长按拖动
     *  false 不支持长按拖动
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * Item是否支持滑动
     *
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * 拖拽切换Item回调
     *
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     *  如果 Item换了位置则返回true；反之返回false
     */

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        itemTouchHelperCallback.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
    }

    /**
     * 滑动Item
     *
     * @param viewHolder
     * @param direction Item滑动的方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchHelperCallback.onItemDelete(viewHolder.getAdapterPosition());
    }


    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    public interface ItemTouchHelperCallback{
        void onItemDelete(int position);
        void onMove(int from, int to);
    }
}
