package ir.sadeghpro.husky_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ir.sadeghpro.husky.Slider;
import ir.sadeghpro.husky.SliderModel;
import ir.sadeghpro.husky.model.Simple;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Slider slider = findViewById(R.id.slider);
        List<SliderModel> modelList = new ArrayList<>();
        modelList.add(new Simple("https://www.ctvnews.ca/polopoly_fs/1.4037876!/httpImage/image.jpg_gen/derivatives/landscape_1020/image.jpg"));
        modelList.add(new Simple("https://d1q6f0aelx0por.cloudfront.net/product-logos/81630ec2-d253-4eb2-b36c-eb54072cb8d6-golang.png"));
        modelList.add(new Simple("http://images.math.cnrs.fr/IMG/png/section8-image.png"));
        slider.setModel(modelList);
    }
}
