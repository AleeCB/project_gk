package com.example.gr;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuculentasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuculentasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SuculentasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuculentasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuculentasFragment newInstance(String param1, String param2) {
        SuculentasFragment fragment = new SuculentasFragment();
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
        return inflater.inflate(R.layout.fragment_suculentas, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

/*
        //BOTONES QUE ENVIAN AL FRAGMENT DE RIEGO
        CardView myCardViewRiego = view.findViewById(R.id.carRiego);
        TextView myTextViewRiego = view.findViewById(R.id.textRiego);

        //BOTONES QUE ENVIAN AL FRAGMENT DE LUZ
        CardView myCardViewLuZ = view.findViewById(R.id.carLuz);
        TextView myTextViewLuz = view.findViewById(R.id.textLuz);

        //BOTONES QUE ENVIAN AL FRAGMENT DE SITIO
        CardView myCardViewSitio = view.findViewById(R.id.carSitio);
        TextView myTextViewSitio = view.findViewById(R.id.textSitio);

        //BOTONES QUE ENVIAN AL FRAGMENT DE ALIMENTO
        CardView myCardViewAli = view.findViewById(R.id.carAlimento);
        TextView myTextViewAli = view.findViewById(R.id.textAlimento);

        //BOTONES QUE ENVIAN AL FRAGMENT DE COMO PLANTAR
        CardView myCardViewCP = view.findViewById(R.id.carComoP);
        TextView myTextViewCP = view.findViewById(R.id.textComoP);


        //BOTONES QUE ENVIAN AL FRAGMENT DE RIEGO
        myCardViewRiego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_riegoSuculentasFragment2);
            }
        });
        myTextViewRiego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_riegoSuculentasFragment2);
            }
        });

        //BOTONES QUE ENVIAN AL FRAGMENT DE LUZ
        myCardViewLuZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_luzSuculentasFragment);
            }
        });
        myTextViewLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_luzSuculentasFragment);
            }
        });


        //BOTONES QUE ENVIAN AL FRAGMENT DE SITIO
        myCardViewSitio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_sitioSuculentasFragment);
            }
        });
        myTextViewSitio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_sitioSuculentasFragment);
            }
        });

        //BOTONES QUE ENVIAN AL FRAGMENT DE ALIMENTO
        myCardViewAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_alimentoSuculentasFragment);
            }
        });
        myTextViewAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_alimentoSuculentasFragment);
            }
        });

        //BOTONES QUE ENVIAN AL FRAGMENT DE COMO PLANTAR
        myCardViewCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_plantarSuculentasFragment);
            }
        });
        myTextViewCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_suculentasFragment_to_plantarSuculentasFragment);
            }
        });*/
    }
}