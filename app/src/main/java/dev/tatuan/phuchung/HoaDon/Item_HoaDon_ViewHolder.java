package dev.tatuan.phuchung.HoaDon;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.phuchung.R;

/**
 * Created by tatuan on 10/05/2018.
 */

public class Item_HoaDon_ViewHolder extends RecyclerView.ViewHolder {
    ImageView imgtt_hinhanh;
    TextView tvtt_tendouong, tvtt_soluong, tvtt_giatien;

    public Item_HoaDon_ViewHolder(View itemView) {
        super(itemView);
        imgtt_hinhanh = itemView.findViewById(R.id.imgtt_hinhanh);

        tvtt_giatien = itemView.findViewById(R.id.tvtt_giatien);

        tvtt_tendouong = itemView.findViewById(R.id.tvtt_tendouong);

        tvtt_soluong = itemView.findViewById(R.id.tvtt_soluong);

    }
}
