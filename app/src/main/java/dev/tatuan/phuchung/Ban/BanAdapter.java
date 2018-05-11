package dev.tatuan.phuchung.Ban;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dev.tatuan.phuchung.Data.TableData;
import dev.tatuan.phuchung.ItemClickListener;
import dev.tatuan.phuchung.Menu.Menu;
import dev.tatuan.phuchung.R;

public class BanAdapter extends RecyclerView.Adapter<ItemBanViewHolder> {

    public ArrayList<TableData> tableData;
    public Context mContext;

    public BanAdapter(ArrayList<TableData> tableData, Context mContext) {
        this.tableData = tableData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ItemBanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tableitem, parent, false);

        return new ItemBanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemBanViewHolder holder, int vitri) {
        final TableData table = tableData.get(vitri);
        final String ban = table.getSoban();

        holder.tv_tenban.setText(ban);
        Picasso.with(mContext).load(table.getHinhanh()).into(holder.img_hinhanh);
        //không có khách ngồi thì nền trắng chữ đen
        if (table.getTrangthai().equals("0")) {
            holder.tv_tenban.setTextColor(Color.parseColor("#000000"));
            //nếu có khách ngồi thì nền xanh chữ trắng
        } else {
            holder.tv_tenban.setTextColor(Color.parseColor("#ff0000"));
        }


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(mContext, Menu.class);
                intent.putExtra("soban", ban);
                intent.putExtra("stt", table.getStt());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tableData.size();
    }


}
