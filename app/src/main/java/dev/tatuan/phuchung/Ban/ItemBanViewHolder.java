package dev.tatuan.phuchung.Ban;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.phuchung.ItemClickListener;
import dev.tatuan.phuchung.R;

public class ItemBanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tv_tenban;
    ImageView img_hinhanh;
    ItemClickListener itemClickListener;
    CardView cv_Ban;

    public ItemBanViewHolder(View itemView) {
        super(itemView);
        tv_tenban = itemView.findViewById(R.id.tenban);
        img_hinhanh = itemView.findViewById(R.id.img_hinhanh);
        cv_Ban = itemView.findViewById(R.id.cvBan);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);

    }
}
