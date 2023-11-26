package com.example.gr;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AgregarPlantaFragment extends DialogFragment {

    Button btn_add;
    EditText nombre, loc, tipo, cMacetas;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agregar_planta, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference("miPlanta");

        nombre = v.findViewById(R.id.nombre);
        loc = v.findViewById(R.id.localizacion);
        tipo = v.findViewById(R.id.tipo);
        cMacetas = v.findViewById(R.id.cuanMacentas);
        btn_add = v.findViewById(R.id.btn_add);

        btn_add = v.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreP = nombre.getText().toString().trim();
                String localiP = loc.getText().toString().trim();
                String tipoP = tipo.getText().toString().trim();
                String cMacetasPText = cMacetas.getText().toString().trim();

                if (nombreP.isEmpty() || localiP.isEmpty() || tipoP.isEmpty() || cMacetasPText.isEmpty()) {
                    Toast.makeText(getContext(), "Ingresar todos los datos", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Double cMacetasP = Double.parseDouble(cMacetasPText);
                        postPet(nombreP, localiP, tipoP, cMacetasP);
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Error al ingresar el número de macetas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;
    }

    private void postPet(String nombreP, String localiP, String tipoP, Double cMacetasP) {
        // Crear un mapa de datos
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombreP);
        map.put("localizacion", localiP);
        map.put("tipo", tipoP);
        map.put("macetas", cMacetasP);

        // Agregar datos a Realtime Database
        mDatabase.push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
