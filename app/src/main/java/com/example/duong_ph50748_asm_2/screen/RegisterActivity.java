package com.example.duong_ph50748_asm_2.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duong_ph50748_asm_2.MainActivity;
import com.example.duong_ph50748_asm_2.R;
import com.example.duong_ph50748_asm_2.dao.UsersDAO;
import com.example.duong_ph50748_asm_2.models.Users;

import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDangNhap,btnTroVe;
    private EditText edtUsername,edtpasword,edtRepassword;
    private List<Users> usersList;
    private Users users;
    private UsersDAO usersDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usersDAO=new UsersDAO(this);
        usersList=usersDAO.getAllDataUsers();
        init();
    }
    public void init(){
        btnDangNhap=findViewById(R.id.btnDangNhap_Register);
        btnTroVe=findViewById(R.id.btnTroLai);
        edtpasword=findViewById(R.id.edtPassword_Register);
        edtRepassword=findViewById(R.id.edtRepassword_resigter);
        edtUsername=findViewById(R.id.edtUserName_Register);
        btnTroVe.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if(id==R.id.btnTroLai){
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        } else if (id==R.id.btnDangNhap_Register) {
            String username=edtUsername.getText().toString().trim();
            String password=edtpasword.getText().toString().trim();
            String retypePassword=edtRepassword.getText().toString().trim();
            if(!username.isEmpty()&&!password.isEmpty()&&!retypePassword.isEmpty()){
                if (password.equals(retypePassword)){
                    users=new Users();
                    users.setUsername(username);
                    users.setPassword(password);
                    boolean check=usersDAO.insertUsers(users);
                    if (check){
                          showToast("dki thanh cong");
                          usersList.add(users);
                          startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    }
                    else {
                        showToast("username đã tồn tại");
                    }
                }
                else {
                    showToast("2 pass chưa giống nhau");
                }
            }
            else {
                showToast("Bạn không được để trống ô nào");
            }
        }
    }

    public void showToast(String v){
        Toast.makeText(RegisterActivity.this, v, Toast.LENGTH_SHORT).show();
    }
}