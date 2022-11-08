

package edu.uncc.hw04.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.uncc.hw04.R;
import edu.uncc.hw04.databinding.FragmentBacBinding;
import edu.uncc.hw04.models.Drink;
import edu.uncc.hw04.models.Profile;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BACFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BACFragment extends Fragment {
    FragmentBacBinding binding;
    private Profile mProfile;
    ArrayList<Drink> drinksArrayList = new ArrayList<>();
    private boolean isChecked = true;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BACFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BACFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BACFragment newInstance(String param1, String param2) {
        BACFragment fragment = new BACFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void getProfile(Profile p) {
        mProfile = p;
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
        binding = FragmentBacBinding.inflate(inflater, container, false);

        binding.setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.gotoSetProfile();
            }
        });

        if (mProfile == null ) {
            binding.weightEntered.setText("N/A");
            binding.addDrinkButton.setEnabled(false);
            binding.viewDrinksButton.setEnabled(false);
        } else {
            binding.weightEntered.setText(mProfile.getWeight() + " lbs (" + mProfile.getGender() + ")");
        }

        drinksArrayList.clear();
        drinksArrayList.addAll(mlistener.getCurrentDrinkList());

        if(drinksArrayList.size() == 0){
            binding.BAC.setText("0.000");
        } else {
            calculateAndDisplayBAC();
        }

        binding.addDrinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.gotoAddDrink();
            }
        });

        binding.viewDrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.gotoViewDrinks();
            }
        });

        binding.resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.weightEntered.setText("N/A");
                binding.addDrinkButton.setEnabled(false);
                binding.viewDrinksButton.setEnabled(false);
                binding.BAC.setText("0.000");
                binding.numberOfDrinks.setText("0");
                binding.status.setText("You're Safe");
                binding.status.setBackgroundColor(Color.parseColor("#20751A"));
                binding.status.setTextColor(Color.parseColor("#FFFFFFFF"));
                mProfile = null;

                drinksArrayList.clear();
                mlistener.clearList();
            }
        });

        return binding.getRoot();
    }

    void calculateAndDisplayBAC(){
        double rConstant = 0.66;
        if (mProfile.getGender().equals("Male")) {
            rConstant = 0.73;
        }

        double alcoholPercentage = 0.0;
        for (Drink drink: drinksArrayList) {
            alcoholPercentage = alcoholPercentage + drink.getAlcoholPercentage() * drink.getDrinkSize();
        }
        alcoholPercentage = alcoholPercentage / 100.0;

        double BACValue = (alcoholPercentage * 5.14 / (mProfile.getWeight() * rConstant));

        DecimalFormat threeDForm = new DecimalFormat("#.###");
        double roundedValue = Double.parseDouble(threeDForm.format(BACValue));
        binding.BAC.setText(Double.toString(roundedValue));

        binding.numberOfDrinks.setText(String.valueOf(drinksArrayList.size()));

        if (BACValue >= 0.00 && BACValue <= 0.08) {
            binding.status.setText("You're Safe");
            binding.status.setBackgroundColor(Color.parseColor("#20751A"));
            binding.status.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else if (BACValue > 0.08 && BACValue <= 0.20) {
            binding.status.setText("Be Careful!");
            binding.status.setBackgroundColor(Color.parseColor("#FF4C14"));
            binding.status.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else if (BACValue > 0.2 && BACValue < 0.25) {
            binding.status.setText("Over The Limit!");
            binding.status.setBackgroundColor(Color.parseColor("#E91E2F"));
            binding.status.setTextColor(Color.parseColor("#FFFFFFFF"));
        } else if (BACValue >= 0.25) {
            binding.status.setText("Over The Limit!");

            binding.addDrinkButton.setEnabled(false);
            binding.status.setBackgroundColor(Color.parseColor("#E91E2F"));
            binding.status.setTextColor(Color.parseColor("#FFFFFFFF"));
            Toast.makeText(getActivity(), "No More Drinks For You!", Toast.LENGTH_LONG).show();
        }
    }
    Ilistener mlistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Ilistener) {
            mlistener = (Ilistener) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface Ilistener {
        void gotoSetProfile();
        void gotoAddDrink();
        void gotoViewDrinks();
        ArrayList<Drink> getCurrentDrinkList();
        void clearList();
    }
}