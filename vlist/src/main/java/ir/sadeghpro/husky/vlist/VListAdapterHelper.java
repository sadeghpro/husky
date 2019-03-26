package ir.sadeghpro.husky.vlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

public abstract class VListAdapterHelper<T> extends ArrayAdapter<T> {

    private Context context;
    private int count;
    private int cellCount = 1;
    private LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            1.0f
    );
    private int layoutResource;

    public VListAdapterHelper(Context context, ArrayList<T> data, int layoutResource) {
        super(context, 0, data);
        this.context = context;
        this.layoutResource = layoutResource;
    }

    @Override
    public int getCount() {
        count = super.getCount();
        return count / cellCount + (count % cellCount == 0 ? 0 : 1);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        if (convertView == null) {
            LinearLayout row = new LinearLayout(context);
            for (int i = 0; i < cellCount; i++) {
                View cell = LayoutInflater.from(context).inflate(layoutResource, parent, false);
                cell.setLayoutParams(param);
                row.addView(cell);
            }
            convertView = row;
        }
        for (int i = 0; i < ((LinearLayout)convertView).getChildCount(); i++) {
            final int finalPosition = (position * cellCount) + i;
            View v = ((LinearLayout)convertView).getChildAt(i);
            if (finalPosition >= count) {
                v.setVisibility(View.INVISIBLE);
            } else {
                v.setVisibility(View.VISIBLE);
                bindModel(finalPosition,v,getItem(finalPosition));
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        VList list = (VList) parent;
                        list.getOnItemVListClickListener().onItemClick(list,v,finalPosition,position);
                    }
                });
            }
        }

        return convertView;
    }

    public int getCellCount() {
        return cellCount;
    }

    public void setCellCount(int cellCount) {
        this.cellCount = cellCount;
    }

    public int getLayoutResource() {
        return layoutResource;
    }

    public void setLayoutResource(int layoutResource) {
        this.layoutResource = layoutResource;
    }

    public abstract void bindModel(int position, View convertView, T model);
}
