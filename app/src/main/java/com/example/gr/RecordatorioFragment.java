package com.example.gr;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class RecordatorioFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button selefecha, selehora;
    private TextView tvfecha, tvhora;
    private Button guardar;
    private Calendar actual = Calendar.getInstance();
    private Calendar calendar = Calendar.getInstance();

    private int minutos, hora, dia, mes, anio;

    private Context mContext;
    private List<Alarma> listaAlarmas;
    private AlarmasAdapter alarmasAdapter;

    public RecordatorioFragment() {
        // Required empty public constructor
    }

    public static RecordatorioFragment newInstance(String param1, String param2) {
        RecordatorioFragment fragment = new RecordatorioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recordatorio, container, false);
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
            }
        });

        // Inicializar la lista de alarmas
        listaAlarmas = new ArrayList<>();

        // Inicializar el RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_alarmas);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        // Crear y configurar el adaptador
        alarmasAdapter = new AlarmasAdapter(listaAlarmas, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la posición del botón de eliminar
                int position = (int) v.getTag();

                // Realizar acciones para eliminar la alarma en la posición especificada
                eliminarAlarma(position);

                // Después de eliminar, asegúrate de notificar al adaptador para que actualice la vista
                alarmasAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(alarmasAdapter);
    }

    private void mostrarVentanaEmergente() {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View popupView = inflater.inflate(R.layout.ventana_emergente_recordatorios, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(popupView);
        final AlertDialog dialog = builder.create();

        // Agrega esta línea para obtener la referencia al EditText
        EditText editTextNombre = popupView.findViewById(R.id.edtNombreAlarma);

        Button btnCerrar = popupView.findViewById(R.id.btnCerrar);
        selefecha = popupView.findViewById(R.id.btn_seleFecha);
        selehora = popupView.findViewById(R.id.btn_selehora);
        tvfecha = popupView.findViewById(R.id.tv_fecha);
        tvhora = popupView.findViewById(R.id.tv_hora);
        guardar = popupView.findViewById(R.id.btn_guardar);

        selefecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anio = actual.get(Calendar.YEAR);
                mes = actual.get(Calendar.MONTH);
                dia = actual.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        calendar.set(Calendar.DAY_OF_MONTH, d);
                        calendar.set(Calendar.MONTH, m);
                        calendar.set(Calendar.YEAR, y);

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
                        String strDate = format.format(calendar.getTime());
                        tvfecha.setText(strDate);
                    }
                }, anio, mes, dia);
                datePickerDialog.show();
            }
        });

        selehora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora = actual.get(Calendar.HOUR_OF_DAY);
                minutos = actual.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY, h);
                        calendar.set(Calendar.MINUTE, m);

                        tvhora.setText(String.format("%02d:%02d", h, m));
                    }
                }, hora, minutos, true);
                timePickerDialog.show();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el nombre del EditText
                String nombreAlarma = editTextNombre.getText().toString();

                // Verificar si el nombre es válido antes de crear la alarma
                if (!nombreAlarma.isEmpty()) {
                    String tag = generateKey();
                    long timeUntilNotification = calendar.getTimeInMillis() - System.currentTimeMillis();
                    if (timeUntilNotification < 0) {
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        timeUntilNotification = calendar.getTimeInMillis() - System.currentTimeMillis();
                    }
                    int random = (int) (Math.random() * 50 + 1);
                    Data data = guardarData("Recordatorio. No te olvides de tu plantita", "Soy un detalle", random);
                    Workmanagernoti.GuardarNoti(timeUntilNotification, data, tag);

                    // Crear una nueva instancia de Alarma con nombre personalizado
                    Alarma nuevaAlarma = new Alarma(nombreAlarma, calendar);

                    // Agregar la nueva alarma a la lista
                    listaAlarmas.add(nuevaAlarma);

                    // Notificar al adaptador que se ha insertado un nuevo elemento
                    alarmasAdapter.notifyItemInserted(listaAlarmas.size() - 1);

                    Toast.makeText(mContext, "Recordatorio guardado.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(mContext, "Ingrese un nombre para la alarma.", Toast.LENGTH_SHORT).show();
                }
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

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

    private Data guardarData(String titulo, String detalle, int id_noti) {
        return new Data.Builder()
                .putString("titulo", titulo)
                .putString("detalle", detalle)
                .putInt("id_noti", id_noti).build();
    }

    private void eliminarAlarma(int position) {
        if (position >= 0 && position < listaAlarmas.size()) {
            // Obtener la alarma en la posición especificada
            Alarma alarmaEliminar = listaAlarmas.get(position);

            // Realizar acciones para eliminar la alarma (por ejemplo, eliminar notificación, etc.)
            // ...

            // Eliminar la alarma de la lista
            listaAlarmas.remove(position);

            // Notificar al adaptador que se ha eliminado un elemento
            alarmasAdapter.notifyItemRemoved(position);

            Toast.makeText(mContext, "Alarma eliminada.", Toast.LENGTH_SHORT).show();
        }
    }
}
