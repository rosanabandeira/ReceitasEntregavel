package br.com.digitalhouse.receitas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.digitalhouse.receitas.adapters.RecipesPagerAdapter;
import br.com.digitalhouse.receitas.adapters.RecyclerRecipesAdapater;
import br.com.digitalhouse.receitas.fragments.PhotoRecipesFragment;
import br.com.digitalhouse.receitas.fragments.RecipesFragment;
import br.com.digitalhouse.receitas.interfaces.ClickFragment;
import br.com.digitalhouse.receitas.model.Recipes;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ClickFragment {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ClickFragment listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setSupportActionBar(toolbar);
        configDrawerLayout(toolbar);
        replaceFragment(new RecipesFragment());

    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
    }

    private void configDrawerLayout(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_vegetarianas) {
            startActivity(new Intent(this, MainActivity.class));

        } else if (id == R.id.nav_massas) {
            startActivity(new Intent(this, MainActivity.class));

        } else if (id == R.id.nav_sobremesas) {
            startActivity(new Intent(this, MainActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(MainActivity mainActivity) {
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_home, fragment);
        transaction.addToBackStack("FRAG");
        transaction.commit();

    }

    @Override
    public void OnClickButton() {
       /* Fragment fragment = PhotoRecipesFragment.newInstance(R.id.imageViewRecipes, "Receita Completa");
        replaceFragment(fragment);*/


    }
}
