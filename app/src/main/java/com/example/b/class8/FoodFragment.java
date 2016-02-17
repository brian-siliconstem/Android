package com.example.b.class8;

import com.wok.food.*;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class FoodFragment extends Fragment {


    public Food theFood;


    private OnFragmentInteractionListener mListener;
    private OnFoodFragmentInteractionListener foodListener;

    public Button cancelFoodB;
    public Button saveFoodB;

    public FoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param f Food

     * @return A new instance of fragment FoodFragment.
     */

    public static FoodFragment newInstance(Food f) {
        FoodFragment fragment = new FoodFragment();
        fragment.theFood=f;
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getParentFragment()==null){
            Toast.makeText(this.getContext(), "FoodFragment.onCreate.getParentFragment=nulll", Toast.LENGTH_SHORT).show();
        }
        onAttachFragment(getParentFragment());
        if (getArguments() != null) {
            ///mParam1 = getArguments().getString(ARG_PARAM1);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View retView =inflater.inflate(R.layout.fragment_food, container, false);
        Spinner categorySpinner = (Spinner)retView.findViewById(R.id.foodCategorySpinner);
        categorySpinner.setAdapter(new ArrayAdapter<FoodCategory>(this.getContext(),android.R.layout.simple_list_item_1, FoodCategory.values()));

        cancelFoodB = (Button) retView.findViewById(R.id.cancelButton);
        cancelFoodB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelFood(view);
            }
        });

        saveFoodB = (Button) retView.findViewById(R.id.saveButton);
        saveFoodB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFood(view);
            }
        });


        saveFoodB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ((OnFoodFragmentInteractionListener)getParentFragment()).saveFood(theFood);

                foodListener.saveFood(theFood);
            }
        });

        cancelFoodB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ((OnFoodFragmentInteractionListener) getParentFragment()).cancelFood(theFood);
                foodListener.cancelFood(theFood);
            }
        });


        return retView;
    }


    public Food getFood(){
        Food theFood=new Food();

        EditText foodNameEditText= (EditText)this.getView().findViewById(R.id.foodName);
        theFood.foodName=foodNameEditText.getText().toString();

        Spinner foodCategorySpinner=(Spinner)this.getView().findViewById(R.id.foodCategorySpinner);
        theFood.category=FoodCategory.valueOf(foodCategorySpinner.getSelectedItem().toString());

        return theFood;
    }
    public void addFood(View view) {
        //OnFoodFragmentInteractionListener listener=(OnFoodFragmentInteractionListener)getParentFragment();
        foodListener.saveFood(new Food("test"));
    }
    public void cancelFood(View view){
        //OnFoodFragmentInteractionListener listener=(OnFoodFragmentInteractionListener)getParentFragment();
        foodListener.cancelFood(new Food("test"));
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

    public void onAttachFragment(Fragment fragment)
    {
        try
        {
            foodListener = (OnFoodFragmentInteractionListener)fragment;

        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnFoodFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFoodFragmentInteractionListener {
        void cancelFood(Food food);
        void saveFood(Food food);
    }
}
