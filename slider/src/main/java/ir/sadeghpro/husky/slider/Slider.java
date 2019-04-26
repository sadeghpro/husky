package ir.sadeghpro.husky.slider;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.List;

import ir.sadeghpro.husky.R;

public class Slider extends ConstraintLayout {
    private Context context;
    ViewPager pager;
    WormDotsIndicator indicator;

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

    }


    public Slider setModel(List<SliderModel> modelList) {
        SliderAdapter adapter = new SliderAdapter(context, modelList);
        pager.setAdapter(adapter);
        return this;
    }
}
