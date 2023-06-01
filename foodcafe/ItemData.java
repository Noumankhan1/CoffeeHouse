package com.example.foodcafe;

import android.content.Intent;

public class ItemData {
    String item_name;
    int images;
    String prices;

    public ItemData(String item_name, int images, String prices) {
        this.item_name = item_name;
        this.images = images;
        this.prices = prices;
    }



    public String getItem_name() {
        return item_name;
    }

    public int getImages() {
        return images;
    }

    public String getPrices() {
        return prices;
    }

}

