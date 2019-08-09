package com.example.icardemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap, btnHuy;
    EditText edtTaikhoan, edtMatkhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ControlButton();
    }

    private void ControlButton() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có muốn thoát");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                login1();
            }
        });
    }
    private void login(){
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.112:81/icarserver/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("1")){
                            startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Wrong username or password",Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("taikhoan", edtTaikhoan.getText().toString().trim());
                params.put("matkhau", edtMatkhau.getText().toString().trim());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void login1(){
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.112:81/icarserver/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("2")){
                            startActivity(new Intent(getApplicationContext(), MemberActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Wrong username or password",Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("taikhoan", edtTaikhoan.getText().toString().trim());
                params.put("matkhau", edtMatkhau.getText().toString().trim());
                return params;            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void init() {
        btnDangNhap = findViewById(R.id.btn_dangnhap);
        btnHuy = findViewById(R.id.btn_huy);
        edtTaikhoan = findViewById(R.id.edt_taikhoan);
        edtMatkhau = findViewById(R.id.edt_matkhau);

    }


}
