package ir.sadeghpro.husky_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.sadeghpro.husky.hlist.HListAdapterHelper;
import ir.sadeghpro.husky.hlist.HList;
import ir.sadeghpro.husky.hlist.OnItemClickListener;
import ir.sadeghpro.husky.slider.Slider;
import ir.sadeghpro.husky.slider.SliderModel;
import ir.sadeghpro.husky.slider.model.SimpleModel;
import ir.sadeghpro.husky.vlist.VList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Slider slider = findViewById(R.id.slider);
        List<SliderModel> modelList = new ArrayList<>();
        modelList.add(new SimpleModel("https://www.ctvnews.ca/polopoly_fs/1.4037876!/httpImage/image.jpg_gen/derivatives/landscape_1020/image.jpg"));
        modelList.add(new SimpleModel("https://d1q6f0aelx0por.cloudfront.net/product-logos/81630ec2-d253-4eb2-b36c-eb54072cb8d6-golang.png"));
        modelList.add(new SimpleModel("http://images.math.cnrs.fr/IMG/png/section8-image.png"));
        slider.setModel(modelList);
        slider.setAutoPlay(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    slider.setAutoPlay(false);
                    Thread.sleep(5000);
                    slider.setAutoPlay(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        HList list = findViewById(R.id.list);
        ArrayList<Model> models = new ArrayList<>();
        models.add(new Model().setName("asdf"));
        models.add(new Model().setName("aaaa"));
        models.add(new Model().setName("bbbb"));
        models.add(new Model().setName("ccc"));
        models.add(new Model().setName("dddd"));
        list.setOnItemClickListener(new OnItemClickListener( new  OnItemClickListener.OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                // TODO Handle item click
                Log.e("@@@@@",""+position);
            }
        }));
        HListAdapterHelper HListAdapterHelper = new HListAdapterHelper(models, HListViewHolder.class,R.layout.hlist_item);
//        HListAdapter adapter = new HListAdapter(this, models);
        list.setAdapter(HListAdapterHelper);
        VList vList = findViewById(R.id.vlist);
        vList.setCellCount(1);
        final ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");
        vList.addHeaderView(new TextView(this));
        vList.setOnLastItemVisible(true, VList.LoadingType.SQUARE,new VList.OnLastItemVisible() {
            @Override
            public void lastItemVisible() {
                try {
                    Thread.sleep(2000);
                    data.add("7");
                    data.add("8");
                    data.add("9");
                    data.add("10");
                    data.add("11");
                    data.add("12");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        VListAdapter adapter = new VListAdapter(this,data,R.layout.vlist_item);
        vList.setAdapter(adapter);
        vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("HuskyLog", position+ " : " + id);
            }
        });
    }
}
