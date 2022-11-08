

package edu.uncc.hw04.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.uncc.hw04.R;
import edu.uncc.hw04.databinding.FragmentSetProfileBinding;
import edu.uncc.hw04.models.Profile;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetProfileFragment extends Fragment {
    FragmentSetProfileBinding binding;
    private String gender;
    private int weight;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SetProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetProfileFragment newInstance(String param1, String param2) {
        SetProfileFragment fragment = new SetProfileFragment();
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
        binding = FragmentSetProfileBinding.inflate(inflater, container, false);
        binding.setButtonAct2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (binding.radioGroupGender.getCheckedRadioButtonId()) {
                    case R.id.femaleRadioButton:
                        gender= "Female";
                        break;

                    case R.id.maleRadioButton:
                        gender = "Male";
                        break;
                }


                if (gender == null || binding.editTextWeight.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "Add a valid weight and choose a gender!", Toast.LENGTH_LONG).show();
                }
                else {

                    weight = Integer.parseInt(binding.editTextWeight.getText().toString());

                    Profile profile = new Profile(weight, gender);
                    mListener.setProfile(profile);
                }
            }
        });
        return binding.getRoot();
    }
    ProfileListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ProfileListener) {
            mListener = (ProfileListener) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    public interface ProfileListener {
        void setProfile(Profile p);
    }
}