package com.example.gr.FragmentsSuculentas;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gr.R;
import com.example.gr.model.SuculentaModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SitioSuculentasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SitioSuculentasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textSitio;
    private ImageView imgSitio;

    public SitioSuculentasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SitioSuculentasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SitioSuculentasFragment newInstance(String param1, String param2) {
        SitioSuculentasFragment fragment = new SitioSuculentasFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sitio_suculentas, container, false);
        textSitio = view.findViewById(R.id.textSitio);
        imgSitio = view.findViewById(R.id.imgSitio);

        // Obtener referencia a la base de datos
        DatabaseReference cuidadosRef = FirebaseDatabase.getInstance().getReference().child("cuidadosSuculentas");

        // Agregar un listener para obtener datos una vez
        cuidadosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Verificar si hay datos en la base de datos
                if (dataSnapshot.exists()) {
                    // Obtener el segundo objeto SuculentaModel
                    int count = 0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (count == 2) {
                            SuculentaModel suculentaModel = snapshot.getValue(SuculentaModel.class);

                            // Mostrar la información en los elementos de la interfaz de usuario
                            if (suculentaModel != null) {
                                textSitio.setText(suculentaModel.getInfo());

                                Glide.with(requireContext())
                                        .load(suculentaModel.getImagen())
                                        .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                                        .circleCrop()
                                        .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                                        .into(imgSitio);
                            }
                            break;  // Salir del bucle después de obtener el segundo objeto
                        }
                        count++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar errores si es necesario
            }
        });

        return view;
    }


}