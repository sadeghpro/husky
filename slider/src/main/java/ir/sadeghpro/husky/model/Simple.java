package ir.sadeghpro.husky.model;

import ir.sadeghpro.husky.SliderModel;

public class Simple implements SliderModel {
    private String imageUrl;

    public Simple(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
