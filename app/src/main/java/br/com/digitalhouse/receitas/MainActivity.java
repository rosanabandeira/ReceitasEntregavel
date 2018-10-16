package br.com.digitalhouse.receitas;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.receitas.adapters.RecipesPagerAdapter;
import br.com.digitalhouse.receitas.fragments.PhotoRecipesFragment;
import br.com.digitalhouse.receitas.fragments.RecipesFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager);
        linearLayout = findViewById(R.id.linearLayout);

        List<Fragment> fragments = getFragments();

        RecipesPagerAdapter pagerAdapter = new RecipesPagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(pagerAdapter);

        for (int i = 0; i < 3; i++) {
            setTexttoContainer(linearLayout, "Receita 1 " + i);
        }

        for (int i = 0; i < 3; i++) {
            setTexttoContainer(linearLayout, "Receita 2 " + i);
        }

    }

    public void setTexttoContainer(LinearLayout container, String message) {
        View layout = getLayoutInflater().inflate(R.layout.fragment_pager_recipes, null);
        TextView item = layout.findViewById(R.id.textView_pager);
        item.setText(message);

        container.addView(layout);

    }

    private List<Fragment> getFragments(){
        List<Fragment> recipes = new ArrayList<>();

        Fragment fragment = PhotoRecipesFragment.newInstance(R.drawable.cake, "Receita Completa");

        recipes.add(fragment);
        recipes.add(PhotoRecipesFragment.newInstance(R.drawable.spaghetti, "Receita Completa 2"));
        recipes.add(PhotoRecipesFragment.newInstance(R.drawable.salad, "Receita Completa 3"));
        recipes.add(PhotoRecipesFragment.newInstance(R.drawable.bakery, "Receita Completa 4"));
        return recipes;
    }
}
