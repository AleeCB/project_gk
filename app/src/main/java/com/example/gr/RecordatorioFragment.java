package com.example.gr;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import static android.widget.Toast.makeText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.work.Data;
import androidx.work.WorkManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordatorioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordatorioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button selefecha, selehora;
    TextView tvfecha, tvhora;
    Button guardar, eliminar;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();
    private Context mContext;
    private int  minutos, hora,dia, mes ,anio;
    public RecordatorioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordatorioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordatorioFragment newInstance(String param1, String param2) {
        RecordatorioFragment fragment = new RecordatorioFragment();
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
        return inflater.inflate(R.layout.fragment_recordatorio, container, false);
        // Crear y configurar el objeto DatePickerDialog

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button boton = view.findViewById(R.id.btn_recordatorio);
        mContext = getActivity();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarVentanaEmergente();
                //mostrarCalendario();

            }
        });



    }//FIN onViewCreated

    private void mostrarVentanaEmergente() {
        // Infla la vista de la ventana emergente
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popupView = inflater.inflate(R.layout.ventana_emergente_recordatorios, null);

        // Muestra la ventana emergente
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(popupView);
        final AlertDialog dialog = builder.create();

        // Configura el botón de cerrar
        Button btnCerrar = popupView.findViewById(R.id.btnCerrar);

        selefecha = popupView.findViewById(R.id.btn_seleFecha);
        selehora = popupView.findViewById(R.id.btn_selehora);
        tvfecha = popupView.findViewById(R.id.tv_fecha);
        tvhora = popupView.findViewById(R.id.tv_hora);
        guardar = popupView.findViewById(R.id.btn_guardar);

//para guardar cambios xd
        selefecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anio = actual.get(Calendar.YEAR);
                mes = actual.get(Calendar.MONTH);
                dia = actual.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        calendar.set(calendar.DAY_OF_MONTH,d);
                        calendar.set(calendar.MONTH,m);
                        calendar.set(calendar.YEAR,y);

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                        String strDate = format.format(calendar.getTime());
                        tvfecha.setText(strDate);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });
        selehora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora = actual.get(Calendar.HOUR_OF_DAY);
                minutos = actual.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog= new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY,h);
                        calendar.set(Calendar.MINUTE,m);

                        tvhora.setText(String.format("%02d:%02d",h, m));

                    }
                },hora,minutos, true);
                timePickerDialog.show();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tag = generatekey();
                long timeUntilNotification = calendar.getTimeInMillis() - System.currentTimeMillis();
                if (timeUntilNotification < 0) {
                    // Si el tiempo seleccionado ya ha pasado, programar para el día siguiente
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    timeUntilNotification = calendar.getTimeInMillis() - System.currentTimeMillis();
                }
                int random = (int) (Math.random() * 50 + 1);
                Data data =  GuardarData(  "Recordatorio. No te olvides de tu plantita" , "Soy un detalle", random);
                Workmanagernoti.GuardarNoti(timeUntilNotification,data,tag);


                Toast.makeText(mContext, "Recordatorio guardado.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });



        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });





        dialog.show();
    }
    private void EliminarNoti(String tag){
        WorkManager.getInstance(mContext).cancelAllWorkByTag(tag);
        makeText(mContext, "Eliminda alarma guardado.", Toast.LENGTH_SHORT).show();
    }
    private String generatekey(){

        return UUID.randomUUID().toString();
    }

    private Data GuardarData (String titulo, String detalle, int id_noti) {
        return new Data.Builder()
                .putString("titulo",titulo)
                .putString("detalle", detalle)
                .putInt("id_noti", id_noti).build();
    }

}



