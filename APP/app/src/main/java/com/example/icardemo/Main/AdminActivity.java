package com.example.icardemo.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.icardemo.Adapter.AdminAdapter;
import com.example.icardemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {
    String urlGetData ="http://192.168.0.111:81/icarserver/admin.php";
    String urlDelete = "http://192.168.0.111:81/icarserver/delete.php";
    ListView lvAdmin;
    ArrayList<Admin> arrayAdmin;
    AdminAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lvAdmin =  findViewById(R.id.lv_list);
        arrayAdmin = new ArrayList<>();

        adapter = new AdminAdapter(this, R.layout.list_admin, arrayAdmin);
        lvAdmin.setAdapter(adapter);



        GetData(urlGetData);
    }
    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                arrayAdmin.clear();
                for (int i =0; i < response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        arrayAdmin.add(new Admin(
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
                        Toast.makeText(AdminActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }


    public void DeleteAdmin(final int idad){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")){
                            Toast.makeText(AdminActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            GetData(urlGetData);
                        }else {
                            Toast.makeText(AdminActivity.this, "Lỗi xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AdminActivity.this, "Xảy ra lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idAdmin", String.valueOf(idad));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.it_menu){
            startActivity(new Intent(AdminActivity.this, AddAdminActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }
}
