package com.ramirez.laboratorio;

import static androidx.core.content.ContextCompat.createDeviceProtectedStorageContext;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SalonesAdaptador extends RecyclerView.Adapter<SalonesAdaptador.viewholder> {

    List<Salones> salones;

    public SalonesAdaptador(List<Salones> LE){
        this.salones = LE;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_elementos,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.setData(salones.get(position));
    }

    @Override
    public int getItemCount() {
        return salones.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Salones resultsholder;
        TextView nombre;
        TextView resultado;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.salonTextView);
            resultado = itemView.findViewById(R.id.lugarTextView);
            itemView.setOnClickListener(this);
        }

        public void setData(Salones resultsholder)
        {
            this.resultsholder = resultsholder;
            nombre.setText(resultsholder.getNombre());
            resultado.setText(resultsholder.getEdificio());
        }

        @Override
        public void onClick(View view) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(itemView.getContext(),SensoresSalonActivity.class);
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }


}