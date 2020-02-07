package com.example.kasitom.antonimSinonim;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kasitom.R;
import com.example.kasitom.model.dataKamus;

import java.util.ArrayList;

class AdapterAntonimSinonim extends RecyclerView.Adapter<AdapterAntonimSinonim.ViewHolder> {
    private ArrayList<dataKamus> daftarKamus;

    AdapterAntonimSinonim(ArrayList<dataKamus> daftarKamus) {
        this.daftarKamus = daftarKamus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_data, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String judul = daftarKamus.get(position).getJudul();
        final String arti = daftarKamus.get(position).getArti();

        holder.tvJudul.setText(judul);
        holder.tvArti.setText(arti);

        boolean isExpanded = daftarKamus.get(position).isExpanded();
        if (isExpanded){
            holder.expandableLayout.setVisibility(View.VISIBLE);
        }else {
            holder.expandableLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return daftarKamus.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout expandableLayout;
        TextView tvJudul, tvArti;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvArti = itemView.findViewById(R.id.tv_arti);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            tvJudul.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataKamus dataKamus = daftarKamus.get(getAdapterPosition());
                    dataKamus.setExpanded(!dataKamus.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
