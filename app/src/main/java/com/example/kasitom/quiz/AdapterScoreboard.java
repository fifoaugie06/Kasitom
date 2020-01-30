package com.example.kasitom.quiz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kasitom.R;
import com.example.kasitom.model.dataScoreBoard;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterScoreboard extends RecyclerView.Adapter<AdapterScoreboard.ViewHolder> {
    private ArrayList<dataScoreBoard> daftarScore;
    private ImageView img_photo;

    public AdapterScoreboard(ArrayList<dataScoreBoard> daftarScore) {
        this.daftarScore = daftarScore;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_scoreboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String nama = daftarScore.get(position).getNama();
        final String photoUri = daftarScore.get(position).getPhotoURI();
        final String correct = daftarScore.get(position).getCorrect();
        final String size = daftarScore.get(position).getSize();
        final String score = daftarScore.get(position).getNilai();

        final int wrong = (Integer.parseInt(size) - Integer.parseInt(correct));

        holder.tv_nama.setText(nama);
        holder.tv_correctVal.setText(correct);
        holder.tv_wrongVal.setText(String.valueOf(wrong));
        holder.tv_score.setText(score);
        Picasso.get()
                .load(photoUri)
                .centerInside()
                .resize(350, 350)
                .into(img_photo);
    }

    @Override
    public int getItemCount() {
        return daftarScore.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nama, tv_correctVal, tv_wrongVal, tv_score, tv_tanggal, tv_fetchData;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_photo = itemView.findViewById(R.id.img_photo);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            tv_correctVal = itemView.findViewById(R.id.tv_correct_value);
            tv_wrongVal = itemView.findViewById(R.id.tv_wrong_value);
            tv_score = itemView.findViewById(R.id.tv_score_value);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
        }
    }
}
