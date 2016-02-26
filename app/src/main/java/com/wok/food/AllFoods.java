package com.wok.food;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by b on 2/17/2016.
 */
public class AllFoods implements Serializable {
    public List<Food> foodList=new ArrayList<Food>();
    public boolean addFood(Food theFood){
        //check for duplicates

        boolean isUnique=true;
        for (Food f  : foodList) {
            if (f.foodName.equalsIgnoreCase(theFood.foodName)&&f.category==theFood.category) {
                isUnique=false;
            }
        }

        if(isUnique)foodList.add(theFood);
        return isUnique;
    }
}
