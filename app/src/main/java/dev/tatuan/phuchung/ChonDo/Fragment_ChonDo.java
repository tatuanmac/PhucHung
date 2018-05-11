package dev.tatuan.phuchung.ChonDo;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dev.tatuan.phuchung.Data.PostData;
import dev.tatuan.phuchung.Data.TableData;
import dev.tatuan.phuchung.R;

public class Fragment_ChonDo extends Fragment {
    RecyclerView rcOfChonDo;
    ArrayList<PostData> datas;
    ChonDoAdapter chonDoAdapter;
    LinearLayoutManager linearLayoutManager;
    PostData postData;
    DatabaseReference mChonDo;
    String soBan, stt, image;
    ImageView imgDellAll;
    TextView tv_XuatHoaDOn;
    AlertDialog.Builder aler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chondo, container, false);

        aler = new AlertDialog.Builder(getContext());
        rcOfChonDo = view.findViewById(R.id.rcOfChonDo);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
        soBan = sharedPreferences.getString("Ban", "");
        stt = sharedPreferences.getString("stt", "");
        image = "https://imgur.com/mv6OAKS.jpg";

        datas = new ArrayList<>();
        chonDoAdapter = new ChonDoAdapter(getActivity(), datas);
        //định dạng adapter
        linearLayoutManager = new LinearLayoutManager(getActivity());
        getDatas();
        rcOfChonDo.setLayoutManager(linearLayoutManager);
        rcOfChonDo.setAdapter(chonDoAdapter);
        imgDellAll = view.findViewById(R.id.imgDellAll);

        tv_XuatHoaDOn = view.findViewById(R.id.tv_XuatHoaDOn);
        XoaHet();
        XuatHoaDon();
        return view;
    }

    public void XoaHet() {
        imgDellAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aler.setCancelable(false);
                aler.setMessage("Xóa hết đồ uống đã chọn?");
                aler.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //thay đổi trạng thái của bàn cũ - bàn hiện tại thành trống
                        final DatabaseReference mDataTrangThaiMoi = FirebaseDatabase.getInstance().getReference("Ban").child(stt);
                        mDataTrangThaiMoi.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                TableData tableData = new TableData(stt, image, soBan, "0");
                                mDataTrangThaiMoi.setValue(tableData);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        DatabaseReference mData = FirebaseDatabase.getInstance().getReference("DonHang").child(soBan);
                        mData.removeValue();
                    }
                });
                aler.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                aler.show();
            }
        });
    }

    public void XuatHoaDon() {

        tv_XuatHoaDOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aler.setCancelable(false);
                aler.setMessage("Xuất hóa đơn " + soBan);
                aler.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //chuyển các item trong đồ đã chọn sang bên hóa đơn
                        final DatabaseReference mDataHoaDon = FirebaseDatabase.getInstance().getReference("HoaDon").child(soBan);
                        final String id = mDataHoaDon.push().getKey();
                        mDataHoaDon.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                PostData postData = new PostData(id, gia, hinhanh, tendouong, soluong);
                                //chuyen du lieu
                                mDataHoaDon.child(id).setValue(datas);
                                // xóa các item ở bên chọn đồ
                                DatabaseReference mDataXoa = FirebaseDatabase.getInstance().getReference("DonHang").child(soBan);
                                mDataXoa.removeValue();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        //thay đổi trạng thái của bàn thành có người ngồi
                        final String image = "https://imgur.com/mv6OAKS.jpg";
                        final DatabaseReference mData = FirebaseDatabase.getInstance().getReference("Ban").child(stt);
                        mData.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                TableData tableData = new TableData(stt, image, soBan, "1");
                                mData.setValue(tableData);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

                aler.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                aler.show();
            }
        });
    }

    // lấy dữ liệu từ database về Object
    private void getDatas() {
        mChonDo = FirebaseDatabase.getInstance().getReference("DonHang").child(soBan);
        mChonDo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datas.clear();
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        postData = data.getValue(PostData.class);
                        datas.add(postData);
                    }
                }
                chonDoAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
