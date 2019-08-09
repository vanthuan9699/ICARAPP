package com.example.icardemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MemberActivity extends AppCompatActivity {
    String urlLayTT = "http://192.168.0.112:81/icarserver/laythongtin.php";
    String urlGetData ="http://192.168.0.112:81/icarserver/admin.php";
    Button btnTinhma;
    ArrayList<Admin> arrayMember;
    MemberAdapter adapter;
    ListView lvTT;
    EditText edtMamay, edtMakichhoat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        lvTT =  findViewById(R.id.lv_layTT);
        arrayMember = new ArrayList<>();
        adapter = new MemberAdapter(this, R.layout.list_ttnguoidung, arrayMember);
        lvTT.setAdapter(adapter);
        GetData(urlGetData);

        AnhXa();


    }
    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayMember.clear();
                for (int i =0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayMember.add(new Admin(
                                object.getInt("id"),
                                object.getString("Ten_TK"),
                                object.getString("MatKhau"),
                                object.getInt("SDT"),
                                object.getString("TenDaiLi"),
                                object.getInt("SoLan_SD")

                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MemberActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void AnhXa() {
        btnTinhma = findViewById(R.id.btn_tinhma);
        edtMamay = findViewById(R.id.edt_mamay);
        edtMakichhoat = findViewById(R.id.edt_makichhoat);



    }


}
