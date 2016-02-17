package com.wok.food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 2/17/2016.
 */
public class AllFoods implements Serializable {
    public List<Food> foodList=new ArrayList<Food>();
    public void addFood(Food theFood){
        foodList.add(theFood);
    }
}
