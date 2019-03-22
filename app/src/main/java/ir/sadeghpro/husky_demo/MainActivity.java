package ir.sadeghpro.husky_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ir.sadeghpro.husky.hlist.AdapterHelper;
import ir.sadeghpro.husky.hlist.HList;
import ir.sadeghpro.husky.slider.Slider;
import ir.sadeghpro.husky.slider.SliderModel;
import ir.sadeghpro.husky.slider.model.SimpleModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Slider slider = findViewById(R.id.slider);
        List<SliderModel> modelList = new ArrayList<>();
        modelList.add(new SimpleModel("https://www.ctvnews.ca/polopoly_fs/1.4037876!/httpImage/image.jpg_gen/derivatives/landscape_1020/image.jpg"));
        modelList.add(new SimpleModel("https://d1q6f0aelx0por.cloudfront.net/product-logos/81630ec2-d253-4eb2-b36c-eb54072cb8d6-golang.png"));
        modelList.add(new SimpleModel("http://images.math.cnrs.fr/IMG/png/section8-image.png"));
        slider.setModel(modelList);

        HList list = findViewById(R.id.list);
        ArrayList<Model> models = new ArrayList<>();
        models.add(new Model().setName("asdf"));
        models.add(new Model().setName("aaaa"));
        models.add(new Model().setName("bbbb"));
        models.add(new Model().setName("ccc"));
        models.add(new Model().setName("dddd"));
        AdapterHelper adapterHelper = new AdapterHelper(models,ViewHolder.class,R.layout.hlist_item);
//        HListAdapter adapter = new HListAdapter(this, models);
        list.setAdapter(adapterHelper);
    }
}
