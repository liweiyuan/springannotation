package com.tingyun.domain;


import com.tingyun.annocation.FruitColor;
import com.tingyun.annocation.FruitName;
import com.tingyun.annocation.FruitProvider;

/**
 * Created by tingyun on 2017/6/13.
 */
public class AppleDomain {
    @FruitName(value = "FuShi Apple")
    private String fruitName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String fruitColor;

    @FruitProvider(id = 1, user = "Tom", address = "China")
    private FruitProvider provider;

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getFruitColor() {
        return fruitColor;
    }

    public void setFruitColor(String fruitColor) {
        this.fruitColor = fruitColor;
    }

    public FruitProvider getProvider() {
        return provider;
    }

    public void setProvider(FruitProvider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "AppleDomain{" +
                "fruitName='" + fruitName + '\'' +
                ", fruitColor='" + fruitColor + '\'' +
                ", provider=" + provider +
                '}';
    }
}
