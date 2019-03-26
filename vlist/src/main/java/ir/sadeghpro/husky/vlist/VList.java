package ir.sadeghpro.husky.vlist;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class VList extends ListView {

    private int cellCount;

    private OnItemClickListener onItemClickListener;

    public VList(Context context) {
        super(context);
    }

    public VList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public VList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        if (adapter instanceof VListAdapterHelper) {
            super.setAdapter(adapter);
            if (cellCount!=0){
                ((VListAdapterHelper) getAdapter()).setCellCount(cellCount);
            }
        } else {
            throw new IllegalArgumentException("Adapter must be instance of VListAdapterHelper");
        }
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        onItemClickListener = listener;
    }


    public OnItemClickListener getOnItemVListClickListener() {
        return onItemClickListener;
    }


    public int getCellCount() {
        return cellCount;
    }

    public void setCellCount(int cellCount) {
        if (getAdapter() != null){
            ((VListAdapterHelper) getAdapter()).setCellCount(cellCount);
        } else {
            this.cellCount = cellCount;
        }
    }
}
