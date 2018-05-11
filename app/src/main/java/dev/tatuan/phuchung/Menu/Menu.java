package dev.tatuan.phuchung.Menu;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import dev.tatuan.phuchung.ChonDo.Fragment_ChonDo;
import dev.tatuan.phuchung.HoaDon.Fragment_HoaDonThanhToan;
import dev.tatuan.phuchung.Menuuu.Fragment_Menu;
import dev.tatuan.phuchung.R;

public class Menu extends AppCompatActivity {
    TextView tv_tenban;
    Bundle bundle;
    String ban,stt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        tv_tenban = findViewById(R.id.tv_tenban);

        bundle = getIntent().getExtras();
        stt = bundle.getString("stt");
        ban = bundle.getString("soban");
        SharedPreferences sPre = getSharedPreferences("DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPre.edit();
        editor.putString("Ban", ban);
        editor.putString("stt",stt);
        editor.apply();


        tv_tenban.setText(ban);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_Menu()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_menu:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_Menu()).commit();
                    break;
                case R.id.nav_chondo:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_ChonDo()).commit();
                    break;
                case R.id.nav_hoadonthanhtoan:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new Fragment_HoaDonThanhToan()).commit();
                    break;
            }
            return false;
        }

    };
}
