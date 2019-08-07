package com.example.icardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class AddAdminActivity extends AppCompatActivity {
    EditText edtTaikhoan, edtMatkhau, edtSDT, edtTendaili, edtSolansd;
    Button btnThem, btnHuy;
    String urlInsert = "http://192.168.0.114:81/icarserver/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        AnhXa();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtTaikhoan.getText().toString().trim();
                String matkhau = edtMatkhau.getText().toString().trim();
                String sdt = edtSDT.getText().toString().trim();
                String tendaili = edtTendaili.getText().toString().trim();
                String solansd = edtSolansd.getText().toString().trim();
                if (taikhoan.isEmpty() || matkhau.isEmpty() || sdt.isEmpty() || tendaili.isEmpty() || solansd.isEmpty()){
                    Toast.makeText(AddAdminActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    AddMember(urlInsert);
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

    private void AddMember(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(AddAdminActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddAdminActivity.this, AdminActivity.class));

                        }else {
                            Toast.makeText(AddAdminActivity.this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddAdminActivity.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("AAA", "Lỗi!\n" + error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("taikhoanad", edtTaikhoan.getText().toString().trim());
                params.put("matkhauad", edtMatkhau.getText().toString().trim());
                params.put("sodienthoai", edtSDT.getText().toString().trim());
                params.put("tendailiad", edtTendaili.getText().toString().trim());
                params.put("solansudungad", edtSolansd.getText().toString().trim());


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void AnhXa() {
        btnThem = (Button) findViewById(R.id.btn_them);
        btnHuy = (Button) findViewById(R.id.btn_huy);
        edtTaikhoan = (EditText) findViewById(R.id.edt_add_tk);
        edtMatkhau = (EditText) findViewById(R.id.edt_add_mk);
        edtSDT = (EditText) findViewById(R.id.edt_add_sdt);
        edtTendaili = (EditText) findViewById(R.id.edt_add_tendaili);
        edtSolansd = (EditText) findViewById(R.id.edt_add_slsd);


    }
}
