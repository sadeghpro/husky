package ir.sadeghpro.husky.hlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnItemClickListener implements RecyclerView.OnItemTouchListener{
    private OnClickListener mListener;

    public interface OnClickListener {
        void onClick(View view, int position);
    }

    private GestureDetector gestureDetector;

    public OnItemClickListener(OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView view, @NonNull MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && gestureDetector.onTouchEvent(e)) {
            mListener.onClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView view, @NonNull MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }
}
