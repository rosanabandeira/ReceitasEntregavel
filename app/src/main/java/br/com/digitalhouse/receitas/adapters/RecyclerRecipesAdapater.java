package br.com.digitalhouse.receitas.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.receitas.R;
import br.com.digitalhouse.receitas.interfaces.RecyclerViewOnItemClickListener;
import br.com.digitalhouse.receitas.model.Recipes;

public class RecyclerRecipesAdapater extends RecyclerView.Adapter<RecyclerRecipesAdapater.ViewHolder> {

    private List<Recipes> recipesList;

    private RecyclerViewOnItemClickListener listener;


    public RecyclerRecipesAdapater(List<Recipes> recipesList, RecyclerViewOnItemClickListener listener) {
        this.recipesList = recipesList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerRecipesAdapater.ViewHolder holder, int position) {
        final Recipes recipes = recipesList.get(position);
        holder.bind(recipes);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(recipes);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    public void filterList(ArrayList<Recipes> filteredList) {
        recipesList = filteredList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewRecipes;
        private TextView textViewRecipes;

        public ViewHolder(View itemView) {
            super(itemView);

            imageViewRecipes = itemView.findViewById(R.id.imageViewRecipes);
            textViewRecipes = itemView.findViewById(R.id.textViewRecipes);
        }

        public void bind(Recipes recipes) {
            Picasso.get()
                    .load(recipes.getRecipes())
                    .placeholder(R.drawable.splash_receitas)
                    .error(android.R.drawable.stat_notify_error)
                    .into(imageViewRecipes);
            textViewRecipes.setText(recipes.getRecipes());
        }
    }
}
