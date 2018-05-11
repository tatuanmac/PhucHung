package dev.tatuan.phuchung.ChonDo;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.phuchung.R;

public class ChonDoViewHolder extends RecyclerView.ViewHolder {

    ImageView imghd_hinhanh, img_suachua, img_xoa;

    TextView tvhd_tendouong, tvhd_soluong, tvhd_giatien;

    public ChonDoViewHolder(View itemView) {
        super(itemView);
        img_suachua = itemView.findViewById(R.id.img_suachua);
        img_xoa = itemView.findViewById(R.id.img_xoa);

        imghd_hinhanh = itemView.findViewById(R.id.imghd_hinhanh);
        tvhd_tendouong = itemView.findViewById(R.id.tvhd_tendouong);
        tvhd_soluong = itemView.findViewById(R.id.tvhd_soluong);
        tvhd_giatien = itemView.findViewById(R.id.tvhd_giatien);
    }
}
