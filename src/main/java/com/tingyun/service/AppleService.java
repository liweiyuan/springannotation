package com.tingyun.service;


import com.tingyun.annocation.Apple;
import com.tingyun.annocation.FruitColor;
import com.tingyun.annocation.FruitName;
import com.tingyun.annocation.FruitProvider;
import com.tingyun.domain.AppleDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * Created by tingyun on 2017/6/13.
 */
@Service
@Apple
public class AppleService {

    @Autowired
    private AppleDomain appleDomain;

    public AppleDomain appleDomain() {
        AppleDomain appleDomain = new AppleDomain();
        return appleDomain;
    }


    public void getFruitInfo(String clas) {
        try {
            Class<?> cls = Class.forName(clas);
            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(FruitName.class) == true) {
                    FruitName name = field.getAnnotation(FruitName.class);
                    System.out.println("Fruit Name:" + name.value());
                }
                if (field.isAnnotationPresent(FruitColor.class)) {
                    FruitColor color = field.getAnnotation(FruitColor.class);
                    System.out.println("Fruit Color:" + color.fruitColor());
                }
                if (field.isAnnotationPresent(FruitProvider.class)) {
                    FruitProvider Provider = field.getAnnotation(FruitProvider.class);
                    System.out.println("Fruit FruitProvider: ProviderID:" + Provider.id() + " Provider:" + Provider.user() + " ProviderAddress:" + Provider.address());
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
