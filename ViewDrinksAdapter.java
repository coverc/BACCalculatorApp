

package edu.uncc.hw04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.uncc.hw04.models.Drink;

public class ViewDrinksAdapter extends RecyclerView.Adapter<ViewDrinksAdapter.ViewDrinksHolder> {
   public static ArrayList<Drink> drinks;
    IDrinkDelete mListener;

    public ViewDrinksAdapter(ArrayList<Drink> drinkData, IDrinkDelete mListener){
        this.mListener = mListener;
        this.drinks = drinkData;
    }

    @NonNull
    @Override
    public ViewDrinksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drink_recycler_view_layout, parent, false);

        ViewDrinksHolder drinksViewHolder = new ViewDrinksHolder(view, mListener);

        return drinksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDrinksHolder holder, int position) {
        Drink drink = drinks.get(position);
        holder.percentAlcoholViewDrinks.setText(drink.alcoholPercentage + "% Alcohol");
        holder.drinkSizeViewDrinks.setText(drink.drinkSize + "oz");
        holder.dateAddedViewDrinks.setText("Added " + drink.addedOn);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return this.drinks.size();
    }

    public static class ViewDrinksHolder extends RecyclerView.ViewHolder{
        TextView percentAlcoholViewDrinks;
        TextView drinkSizeViewDrinks;
        TextView dateAddedViewDrinks;
        int position;
        Drink individualDrink;
        IDrinkDelete mListener;

        public ViewDrinksHolder(@NonNull View itemView, IDrinkDelete mListener) {
            super(itemView);
            this.mListener = mListener;
            percentAlcoholViewDrinks = itemView.findViewById(R.id.percentAlcoholViewDrinks);
            drinkSizeViewDrinks = itemView.findViewById(R.id.drinkSizeViewDrinks);
            dateAddedViewDrinks = itemView.findViewById(R.id.dateAddedViewDrinks);
            itemView.findViewById(R.id.trashButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //drinks.remove(position);
                    mListener.updateList(position);
                }
            });
        }
    }

    public interface IDrinkDelete{
        //public void updateList(ArrayList<Drink> drinks);

       public void updateList(int index);
    }
}
