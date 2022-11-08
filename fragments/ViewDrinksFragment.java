

package edu.uncc.hw04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.uncc.hw04.ViewDrinksAdapter;
import edu.uncc.hw04.databinding.FragmentViewDrinksBinding;
import edu.uncc.hw04.models.Drink;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewDrinksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewDrinksFragment extends Fragment implements ViewDrinksAdapter.IDrinkDelete {
    FragmentViewDrinksBinding binding;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    ViewDrinksAdapter adapter;
    public static ArrayList<Drink> drinkListView = new ArrayList<>();
    ViewDrinksListener mListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ViewDrinksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewDrinksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewDrinksFragment newInstance(String param1, String param2) {
        ViewDrinksFragment fragment = new ViewDrinksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentViewDrinksBinding.inflate(inflater, container, false);
        //drinkListView.clear();
        drinkListView = mListener.getCurrentDrinkList();
        recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ViewDrinksAdapter(drinkListView, this);
        recyclerView.setAdapter(adapter);

        binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.closeViewDrinks();
            }
        });

        binding.sortDescendingAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(drinkListView, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink drink, Drink t1) {
                        return t1.alcoholPercentage - drink.alcoholPercentage;
                    }
                });

                recyclerView.setAdapter(adapter);
            }
        });

        binding.sortAscendingAlcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(drinkListView, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink drink, Drink t1) {
                        return drink.alcoholPercentage - t1.alcoholPercentage;
                    }
                });

                recyclerView.setAdapter(adapter);
            }
        });

        binding.sortDescendingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(drinkListView, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink drink, Drink t1) {
                        return -1 * (t1.dateAdded.compareTo(drink.dateAdded));
                    }
                });

                recyclerView.setAdapter(adapter);
            }
        });

        binding.sortAscendingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(drinkListView, new Comparator<Drink>() {
                    @Override
                    public int compare(Drink drink, Drink t1) {
                        return t1.dateAdded.compareTo(drink.dateAdded);
                    }
                });

                recyclerView.setAdapter(adapter);
            }
        });
        

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //if (context instanceof ViewDrinksListener){
            mListener = (ViewDrinksListener) context;
        //}
    }

    @Override
    public void updateList(int index) {
        drinkListView = mListener.updateDrinkList(index);
        Log.d("demo", "updateList: " + drinkListView);
        adapter = new ViewDrinksAdapter(drinkListView, this);
        recyclerView.setAdapter(adapter);
    }

    public interface ViewDrinksListener {
        ArrayList<Drink> getCurrentDrinkList();
        ArrayList<Drink> updateDrinkList(int index);
        void closeViewDrinks();
    }
}