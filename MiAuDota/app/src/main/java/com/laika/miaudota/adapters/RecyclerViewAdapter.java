package com.laika.miaudota.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.laika.miaudota.R;
import com.laika.miaudota.models.*;
import com.laika.miaudota.activities.PerfilActivity;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    RequestOptions option;
    private Context mContext;
    private ArrayList<Animal> mData;

    public RecyclerViewAdapter(Context mContext, ArrayList<Animal> mData) {

        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.activity_animal_linha,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(mContext, PerfilActivity.class);
                i.putExtra("animalNome", mData.get(viewHolder.getAdapterPosition()).getNome());
                i.putExtra("animalIdade", String.valueOf(mData.get(viewHolder.getAdapterPosition()).getIdade()));
                i.putExtra("animalSexo", mData.get(viewHolder.getAdapterPosition()).getSexo());
                i.putExtra("animalVermifugado", String.valueOf(mData.get(viewHolder.getAdapterPosition()).isVermifugado()));
                i.putExtra("animalVacinado", String.valueOf(mData.get(viewHolder.getAdapterPosition()).isVacinado()));
                i.putExtra("animalPeso", String.valueOf(mData.get(viewHolder.getAdapterPosition()).getPeso()));
                i.putExtra("animalPelagem", mData.get(viewHolder.getAdapterPosition()).getPelagem());
                i.putExtra("animalDescricao", mData.get(viewHolder.getAdapterPosition()).getDescricao());
                i.putExtra("animalEndereco", mData.get(viewHolder.getAdapterPosition()).getEndereco());
                i.putExtra("animalFoto", mData.get(viewHolder.getAdapterPosition()).getFotoUrl());
                if(mData.get(viewHolder.getAdapterPosition()) instanceof Cao)
                    i.putExtra("animalPorte", (((Cao)mData.get(viewHolder.getAdapterPosition())).getPorte()));

                mContext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(mData.get(position).isVermifugado())
            holder.tvVermifugado.setText("Vermifugado(a): Sim");
        else
            holder.tvVermifugado.setText("Vermifugado(a): Não");

        if(mData.get(position).isVacinado())
            holder.tvVacinado.setText("Vacinado(a): Sim");
        else
            holder.tvVacinado.setText("Vacinado(a): Não");

        if(mData.get(position) instanceof Cao)
            holder.tvPorte.setText("Porte: " + String.valueOf((((Cao)mData.get(position)).getPorte())));
        else
            holder.tvPorte.setText("");

        holder.tvNome.setText(String.valueOf(mData.get(position).getNome()));
        holder.tvIdade.setText("Idade: " + String.valueOf(mData.get(position).getIdade()) + " ano(s)");
        holder.tvSexo.setText("Sexo: " + String.valueOf(mData.get(position).getSexo()));

        Glide.with(mContext).load(mData.get(position).getFotoUrl()).apply(option).into(holder.ivFoto);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNome;
        TextView tvIdade;
        TextView tvSexo;
        TextView tvVermifugado;
        TextView tvVacinado;
        TextView tvPorte;
        ImageView ivFoto;
        LinearLayout view_container;


        public MyViewHolder(View itemView){

            super(itemView);
            view_container = itemView.findViewById(R.id.container);
            tvNome = itemView.findViewById(R.id.animalNome);
            tvIdade = itemView.findViewById(R.id.animalIdade);
            tvSexo = itemView.findViewById(R.id.animalSexo);
            tvVermifugado = itemView.findViewById(R.id.animalVermifugado);
            tvVacinado = itemView.findViewById(R.id.animalVacinado);
            tvPorte = itemView.findViewById(R.id.animalPorte);
            ivFoto = itemView.findViewById(R.id.animalFoto);

        }

    }

}
