package com.example.duong_ph50748_asm_2;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duong_ph50748_asm_2.dao.UsersDAO;
import com.example.duong_ph50748_asm_2.models.Users;
import com.example.duong_ph50748_asm_2.screen.HealthyDayActivity;
import com.example.duong_ph50748_asm_2.screen.RegisterActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDangNhap,btnDangKi;
    private CheckBox cbGhiNho;
    private EditText edtUsername,edtpasword;
    private UsersDAO usersDAO;
    private List<Users> usersList;
    private Users users;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login1);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        usersDAO=new UsersDAO(this);
        usersList=usersDAO.getAllDataUsers();
        init();
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        edtUsername.setText(sharedPreferences.getString("username", ""));
        edtpasword.setText(sharedPreferences.getString("pass",""));
        cbGhiNho.setChecked(sharedPreferences.getBoolean("checked", false));
    }
    public void init(){
        btnDangNhap=findViewById(R.id.btnDangNhap_Login);
        btnDangKi=findViewById(R.id.btnDangKi_login);
        cbGhiNho=findViewById(R.id.cbGhiNho);
        edtUsername=findViewById(R.id.edtUserName_Login);
        edtpasword=findViewById(R.id.edtPassword_Login);
        btnDangKi.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.btnDangKi_login){
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        } else if (id==R.id.btnDangNhap_Login) {
            String username=edtUsername.getText().toString().trim();
            String pass=edtpasword.getText().toString().trim();
            users=new Users();
            boolean check=false;
            if (!username.isEmpty()&&!pass.isEmpty()){
                for (int i=0;i<=usersList.size()-1;i++){
                    if (username.equals(usersList.get(i).getUsername())&&pass.equals(usersList.get(i).getPassword())){
                        check=true;
                        break;
                    }
                }
                if (check){
                    showToast("đúng");
                    Intent intent=new Intent(MainActivity.this, HealthyDayActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    if (cbGhiNho.isChecked()){
                        editor.putString("username",username);
                        editor.putString("pass",pass);
                        editor.putBoolean("checked",false);
                    }
                    else {
                        editor.remove("username");
                        editor.remove("pass");
                        editor.remove("checked");
                    }
                    editor.commit();
                }
                else  {
                    showToast("Username hoặc Password không đúng");
                }

            }
            else {
                showToast("Bạn không được để trống");
            }
        }
    }
    public void showToast(String v){
        Toast.makeText(MainActivity.this, v, Toast.LENGTH_SHORT).show();
    }
}
