package com.example.icardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateADActivity extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau, edtSDT, edtTenDaiLi, edtSoLanSD;
    Button btnUpdate, btnHuy;

    int id = 0;
    String urlUpdate = "http://192.168.0.112:81/icarserver/update.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ad);
        Intent intent = getIntent();
        Admin admin = (Admin) intent.getSerializableExtra("dataAdmin");
        Toast.makeText(this, admin.getTaikhoan(), Toast.LENGTH_SHORT).show();

        AnhXa();
        id = admin.getId();
        edtTaiKhoan.setText(admin.getTaikhoan());
        edtMatKhau.setText(admin.getMatkhau());
        edtSDT.setText(admin.getSDT() + "");
        edtTenDaiLi.setText(admin.getTenDaiLi());
        edtSoLanSD.setText(admin.getSoLanSuDung() + "");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtTaiKhoan.getText().toString().trim();
                String matkhau = edtMatKhau.getText().toString().trim();
                String SDT = edtSDT.getText().toString().trim();
                String tendaili = edtTenDaiLi.getText().toString().trim();
                String solansd = edtSoLanSD.getText().toString().trim();

                if (taikhoan.matches("") || matkhau.matches("") || SDT.equals("") || tendaili.length() == 0 || solansd.length() == 0){
                    Toast.makeText(UpdateADActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    UpdateAdmin(urlUpdate);

                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void UpdateAdmin(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(UpdateADActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateADActivity.this, AdminActivity.class));
                        }else {
                            Toast.makeText(UpdateADActivity.this, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateADActivity.this, "Xảy ra lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();

                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idAD", String.valueOf(id));
                params.put("taikhoanAD", edtTaiKhoan.getText().toString().trim());
                params.put("matkhauAD", edtMatKhau.getText().toString().trim());
                params.put("SDTAD", edtSDT.getText().toString().trim());
                params.put("TenDaiLiAD", edtTenDaiLi.getText().toString().trim());
                params.put("SoLanSuDungAD", edtSoLanSD.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtTaiKhoan = findViewById(R.id.edt_up_tk);
        edtMatKhau = findViewById(R.id.edt_up_mk);
        edtSDT = findViewById(R.id.edt_up_sdt);
        edtTenDaiLi = findViewById(R.id.edt_up_tendaili);
        edtSoLanSD = findViewById(R.id.edt_up_slsd);
        btnUpdate = findViewById(R.id.btn_update);
        btnHuy = findViewById(R.id.btn_huy_edit);
    }
}
