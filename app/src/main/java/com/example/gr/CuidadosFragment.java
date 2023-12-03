package com.example.gr;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuidadosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuidadosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CuidadosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuidadosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuidadosFragment newInstance(String param1, String param2) {
        CuidadosFragment fragment = new CuidadosFragment();
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
        View view = inflater.inflate(R.layout.fragment_cuidados, container, false);
        Bundle args = getArguments();
        if (args != null) {
            String nombre    = args.getString("nombre");
            String frase = args.getString("frase");

            String imagenRiego = args.getString("imgRiego");
            String riego = args.getString("infoRiego");
            String imagenLuz = args.getString("imgLuz");
            String luz = args.getString("infoLuz");
            String imagenSitio = args.getString("imgSitio");
            String sitio = args.getString("infoSitio");
            String imagenAli = args.getString("imgAli");
            String ali = args.getString("infoAli");
            String imagenPlan = args.getString("imgPlan");
            String plan = args.getString("infoPlan");


            // Usar la variable 'nombre' para mostrar los datos en el fragmento
            TextView textViewNombre = view.findViewById(R.id.info);
            textViewNombre.setText(nombre);
            TextView textViewFrase = view.findViewById(R.id.textFrase);
            textViewFrase.setText(frase);

            // Cargar la imagen usando Glide
            ImageView imageView = view.findViewById(R.id.imgIcono);
            Glide.with(imageView.getContext())
                    .load(args.getString("icono"))// URL de la imagen desde la base de datos
                    .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                    .circleCrop()
                    .error(R.drawable.planta)
                    .into(imageView);


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
                    Bundle datosParaRiegoFragment = new Bundle();
                    datosParaRiegoFragment.putString("infoRiego", riego);
                    datosParaRiegoFragment.putString("imgRiego", imagenRiego);


                    Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_riegoFragment, datosParaRiegoFragment);
                }
            });
            myTextViewRiego.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle datosParaRiegoFragment = new Bundle();
                    datosParaRiegoFragment.putString("infoRiego", riego);
                    datosParaRiegoFragment.putString("imgRiego", imagenRiego);

                    Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_riegoFragment, datosParaRiegoFragment);
                }
            });

       //BOTONES QUE ENVIAN AL FRAGMENT DE LUZ
        myCardViewLuZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datosParaRiegoFragment = new Bundle();
                datosParaRiegoFragment.putString("infoLuz", luz);
                datosParaRiegoFragment.putString("imgLuz", imagenLuz);
                Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_luzFragment, datosParaRiegoFragment);
            }
        });
        myTextViewLuz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datosParaRiegoFragment = new Bundle();
                datosParaRiegoFragment.putString("infoLuz", luz);
                datosParaRiegoFragment.putString("imgLuz", imagenLuz);
                Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_luzFragment, datosParaRiegoFragment);
            }
        });


        //BOTONES QUE ENVIAN AL FRAGMENT DE SITIO
        myCardViewSitio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datosParaRiegoFragment = new Bundle();
                datosParaRiegoFragment.putString("infoSitio", sitio);
                datosParaRiegoFragment.putString("imgSitio", imagenSitio);
                Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_sitioFragment, datosParaRiegoFragment);
            }
        });
        myTextViewSitio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datosParaRiegoFragment = new Bundle();
                datosParaRiegoFragment.putString("infoSitio", sitio);
                datosParaRiegoFragment.putString("imgSitio", imagenSitio);
                Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_sitioFragment, datosParaRiegoFragment);
            }
        });

        //BOTONES QUE ENVIAN AL FRAGMENT DE ALIMENTO
        myCardViewAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datosParaRiegoFragment = new Bundle();
                datosParaRiegoFragment.putString("infoAli", ali);
                datosParaRiegoFragment.putString("imgAli", imagenAli);
                Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_alimentoFragment, datosParaRiegoFragment);
            }
        });
        myTextViewAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datosParaRiegoFragment = new Bundle();
                datosParaRiegoFragment.putString("infoAli", ali);
                datosParaRiegoFragment.putString("imgAli", imagenAli);
                Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_alimentoFragment, datosParaRiegoFragment);
            }
        });

        //BOTONES QUE ENVIAN AL FRAGMENT DE COMO PLANTAR
        myCardViewCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datosParaRiegoFragment = new Bundle();
                datosParaRiegoFragment.putString("infoPlan", plan);
                datosParaRiegoFragment.putString("imgPlan", imagenPlan);
                Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_plantarFragment, datosParaRiegoFragment);
            }
        });
        myTextViewCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle datosParaRiegoFragment = new Bundle();
                datosParaRiegoFragment.putString("infoPlan", plan);
                datosParaRiegoFragment.putString("imgPlan", imagenPlan);
                Navigation.findNavController(view).navigate(R.id.action_cuidadosFragment_to_plantarFragment, datosParaRiegoFragment);
            }
        });
        }
        return view;

    }
}