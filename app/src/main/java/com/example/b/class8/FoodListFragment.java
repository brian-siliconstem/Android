package com.example.b.class8;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.support.v4.app.FragmentManager;
//import android.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
//import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wok.food.*;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodListFragment extends Fragment implements FoodFragment.OnFoodFragmentInteractionListener,FoodListAdapter.BtnClickListener {

    private OnFragmentInteractionListener mListener;
    private FoodFragment foodFragment;
    public FoodAppState foodAppState;
    FoodListAdapter foodListAdapter;
    public final String foodFragmentTag="foodFragmentTag";
    private boolean showToast=true;
    public FoodListFragment() {
        // Required empty public constructor
    }

    public static FoodListFragment newInstance(FoodAppState fas) {
        FoodListFragment fragment = new FoodListFragment();
        fragment.foodAppState=fas;
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void toast(String message){
        if (showToast) Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void addFood(View v){
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        foodFragment=FoodFragment.newInstance(new Food());
        //FoodFragment foodFragment = (FoodFragment)fragmentManager.findFragmentById(R.id.foodFragment);
        fragmentTransaction.replace(R.id.flFoodFragmentContainer,foodFragment,foodFragmentTag);
        fragmentTransaction.commit();

    }

    public void cancelFood(){
        FragmentManager fragmentManager = getChildFragmentManager();
        FoodFragment foodFragment = (FoodFragment)  fragmentManager.findFragmentByTag(foodFragmentTag);
        //FragmentManager fragmentManager = this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(foodFragment);
        fragmentTransaction.commit();
        Toast.makeText(this.getContext(), "FoodListFragment.cancelFood", Toast.LENGTH_SHORT).show();
    }
    public void saveFood(Food food){

        Food theFood=food;//=this.foodFragment.getFood();
        if(this.foodAppState.allFoods.addFood(theFood)){
            this.refreshList();
            cancelFood();
            toast("FoodListFragment.saveFood: Added[" + food.foodName + "]");
            //errorMessage.setVisibility(View.VISIBLE);
        }
        else {
            TextView errorMessage = (TextView) this.getView().findViewById(R.id.textViewErrorMessage);
            errorMessage.setVisibility(View.VISIBLE);
            toast("Duplicate Food - new entry was not created");

        }

    }

    public void updateFood(Food food,int position){

        Food theFood=food;//this.foodFragment.getFood();
        Food toUpdate=this.foodAppState.allFoods.foodList.get(position);
        toUpdate.category=theFood.category;
        toUpdate.foodName=theFood.foodName;
        this.refreshList();
        cancelFood();
        Toast.makeText(this.getContext(), "FoodListFragment.updateFood:[" + food.foodName + "]", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View retView = inflater.inflate(R.layout.fragment_food_list, container, false);


        //FragmentManager fragmentManager = this.getFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //FoodFragment foodFragment = (FoodFragment)fragmentManager.findFragmentById(R.id.foodFragment);
       // fragmentTransaction.hide(foodFragment);
        //fragmentTransaction.commit();

        ImageButton newFood = (ImageButton) retView.findViewById(R.id.fragmentFoodListAddFoodButton);
        newFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFood(view);
            }
        });

        buildList(retView);
        return retView;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void onDeleteClick(int position) {
        this.foodAppState.allFoods.foodList.remove(position);
        Toast.makeText(this.getContext(), "FoodListFragment.onDeleteClick: removing item:"+position, Toast.LENGTH_SHORT).show();
        this.refreshList();
        this.buildList(this.getView());
    }
    public void onEditClick(int position) {

        Food toEdit=this.foodAppState.allFoods.foodList.get(position);
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        foodFragment=FoodFragment.newInstance(toEdit,position);
        fragmentTransaction.replace(R.id.flFoodFragmentContainer,foodFragment,foodFragmentTag);
        fragmentTransaction.commit();

        this.refreshList();
        this.buildList(this.getView());
    }

    public void buildList(View v)
    {
        //This uses the custom adapter in ItemEntryAdapter.java
        ListView lv = (ListView) v.findViewById(R.id.foodListView);
        this.foodListAdapter = new FoodListAdapter(this.getContext(), R.layout.adapter_food_list_row, this.foodAppState.allFoods.foodList,this);
        lv.setAdapter(this.foodListAdapter);

        Toast.makeText(this.getContext(), "FoodListFragment.buildList", Toast.LENGTH_SHORT).show();
    }
    public void refreshList()
    {
        //this forces the list of items to be updated to show the newly added item
        //if we don't do this, the item will be there, but not shown until the screen refreshes
        final ArrayAdapter adapter = (ArrayAdapter)this.foodListAdapter;
        this.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
