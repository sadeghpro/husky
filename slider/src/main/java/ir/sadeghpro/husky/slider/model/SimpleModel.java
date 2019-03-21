package ir.sadeghpro.husky.slider.model;

import ir.sadeghpro.husky.slider.SliderModel;

public class SimpleModel implements SliderModel {
    private String imageUrl;

    public SimpleModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
