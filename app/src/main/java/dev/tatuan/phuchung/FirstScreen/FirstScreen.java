package dev.tatuan.phuchung.FirstScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import dev.tatuan.phuchung.Ban.BanActivity;
import dev.tatuan.phuchung.R;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        ImageView icon_coffee = findViewById(R.id.icon_coffee);
        icon_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BanActivity.class));
            }
        });
    }
}
