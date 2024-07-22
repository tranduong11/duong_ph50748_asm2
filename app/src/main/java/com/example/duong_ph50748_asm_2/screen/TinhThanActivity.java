package com.example.duong_ph50748_asm_2.screen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duong_ph50748_asm_2.R;
import com.example.duong_ph50748_asm_2.adapter.BietOnAdapter;
import com.example.duong_ph50748_asm_2.dao.UsersDAO;
import com.example.duong_ph50748_asm_2.models.BietOn;

import java.util.ArrayList;

public class TinhThanActivity extends AppCompatActivity {
    private Button btnAdd;
    private ArrayList<BietOn> listBietOn;
    private BietOnAdapter adapter;
    private RecyclerView recyclerView;
    private EditText edtBietOn;
    private UsersDAO usersDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tinh_than);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar=findViewById(R.id.toolbar_tinhThan);
        setSupportActionBar(toolbar);
        ActionBar bar=getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Tinh thần");
        btnAdd=findViewById(R.id.btnAdd);
        recyclerView=findViewById(R.id.recycle_bieton);
        usersDAO=new UsersDAO(this);
        listBietOn= (ArrayList<BietOn>) usersDAO.getAllDataBietOn();

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);

        adapter=new BietOnAdapter(this,listBietOn);
        recyclerView.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               customDialog();
            }
        });
    }
    public void customDialog(){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_bieton);
        edtBietOn=dialog.findViewById(R.id.edt_bieton);
        Button btnDongY=dialog.findViewById(R.id.btnDongY);
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loibieton=edtBietOn.getText().toString().trim();
                BietOn bietOn=new BietOn();
                if (!loibieton.isEmpty()){
                    bietOn.setLoiBietOn(loibieton);
                    boolean check=usersDAO.insertBietOn(bietOn);
                    if (check){
                        showToast("thành công");
                        listBietOn.add(bietOn);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                    else showToast("kh thành công");
                }
                else showToast("Bạn cần nhập lời biết ơn");
            }
        });
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String v){
        Toast.makeText(TinhThanActivity.this, v, Toast.LENGTH_SHORT).show();
    }
}