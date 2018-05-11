package dev.tatuan.phuchung.HoaDon;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import dev.tatuan.phuchung.Data.PostData;
import dev.tatuan.phuchung.Data.TableData;
import dev.tatuan.phuchung.R;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_HoaDonThanhToan extends Fragment {
    TextView tv_DoiBan, tv_ThanhToan;

    Dialog mBottommSheetDialog;

    EditText edt_SoBan;

    TextView btn_chuyenban;

    String soBan, stt, image;

    DatabaseReference mHoaDon, mDataChuyenBan, mDataTrangThaiCu, mDataTrangThaiMoi;

    RecyclerView rcOfHoaDon;

    List_HoaDon_Adapter listHoaDonAdapter;

    ArrayList<String> arrList;

    private HashMap<String, ArrayList<PostData>> mapDonHang;

    AlertDialog.Builder aler;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoadonthanhtoan, container, false);
        //
        image = "https://imgur.com/mv6OAKS.jpg";
        //
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
        soBan = sharedPreferences.getString("Ban", "");
        stt = sharedPreferences.getString("stt", "");

        mHoaDon = FirebaseDatabase.getInstance().getReference("HoaDon").child(soBan);
        //
        rcOfHoaDon = view.findViewById(R.id.rcOfHoaDon);

        getDatas();

        dialog();

        tv_ThanhToan = view.findViewById(R.id.tv_ThanhToan);
        ThanhToan();


        tv_DoiBan = view.findViewById(R.id.tv_DoiBan);
        DoiBan();
        new LoadData().execute();

        return view;
    }

    private void getDatas() {
        //
        arrList = new ArrayList<>();
        listHoaDonAdapter = new List_HoaDon_Adapter(arrList, getActivity());
        mapDonHang = new HashMap<>();

        rcOfHoaDon.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcOfHoaDon.setAdapter(listHoaDonAdapter);
    }

    private void dialog() {
        aler = new AlertDialog.Builder(getActivity());

        //dialog đổi bàn
        mBottommSheetDialog = new Dialog(getActivity(), R.style.MaterialDialogSheet);
        mBottommSheetDialog.setContentView(R.layout.doi_ban);
        mBottommSheetDialog.setCancelable(true);
        mBottommSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottommSheetDialog.getWindow().setGravity(Gravity.BOTTOM);

        //
        edt_SoBan = mBottommSheetDialog.findViewById(R.id.edt_SoBan);
        btn_chuyenban = mBottommSheetDialog.findViewById(R.id.btn_chuyenban);
    }

    private void ThanhToan() {
        tv_ThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aler.setCancelable(false);
                aler.setMessage("Thanh toán");
                aler.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        calendar = Calendar.getInstance();
                        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        final String date = simpleDateFormat.format(calendar.getTime());

                        //thay đổi trạng thái của bàn cũ - bàn hiện tại thành trống
                        mDataTrangThaiMoi = FirebaseDatabase.getInstance().getReference("Ban").child(stt);
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

                        //
                        mDataChuyenBan = FirebaseDatabase.getInstance().getReference("ThongKe").child(date);
                        mDataChuyenBan.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                mDataChuyenBan.child(soBan).setValue(mapDonHang);

                                // xóa dữ liệu bàn cũ
                                DatabaseReference mHoaDonn = FirebaseDatabase.getInstance().getReference("HoaDon").child(soBan);
                                mHoaDonn.removeValue();
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

    private void DoiBan() {
        tv_DoiBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottommSheetDialog.show();

                btn_chuyenban.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //chuyển dữ liệu từ bàn cũ sang bàn mới
                        final String soBanMoi = edt_SoBan.getText().toString();

                        mDataChuyenBan = FirebaseDatabase.getInstance().getReference("HoaDon").child("Bàn " + soBanMoi);
                        mDataChuyenBan.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                mDataChuyenBan.setValue(mapDonHang);

                                // xóa dữ liệu bàn cũ
                                DatabaseReference mHoaDonn = FirebaseDatabase.getInstance().getReference("HoaDon").child(soBan);
                                mHoaDonn.removeValue();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        //thay đổi trạng thái của bàn mới thành có người ngồi

                        mDataTrangThaiCu = FirebaseDatabase.getInstance().getReference("Ban").child(soBanMoi);
                        mDataTrangThaiCu.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                TableData tableData = new TableData(soBanMoi, image, "Bàn " + soBanMoi, "1");
                                mDataTrangThaiCu.setValue(tableData);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        //thay đổi trạng thái của bàn cũ - bàn hiện tại thành trống
                        mDataTrangThaiMoi = FirebaseDatabase.getInstance().getReference("Ban").child(stt);
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

                        mBottommSheetDialog.dismiss();
                    }
                });

            }
        });
    }

    private class LoadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //child tới node đơn hàng + số điện thoại
            mHoaDon.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    arrList.clear();

                    // lấy ra các value của node
                    if (dataSnapshot.getValue() != null) {
                        for (final DataSnapshot data : dataSnapshot.getChildren()) {

                            arrList.add(data.getKey());

                            final ArrayList<PostData> arrDonHang = new ArrayList<>();

                            //child tới node đơn hàng của tôi trong giỏ hàng
                            mHoaDon.child(data.getKey()).addValueEventListener(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.getValue() != null) {
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            arrDonHang.add(dataSnapshot1.getValue(PostData.class));
                                        }
                                        mapDonHang.put(data.getKey(), arrDonHang);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                publishProgress();
                            }
                        }, 500);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            listHoaDonAdapter.setMapDonHang(mapDonHang);
            listHoaDonAdapter.notifyDataSetChanged();
        }
    }
}