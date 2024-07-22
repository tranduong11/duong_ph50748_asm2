package com.example.duong_ph50748_asm_2.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.duong_ph50748_asm_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HealthyDayActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private Button btnVanDong,btnTinhThan,btnChuyenGia,btnSucKhoe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_healthy_day);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerlayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar=findViewById(R.id.toolbar_healthyday);
        setSupportActionBar(toolbar);
        ActionBar bar=getSupportActionBar();
        bar.setTitle("Healthy Day");
        drawerLayout=findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        init();
    }
    public void init(){
        btnVanDong=findViewById(R.id.btnVanDong);
        btnChuyenGia=findViewById(R.id.btnChuyenGia);
        btnSucKhoe=findViewById(R.id.btnSucKhoe);
        btnTinhThan=findViewById(R.id.btnTinhThan);
        btnTinhThan.setOnClickListener(this);
        btnSucKhoe.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.btnTinhThan){
            startActivity(new Intent(HealthyDayActivity.this, TinhThanActivity.class));
        } else if (id==R.id.btnSucKhoe) {
            startActivity(new Intent(HealthyDayActivity.this,SucKhoeActivity.class));
        }
    }
}