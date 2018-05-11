package dev.tatuan.phuchung.ChonDo;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import dev.tatuan.phuchung.Data.PostData;
import dev.tatuan.phuchung.R;

public class ChonDoAdapter extends RecyclerView.Adapter<ChonDoViewHolder> {
    Context mContext;
    ArrayList<PostData> datas;
    DatabaseReference mXoa;
    String soBan, id, gia, hinhanh, tendouong;
    Dialog mBottommSheetDialog;
    EditText edt_Soluong;
    TextView tv_xacnhan;
    DatabaseReference mSua;

    public ChonDoAdapter(Context mContext, ArrayList<PostData> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ChonDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chondo, parent, false);

        return new ChonDoViewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull ChonDoViewHolder holder, int position) {
        final PostData data = datas.get(position);


        SharedPreferences sharedPreferences = mContext.getSharedPreferences("DATA", Context.MODE_PRIVATE);
        soBan = sharedPreferences.getString("Ban", "");

        holder.tvhd_tendouong.setText(data.getTendouong());
        holder.tvhd_soluong.setText(data.getSoluong());

        //
        Integer sl = Integer.parseInt(data.getSoluong());
        Integer tiengoc = Integer.parseInt(data.getGia());

        Integer tien = sl * tiengoc;

        holder.tvhd_giatien.setText(NumberFormat.getNumberInstance(Locale.US).format(tien) + " đ");

        Picasso.with(mContext).load(data.getHinhanh()).into(holder.imghd_hinhanh);
        //btn xóa item
        holder.img_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = data.getId();
                mXoa = FirebaseDatabase.getInstance().getReference("DonHang").child(soBan).child(id);
                mXoa.removeValue();
                Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
            }
        });


        //dialog nhập số lượng
        mBottommSheetDialog = new Dialog(mContext, R.style.MaterialDialogSheet);
        mBottommSheetDialog.setContentView(R.layout.soluong);
        mBottommSheetDialog.setCancelable(true);
        mBottommSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottommSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        //
        edt_Soluong = mBottommSheetDialog.findViewById(R.id.edt_Soluong);
        tv_xacnhan = mBottommSheetDialog.findViewById(R.id.btn_xacnhan);

        holder.img_suachua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottommSheetDialog.show();

                tv_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String soluong = edt_Soluong.getText().toString();
                        if (soluong.equals("")) {
                            soluong = "1";
                        }
                        id = data.getId();
                        gia = data.getGia();
                        hinhanh = data.getHinhanh();
                        tendouong = data.getTendouong();
                        if (!soluong.equals("0")) {
//                         số lượng khác 0 thì sửa
                            mSua = FirebaseDatabase.getInstance().getReference("DonHang").child(soBan).child(id);
                            final String finalSoluong = soluong;
                            mSua.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    PostData changeData = new PostData(id, gia, hinhanh, tendouong, finalSoluong);
                                    mSua.setValue(changeData);
                                    Toast.makeText(mContext, "Change", Toast.LENGTH_SHORT).show();
                                    mBottommSheetDialog.dismiss();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        //nếu số lượng bằng 0 thì xóa
                        else {
                            mXoa = FirebaseDatabase.getInstance().getReference("DonHang").child(soBan).child(id);
                            mXoa.removeValue();
                            mBottommSheetDialog.dismiss();

                        }


                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
