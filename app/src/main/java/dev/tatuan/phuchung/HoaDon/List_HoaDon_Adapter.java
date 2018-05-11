package dev.tatuan.phuchung.HoaDon;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import dev.tatuan.phuchung.Data.PostData;
import dev.tatuan.phuchung.R;

/**
 * Created by tatuan on 10/05/2018.
 */

public class List_HoaDon_Adapter extends RecyclerView.Adapter<List_Hoa_DonViewHolder> {
    ArrayList<PostData> arrPost;
    ArrayList<String> arrString;
    private Context mContext;
    Item_HoaDon_Adapter adapter;

    public List_HoaDon_Adapter(ArrayList<String> arrString, Context mContext) {
        this.arrString = arrString;
        this.mContext = mContext;
    }

    HashMap<String, ArrayList<PostData>> mapDonHang;

    public void setMapDonHang(HashMap<String, ArrayList<PostData>> mapDonHang) {
        this.mapDonHang = mapDonHang;
    }

    @Override
    public List_Hoa_DonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_hoadon, parent, false);

        return new List_Hoa_DonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(List_Hoa_DonViewHolder holder, int position) {
        arrPost = mapDonHang.get(arrString.get(position));
        adapter = new Item_HoaDon_Adapter(arrPost, mContext);

        holder.rcOfHoaDon.setAdapter(adapter);
        holder.rcOfHoaDon.setLayoutManager(new LinearLayoutManager(mContext));

    }

    @Override
    public int getItemCount() {
        return arrString.size();
    }
}
