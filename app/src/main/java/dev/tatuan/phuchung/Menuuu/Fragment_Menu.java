package dev.tatuan.phuchung.Menuuu;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import dev.tatuan.phuchung.Data.MenuData;
import dev.tatuan.phuchung.Data.PostData;
import dev.tatuan.phuchung.ItemClickListener;
import dev.tatuan.phuchung.R;

public class Fragment_Menu extends Fragment {
    RecyclerView rcOfCaffe, rcOfNuocEp, rcOfSinhTo, rcOfTra, rcOfDaxay;

    // Biến truy cập đến Database của Firebase
    DatabaseReference databaseReference;
    DatabaseReference HoaDonData;

    //
    StaggeredGridLayoutManager gridLayoutManager;
    EditText edt_Soluong;
    TextView tv_xacnhan;
    private Dialog mBottommSheetDialog;
    String soBan;
    String gia, hinhanh, tendouong, soluong;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_chinh, container, false);

        //recycler view hiển thị danh sách các món đồ uống
        rcOfCaffe = view.findViewById(R.id.rcOfCaffe);
        caffe();
        rcOfNuocEp = view.findViewById(R.id.rcOfNuocEp);
        nuocep();
        rcOfSinhTo = view.findViewById(R.id.rcOfSinhTo);
        sinhto();
        rcOfTra = view.findViewById(R.id.rcOfTra);
        tra();
        rcOfDaxay = view.findViewById(R.id.rcOfDaxay);
        daxay();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATA", Context.MODE_PRIVATE);
        soBan = sharedPreferences.getString("Ban", "");

        //dialog nhập số lượng
        mBottommSheetDialog = new Dialog(getActivity(), R.style.MaterialDialogSheet);
        mBottommSheetDialog.setContentView(R.layout.soluong);
        mBottommSheetDialog.setCancelable(true);
        mBottommSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottommSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        //
        edt_Soluong = mBottommSheetDialog.findViewById(R.id.edt_Soluong);
        tv_xacnhan = mBottommSheetDialog.findViewById(R.id.btn_xacnhan);


        return view;
    }

    private void caffe() {
        databaseReference = FirebaseDatabase.getInstance().getReference("DoUong").child("Caffe");
        FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder> adapter = new FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder>(MenuData.class, R.layout.item_menu, MenuItemViewHolder.class, databaseReference) {
            @SuppressLint("NewApi")
            @Override
            protected void populateViewHolder(final MenuItemViewHolder viewHolder, final MenuData model, int position) {

                Picasso.with(getActivity()).load(model.getHinhanh()).into(viewHolder.imgMenu);

                viewHolder.tv_tenDoUong.setText(model.getTendouong());

                long tien = Long.parseLong(model.getGia());
                viewHolder.tv_giaTien.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        mBottommSheetDialog.show();
                        gia = model.getGia();
                        hinhanh = model.getHinhanh();
                        tendouong = model.getTendouong();
                        XacNhan();


                    }
                });
            }
        };
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rcOfCaffe.setLayoutManager(gridLayoutManager);
        rcOfCaffe.setAdapter(adapter);
    }

    private void nuocep() {
        databaseReference = FirebaseDatabase.getInstance().getReference("DoUong").child("NuocEp");
        FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder> adapter = new FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder>(MenuData.class, R.layout.item_menu, MenuItemViewHolder.class, databaseReference) {
            @SuppressLint("NewApi")
            @Override
            protected void populateViewHolder(final MenuItemViewHolder viewHolder, final MenuData model, int position) {

                Picasso.with(getActivity()).load(model.getHinhanh()).into(viewHolder.imgMenu);

                viewHolder.tv_tenDoUong.setText(model.getTendouong());

                long tien = Long.parseLong(model.getGia());

                viewHolder.tv_giaTien.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        mBottommSheetDialog.show();
                        gia = model.getGia();
                        hinhanh = model.getHinhanh();
                        tendouong = model.getTendouong();
                        XacNhan();

                    }
                });
            }
        };
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rcOfNuocEp.setLayoutManager(gridLayoutManager);
        rcOfNuocEp.setAdapter(adapter);
    }

    private void sinhto() {
        databaseReference = FirebaseDatabase.getInstance().getReference("DoUong").child("SinhTo");
        FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder> adapter = new FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder>(MenuData.class, R.layout.item_menu, MenuItemViewHolder.class, databaseReference) {
            @SuppressLint("NewApi")
            @Override
            protected void populateViewHolder(final MenuItemViewHolder viewHolder, final MenuData model, int position) {

                Picasso.with(getActivity()).load(model.getHinhanh()).into(viewHolder.imgMenu);

                viewHolder.tv_tenDoUong.setText(model.getTendouong());

                long tien = Long.parseLong(model.getGia());
                viewHolder.tv_giaTien.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        mBottommSheetDialog.show();
                        gia = model.getGia();
                        hinhanh = model.getHinhanh();
                        tendouong = model.getTendouong();
                        XacNhan();


                    }
                });
            }
        };
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rcOfSinhTo.setLayoutManager(gridLayoutManager);
        rcOfSinhTo.setAdapter(adapter);
    }

    private void tra() {
        //hiển thị danh sách đồ uống trong node trà;
        databaseReference = FirebaseDatabase.getInstance().getReference("DoUong").child("Tra");//trỏ tới node Đồ uống và trỏ tiếp tới node trà;

        //là 1 adapter để khởi tạo hiển thị cho item
        FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder> adapter = new FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder>(MenuData.class, R.layout.item_menu, MenuItemViewHolder.class, databaseReference) {
            @SuppressLint("NewApi")
            @Override
            protected void populateViewHolder(final MenuItemViewHolder viewHolder, final MenuData model, int position) {
                Picasso.with(getActivity()).load(model.getHinhanh()).into(viewHolder.imgMenu);

                viewHolder.tv_tenDoUong.setText(model.getTendouong());

                long tien = Long.parseLong(model.getGia());

                viewHolder.tv_giaTien.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));

                //sự kiên click vào đồ uống
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        // chọn số lượng muốn uống
                        mBottommSheetDialog.show();
                        // lấy ra các thông tin cần thiết : giá tiền 1 sp, địa chỉ hình ảnh, tên của sản phẩm
                        gia = model.getGia();
                        hinhanh = model.getHinhanh();
                        tendouong = model.getTendouong();

                        XacNhan();


                    }
                });
            }
        };
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rcOfTra.setLayoutManager(gridLayoutManager);
        rcOfTra.setAdapter(adapter);
    }

    private void daxay() {
        databaseReference = FirebaseDatabase.getInstance().getReference("DoUong").child("DaXay");
        FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder> adapter = new FirebaseRecyclerAdapter<MenuData, MenuItemViewHolder>(MenuData.class, R.layout.item_menu, MenuItemViewHolder.class, databaseReference) {
            @SuppressLint("NewApi")
            @Override
            protected void populateViewHolder(final MenuItemViewHolder viewHolder, final MenuData model, int position) {
//                Toast.makeText(context, hinhanh+"", Toast.LENGTH_SHORT).show();
                Picasso.with(getActivity()).load(model.getHinhanh()).into(viewHolder.imgMenu);

                viewHolder.tv_tenDoUong.setText(model.getTendouong());

                long tien = Long.parseLong(model.getGia());

                viewHolder.tv_giaTien.setText(NumberFormat.getNumberInstance(Locale.US).format(tien));

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        mBottommSheetDialog.show();

                        gia = model.getGia();
                        hinhanh = model.getHinhanh();
                        tendouong = model.getTendouong();
                        XacNhan();

                    }
                });
            }
        };
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rcOfDaxay.setLayoutManager(gridLayoutManager);
        rcOfDaxay.setAdapter(adapter);
    }

    private void XacNhan() {
        //sự kiện xác nhận số lượng đồ uống
        tv_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // lấy ra số lượng đã điền vào
                soluong = edt_Soluong.getText().toString();
                // trường hợp không điền số thì số lượng mặc định là 1
                if (soluong.equals("")) {
                    soluong = "1";
                }

                // trỏ tới node đơn hàng và trỏ tới số bàn đã chọn
                HoaDonData = FirebaseDatabase.getInstance().getReference("DonHang").child(soBan);
                final String id = HoaDonData.push().getKey();

                HoaDonData.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // đẩy thông tin sản phẩm vào số bàn đã chọn ở trong node đơn hàng
                        PostData postData = new PostData(id, gia, hinhanh, tendouong, soluong);
                        HoaDonData.child(id).setValue(postData);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                mBottommSheetDialog.dismiss();

            }

        });
    }
}
