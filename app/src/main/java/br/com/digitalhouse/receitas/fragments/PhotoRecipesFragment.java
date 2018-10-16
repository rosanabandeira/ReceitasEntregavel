package br.com.digitalhouse.receitas.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.digitalhouse.receitas.R;

public class PhotoRecipesFragment extends Fragment {

    private int imageResource;
    private String textTitle;


    public PhotoRecipesFragment() {
    }

    public static PhotoRecipesFragment newInstance(int image, String text) {
        Bundle args = new Bundle();

        PhotoRecipesFragment fragment = new PhotoRecipesFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("IMAGEM", image);


        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() !=null) {
            imageResource =  getArguments().getInt("IMAGEM");
            textTitle = getArguments().getString("TEXTO");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager_recipes, container, false);

        ImageView imageViewPhoto = view.findViewById(R.id.imageView_pager);
        TextView textViewTitle = view.findViewById(R.id.textView_pager);

        imageResource = getArguments().getInt("IMAGEM");
        textTitle = getArguments().getString("TEXT");

        textViewTitle.setText(textTitle);
        imageViewPhoto.setImageResource(imageResource);


        return view;
    }
}
