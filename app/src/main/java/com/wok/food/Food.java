package com.wok.food;
import java.io.Serializable;
/**
 * Created by b on 2/15/2016.
 */
public class Food  implements Serializable{
    public String foodName;

    public FoodCategory category;


    public Food(String name, FoodCategory category){
        foodName=name;
        category=category;
    }

    public Food(String name){
        foodName=name;
        category=FoodCategory.OTHER;
    }

    public Food(){
        foodName="";
        category=FoodCategory.OTHER;
    }
}
