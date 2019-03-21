package ir.sadeghpro.husky;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<SliderModel> modelList;
    private Context context;

    public SliderAdapter(Context context, List<SliderModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView image = new ImageView(context);
        SliderModel model = modelList.get(position);
        Glide.with(context).load(model.getImageUrl()).centerCrop().into(image);
        container.addView(image);
        return image;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }
}
