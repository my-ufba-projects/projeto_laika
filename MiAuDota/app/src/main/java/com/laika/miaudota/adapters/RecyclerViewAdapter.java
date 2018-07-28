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
import com.laika.miaudota.outros.IConstants;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private RequestOptions option;
    private Context mContext;
    private List<Animal> mData;

    public RecyclerViewAdapter(Context mContext, List<Animal> mData) {

        this.mContext = mContext;
        this.mData = mData;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.activity_animal_linha,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.viewContainer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(mContext, PerfilActivity.class);
                i.putExtra("id", mData.get(viewHolder.getAdapterPosition()).getId());
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
            holder.tvVermifugado.setText(mContext.getString(R.string.vermifugado_sim));
        else
            holder.tvVermifugado.setText(mContext.getString(R.string.vermifugado_nao));

        if(mData.get(position).isVacinado())
            holder.tvVacinado.setText(mContext.getString(R.string.vacinado_sim));
        else
            holder.tvVacinado.setText(mContext.getString(R.string.vermifugado_nao));

        if(mData.get(position) instanceof Cao)
            holder.tvPorte.setText(String.format("%s%s", mContext.getString(R.string.porte_question), String.valueOf((((Cao) mData.get(position)).getPorte()))));
        else
            holder.tvPorte.setText(IConstants.BLANK);

        holder.tvNome.setText(String.valueOf(mData.get(position).getNome()));
        holder.tvIdade.setText(String.format("%s%s%s", mContext.getString(R.string.idade_question), String.valueOf(mData.get(position).getIdade()), mContext.getString(R.string.anos_postquestion)));
        holder.tvSexo.setText(String.format("%s%s", mContext.getString(R.string.sexo_question), String.valueOf(mData.get(position).getSexo())));

        if(!(mData.get(position).getFotoUrl().contains(IConstants.JPG_EXTENSION)) && !(mData.get(position).getFotoUrl().contains(IConstants.JPEG_EXTENSION)) && !(mData.get(position).getFotoUrl().contains(IConstants.PNG_EXTENSION)))
            mData.get(position).setFotoUrl(IConstants.URL_FOTO_PADRAO);

        Glide.with(mContext).load(mData.get(position).getFotoUrl()).apply(option).into(holder.ivFoto);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNome;
        TextView tvIdade;
        TextView tvSexo;
        TextView tvVermifugado;
        TextView tvVacinado;
        TextView tvPorte;
        ImageView ivFoto;
        LinearLayout viewContainer;

        private MyViewHolder(View itemView){

            super(itemView);
            viewContainer = itemView.findViewById(R.id.container);
            tvNome = itemView.findViewById(R.id.animal_nome);
            tvIdade = itemView.findViewById(R.id.animal_idade);
            tvSexo = itemView.findViewById(R.id.animal_sexo);
            tvVermifugado = itemView.findViewById(R.id.animal_vermifugado);
            tvVacinado = itemView.findViewById(R.id.animal_vacinado);
            tvPorte = itemView.findViewById(R.id.animal_porte);
            ivFoto = itemView.findViewById(R.id.animal_foto);

        }

    }

    //Método que atualiza o recyclerView com base no filtro da busca
    public void updateList( List<Animal> newList){
        mData = new ArrayList<>();
        mData.addAll(newList);
        notifyDataSetChanged();
    }

}
