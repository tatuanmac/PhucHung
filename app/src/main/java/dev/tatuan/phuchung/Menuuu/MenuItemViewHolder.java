package dev.tatuan.phuchung.Menuuu;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.tatuan.phuchung.ItemClickListener;
import dev.tatuan.phuchung.R;


public class MenuItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView imgMenu;
    TextView tv_tenDoUong, tv_giaTien;
    public ItemClickListener itemClickListener;

    public MenuItemViewHolder(View itemView) {
        super(itemView);
        imgMenu = itemView.findViewById(R.id.img_menu);
        tv_tenDoUong = itemView.findViewById(R.id.tv_tenDoUong);
        tv_giaTien = itemView.findViewById(R.id.tv_giaTien);

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
