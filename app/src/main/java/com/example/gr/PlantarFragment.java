package com.example.gr;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlantarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlantarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlantarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlantarFragment newInstance(String param1, String param2) {
        PlantarFragment fragment = new PlantarFragment();
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
        View view = inflater.inflate(R.layout.fragment_plantar, container, false);

        // Obtén los argumentos
        Bundle args = getArguments();
        if (args != null) {
            String infoR = args.getString("infoPlan");
            TextView textViewPlan = view.findViewById(R.id.textComoP); // Reemplaza con tu ID de TextView
            textViewPlan.setText(infoR);


            // Cargar la imagen usando Glide
            ImageView imageView = view.findViewById(R.id.imgComoP); // Reemplaza con tu ID de ImageView
            Glide.with(imageView.getContext() )
                    .load(args.getString("imgPlan"))// URL de la imagen desde la base de datos
                    .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                    .circleCrop()
                    .error(R.drawable.planta)
                    .into(imageView);
        }

        return view;
    }
}