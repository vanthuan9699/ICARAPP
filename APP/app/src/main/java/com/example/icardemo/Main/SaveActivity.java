package com.example.icardemo.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.icardemo.R;

import java.util.HashMap;
import java.util.Map;

public class SaveActivity extends AppCompatActivity {
    TextView tvTennguoidung, tvMaymay, tvMakichhoat;
    Button btnOK, btnHuyok;
    String urlSave = "http://192.168.0.112:81/icarserver/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("");
        Admin admin = (Admin) intent.getSerializableExtra("dataAdmin");
        Toast.makeText(this, admin.getTaikhoan(), Toast.LENGTH_SHORT).show();


        Anhxa();
        tvTennguoidung.setText(admin.getTaikhoan());
        String mamay = bundle.getString("mamay");
        String makichhoat = bundle.getString("makichhoat");
        tvMaymay.setText(mamay);
        tvMakichhoat.setText(makichhoat);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveMember(urlSave);
            }
        });
        btnHuyok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        tvTennguoidung = findViewById(R.id.tv_tennguoidung);
        tvMaymay = findViewById(R.id.tv_mamay);
        tvMakichhoat = findViewById(R.id.tv_makichhoat);
        btnOK = findViewById(R.id.btn_ok);
        btnHuyok = findViewById(R.id.btn_huy_ok);
    }
    private void SaveMember(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(SaveActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SaveActivity.this, MemberActivity.class));

                        }else {
                            Toast.makeText(SaveActivity.this, "Lỗi lưu", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SaveActivity.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi!\n" + error.toString());

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("taikhoannd", tvTennguoidung.getText().toString().trim());
                params.put("makichhoatnd", tvMakichhoat.getText().toString().trim());
                params.put("mamaynd", tvMaymay.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);


    }
}
