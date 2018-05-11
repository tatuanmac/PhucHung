package dev.tatuan.phuchung.Ban;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dev.tatuan.phuchung.Data.TableData;
import dev.tatuan.phuchung.R;

public class BanActivity extends AppCompatActivity {
    RecyclerView recyclerOfBan;
    DatabaseReference ban, check;
    ArrayList<TableData> tableData;
    BanAdapter adapter;
    StaggeredGridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerOfBan = findViewById(R.id.rcOfBan);

        tableData = new ArrayList<>();
        adapter = new BanAdapter(tableData, this);

        //
        ban = FirebaseDatabase.getInstance().getReference("Ban");
        ban.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tableData.clear();
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        TableData tData = data.getValue(TableData.class);
                        tableData.add(tData);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //dinh dang
        gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        recyclerOfBan.setLayoutManager(gridLayoutManager);//gan kieu dinh dang vao danh sach

        recyclerOfBan.setAdapter(adapter);//gan kieu dinh dang item vao danh sach

    }

    @Override
    public void onBackPressed() {

    }
}
