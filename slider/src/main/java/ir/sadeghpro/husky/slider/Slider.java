package ir.sadeghpro.husky.slider;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.Collections;
import java.util.List;

import ir.sadeghpro.husky.R;

public class Slider extends ConstraintLayout {
    private Context context;
    private ViewPager pager;
    private WormDotsIndicator indicator;
    private Thread thread;
    private int interval = 4000;
    private boolean autoPlay = false;
    private int position = 0;
    private List<SliderModel> modelList;

    public Slider(Context context) {
        super(context);
        init(context);
    }

    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Slider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.context = context;

        //<editor-fold desc="Create and add views to ConstraintLayout">
        pager = new ViewPager(context);
        pager.setId(R.id.pager);
        indicator = new WormDotsIndicator(context);
        indicator.setId(R.id.indicator);
        indicator.setViewPager(pager);
        addView(pager);
        addView(indicator);
        //</editor-fold>

        //<editor-fold desc="Set views constraint">
        ConstraintSet set = new ConstraintSet();
        set.clone(this);
        set.connect(pager.getId(), ConstraintSet.LEFT, getId(), ConstraintSet.LEFT, 0);
        set.connect(pager.getId(), ConstraintSet.TOP, getId(), ConstraintSet.TOP, 0);
        set.connect(pager.getId(), ConstraintSet.BOTTOM, getId(), ConstraintSet.BOTTOM, 0);
        set.connect(pager.getId(), ConstraintSet.RIGHT, getId(), ConstraintSet.RIGHT, 0);
        set.connect(indicator.getId(), ConstraintSet.LEFT, getId(), ConstraintSet.LEFT, 0);
        set.connect(indicator.getId(), ConstraintSet.BOTTOM, getId(), ConstraintSet.BOTTOM, 8);
        set.connect(indicator.getId(), ConstraintSet.RIGHT, getId(), ConstraintSet.RIGHT, 0);
        set.applyTo(this);
        //</editor-fold>

        //<editor-fold desc="Set direction">
        post(new Runnable() {
            @Override
            public void run() {
                if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                    if (pager.getAdapter() != null && pager.getAdapter().getCount() > 0) {
                        Collections.reverse(modelList);
                        SliderAdapter adapter = new SliderAdapter(getContext(), modelList, Slider.this);
                        pager.setAdapter(adapter);
                        position = pager.getAdapter().getCount() - 1;
                        pager.setCurrentItem(position, false);
                    }
                }
            }
        });
        //</editor-fold>

        //<editor-fold desc="Set change listener to set position for when auto play is enable">
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int pos) {
                position = pos;
            }
        });
        //</editor-fold>
    }

    public void setAutoPlay(boolean enable) {
        this.autoPlay = enable;
        if (autoPlay) {
            if (thread == null) {
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (autoPlay) {
                            System.out.println(System.currentTimeMillis());
                            try {
                                Thread.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (pager.getAdapter() != null) {
                                if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                                    position--;
                                    if (position < 0) {
                                        position = pager.getAdapter().getCount() - 1;
                                    }
                                } else {
                                    int count = pager.getAdapter().getCount();
                                    position++;
                                    if (position >= count) {
                                        position = 0;
                                    }
                                }
                            }
                            ((Activity) context).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pager.setCurrentItem(position, true);
                                }
                            });
                        }
                        thread = null;
                    }
                });
                thread.start();
            } else if (!thread.isAlive()) {
                thread.start();
            }
        }
    }

    public boolean isAutoPaly() {
        return autoPlay;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setDotIndicatorColor(int color) {
        indicator.setDotIndicatorColor(color);
    }

    public void setStrokeDotsIndicatorColor(int color) {
        indicator.setStrokeDotsIndicatorColor(color);
    }

    public Slider setModel(List<SliderModel> modelList) {
        this.modelList = modelList;
        SliderAdapter adapter = new SliderAdapter(context, modelList, this);
        pager.setAdapter(adapter);
        return this;
    }
}
