

package edu.uncc.hw04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.uncc.hw04.R;
import edu.uncc.hw04.databinding.FragmentAddDrinkBinding;
import edu.uncc.hw04.models.Drink;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDrinkFragment extends Fragment {
    FragmentAddDrinkBinding binding;
    int amount = 0;
    int percent;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
    String dateAdded;
    Date addTheDate;
    boolean isChecked = true;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddDrinkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddDrinkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDrinkFragment newInstance(String param1, String param2) {
        AddDrinkFragment fragment = new AddDrinkFragment();
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
        binding = FragmentAddDrinkBinding.inflate(inflater, container, false);
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                binding.seekBarValue.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.addDrinkButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (binding.drinkSizeRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.oneRadioButton:
                        amount = 1;
                        break;

                    case R.id.fiveRadioButton:
                        amount = 5;
                        break;

                    case R.id.twelveRadioButton:
                        amount = 12;
                        break;
                }

                percent = binding.seekBar.getProgress();
                if (amount == 0 || percent == 0) {
                    Toast.makeText(getActivity(), "Add a valid drink!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    dateAdded = df.format(Calendar.getInstance().getTime());
                    addTheDate = Calendar.getInstance().getTime();
                    Drink drinks = new Drink(amount, percent, dateAdded, addTheDate);

                    mlistener.addDrinkToList(drinks);
                }
            }
        });
        binding.cancelButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.closeAddDrink();
            }
        });

        return binding.getRoot();
    }
    AddDrinkListener mlistener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

            mlistener = (AddDrinkListener) context;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface AddDrinkListener {
        void addDrinkToList(Drink d);
        void closeAddDrink();

    }
}