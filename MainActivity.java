

package edu.uncc.hw04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uncc.hw04.databinding.ActivityMainBinding;
import edu.uncc.hw04.fragments.AddDrinkFragment;
import edu.uncc.hw04.fragments.BACFragment;
import edu.uncc.hw04.fragments.SetProfileFragment;
import edu.uncc.hw04.fragments.ViewDrinksFragment;
import edu.uncc.hw04.models.Drink;
import edu.uncc.hw04.models.Profile;

public class MainActivity extends AppCompatActivity implements BACFragment.Ilistener, SetProfileFragment.ProfileListener, AddDrinkFragment.AddDrinkListener, ViewDrinksFragment.ViewDrinksListener{
    ActivityMainBinding binding;
    Profile profile;
    ArrayList<Drink> drinkList = new ArrayList<>();
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("BAC Calculator");
        getSupportFragmentManager().beginTransaction().add(R.id.containerView, new BACFragment(), "BACCalculatorFrag")
                .commit();
    }

    @Override
    public void setProfile(Profile p) {
        BACFragment fragment = (BACFragment) getSupportFragmentManager().findFragmentByTag("BACCalculatorFrag");
        if (fragment != null) {
            setTitle("BAC Calculator");
            fragment.getProfile(p);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void gotoSetProfile() {
        setTitle("Set Weight/Gender");
        getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new SetProfileFragment(), "setProfileFrag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoAddDrink() {
        setTitle("Add Drink");
        getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new AddDrinkFragment(), "addDrinkFrag")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoViewDrinks() {
        if (drinkList.size() > 0) {
            //are there any drinks in the list? yes

            setTitle("View Drinks");
            getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new ViewDrinksFragment(), "viewDrinkFrag")
                    .addToBackStack(null)
                    .commit();
        } else {

            Toast.makeText(this, "There is no drinks added", Toast.LENGTH_SHORT).show();
            //drinkList.remove(drinkList);
        }
    }

    @Override
    public void addDrinkToList(Drink d) {
        drinkList.add(d);
        Log.d("demo", "addDrinkToList: " + drinkList);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void closeAddDrink() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public ArrayList<Drink> getCurrentDrinkList() {
        Log.d("demo", "getCurrentDrinkList: " + drinkList);
        return drinkList;
    }

    @Override
    public ArrayList<Drink> updateDrinkList(int index) {
        drinkList.remove(index);
        Log.d("demo", "updateDrinkList: " + drinkList);
        return drinkList;
    }


    @Override
    public void closeViewDrinks() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void clearList() {
        this.drinkList.clear();
    }


}