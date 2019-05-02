package ir.sadeghpro.husky.vlist;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.tbuonomo.materialsquareloading.MaterialSquareLoading;

public class VList extends ListView {

    private int cellCount;
    private boolean loading;
    private boolean finish;

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
            if (cellCount != 0) {
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
        if (getAdapter() != null) {
            ((VListAdapterHelper) getAdapter()).setCellCount(cellCount);
        } else {
            this.cellCount = cellCount;
        }
    }

    public void setOnLastItemVisible(final OnLastItemVisible onLastItemVisible) {
        setOnLastItemVisible(false, null, onLastItemVisible);
    }

    public void setOnLastItemVisible(final boolean enableLoading, final LoadingType type, final OnLastItemVisible onLastItemVisible) {
        setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && !loading && !finish) {
                    loading = true;
                    View loadingView = null;
                    if (enableLoading) {
                        switch (type) {
                            case CIRCLE:
                                loadingView = LayoutInflater.from(getContext()).inflate(R.layout.circle_loading, VList.this, false);
                                break;
                            case SQUARE:
                            default:
                                loadingView = LayoutInflater.from(getContext()).inflate(R.layout.square_loading, VList.this, false);
                                MaterialSquareLoading materialSquareLoading = loadingView.findViewById(R.id.material_square_loading_view);
                                materialSquareLoading.show();
                                break;
                        }
                        addFooterView(loadingView);
                    }
                    final View finalLoadingView = loadingView;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            onLastItemVisible.lastItemVisible();
                            loading = false;
                            ((Activity) getContext()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (enableLoading) {
                                        removeFooterView(finalLoadingView);
                                    }
                                    ((VListAdapterHelper) getAdapter()).notifyDataSetChanged();
                                }
                            });
                        }
                    }).start();
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
        });
    }

    public interface OnLastItemVisible {
        void lastItemVisible();
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public enum LoadingType {
        CIRCLE,
        SQUARE
    }
}
