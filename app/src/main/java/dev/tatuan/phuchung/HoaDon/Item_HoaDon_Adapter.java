package dev.tatuan.phuchung.HoaDon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import dev.tatuan.phuchung.Data.PostData;
import dev.tatuan.phuchung.R;

/**
 * Created by tatuan on 10/05/2018.
 */

public class Item_HoaDon_Adapter extends RecyclerView.Adapter<Item_HoaDon_ViewHolder> {
    ArrayList<PostData> arrItemPost;
    private Context mContext;

    public Item_HoaDon_Adapter(ArrayList<PostData> arrItemPost, Context mContext) {
        this.arrItemPost = arrItemPost;
        this.mContext = mContext;
    }

    @Override
    public Item_HoaDon_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hoadon_thanhtoan, parent, false);



        return new Item_HoaDon_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Item_HoaDon_ViewHolder holder, int position) {
        PostData postData = arrItemPost.get(position);
        holder.tvtt_tendouong.setText(postData.getTendouong());

        Integer sl = Integer.parseInt(postData.getSoluong());
        Integer tiengoc = Integer.parseInt(postData.getGia());

        Integer tien = sl * tiengoc;

        holder.tvtt_giatien.setText(NumberFormat.getNumberInstance(Locale.US).format(tien) + " đ");

        holder.tvtt_soluong.setText(postData.getSoluong());

        Picasso.with(mContext).load(postData.getHinhanh()).into(holder.imgtt_hinhanh);

    }

    @Override
    public int getItemCount() {
        return arrItemPost.size();
    }
}
