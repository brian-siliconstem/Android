package com.example.b.class8;

import com.wok.food.*;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by b on 2/15/2016.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

        boolean showToast=false;
        private static int numberOfTabs=5;
        public List<String> tabTitles=new ArrayList<String>();
        private static String[] staticTitles= new String[] { "Need to Buy","Menu", "Pantry", "Recipes","Food" };
        private Context context;
        private FoodAppState foodAppState;

        public MainPagerAdapter(FragmentManager fm, Context context,FoodAppState fas) {
            super(fm);
            this.context = context;
            this.foodAppState=fas;

            this.numberOfTabs+=fas.openRecipes.size();
            for(int i=0;i<staticTitles.length;i++){
                tabTitles.add(staticTitles[i]);
            }
            for(String t:fas.openRecipes.keySet()){
                tabTitles.add(t);
            }

            toast("MainPagerAdapter:constructor");
        }

        @Override
        public Fragment getItem(int position) {
            toast("MainPagerAdapter:getItem:" + position);
            switch (position) {
                case 0:
                    ShoppingListFragment shoppingList =ShoppingListFragment.newInstance();//= new ShoppingListFragment();
                    toast("MainPagerAdapter:getItem:ShoppingListFragment");
                    return shoppingList;
                case 1:
                    MenuListFragment menuList =  MenuListFragment.newInstance();
                    toast("MainPagerAdapter:getItem:MenuListFragment");
                    return menuList;
                case 2:
                    PantryFragment pantry =  PantryFragment.newInstance();
                    return pantry;
                case 3:
                    RecipeListFragment recipes =  RecipeListFragment.newInstance();
                    return recipes;
                case 4:

                    FoodListFragment food =  FoodListFragment.newInstance(this.foodAppState);

                    return food;

                default:
                    //the remaining page are recipes that have been opened for browsing
                    FoodListFragment recipestandin =  FoodListFragment.newInstance(this.foodAppState);


                    return recipestandin;
            }
        }


    @Override
        public int getCount() {
       // Toast.makeText(this.context, "MainPagerAdapter:getCount: "+numberOfTabs, Toast.LENGTH_SHORT).show();
            return numberOfTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            toast("MainPagerAdapter:getPageTitle:"+position);
            return tabTitles.get(position);
        }
    public void toast(String message){
        if (showToast) Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show();
    }
}
