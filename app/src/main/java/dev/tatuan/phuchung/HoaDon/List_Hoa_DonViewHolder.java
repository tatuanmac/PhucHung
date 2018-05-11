package dev.tatuan.phuchung.HoaDon;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import dev.tatuan.phuchung.R;

/**
 * Created by tatuan on 10/05/2018.
 */

public class List_Hoa_DonViewHolder extends RecyclerView.ViewHolder {
    RecyclerView rcOfHoaDon;

    public List_Hoa_DonViewHolder(View itemView) {
        super(itemView);
        rcOfHoaDon = itemView.findViewById(R.id.recyclerDonHang);
    }
}
