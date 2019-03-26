package ir.sadeghpro.husky.hlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class HListViewHolderHelper<T> extends RecyclerView.ViewHolder {

    public HListViewHolderHelper(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindModel(T model);
}
