package com.example.icardemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.icardemo.Main.Admin;
import com.example.icardemo.Main.MemberActivity;
import com.example.icardemo.R;
import com.example.icardemo.Main.SaveActivity;

import java.util.List;

public class MemberAdapter extends BaseAdapter {
    private MemberActivity context1;
    private int layout;
    private List<Admin> list;

    public MemberAdapter(MemberActivity context1, int layout, List<Admin> list){
        this.context1 = context1;
        this.layout = layout;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvTentaikhoan, tvSolancon;
        Button btnTinhma, btnLuu;
        EditText edtMamay, edtMakichhoat;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null){
            holder = new MemberAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvTentaikhoan =  view.findViewById(R.id.tv_taikhoan1);
            holder.tvSolancon =  view.findViewById(R.id.tv_solancon);
            holder.btnTinhma = view.findViewById(R.id.btn_tinhma);
            holder.btnLuu = view.findViewById(R.id.btn_lưu);
            holder.edtMamay = view.findViewById(R.id.edt_mamay);
            holder.edtMakichhoat = view.findViewById(R.id.edt_makichhoat);

            view.setTag(holder);
        }else {
            holder = (MemberAdapter.ViewHolder) view.getTag();
        }
        final Admin admin = list.get(i);
        holder.tvTentaikhoan.setText(admin.getTaikhoan());
        holder.tvSolancon.setText("Số lần còn "+ admin.getSoLanSuDung());
        //tính mã bằng mã máy * 2
        holder.btnTinhma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mamay = holder.edtMamay.getText().toString();
                int mamay1 = Integer.parseInt(mamay);
                int tong = mamay1 * 2;

                holder.edtMakichhoat.setText(String.valueOf(tong));

            }
        });
        // lưu tính mã vào database
        holder.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context1, SaveActivity.class);
                intent.putExtra("dataAdmin", admin);
                String mamay = holder.edtMamay.getText().toString();
                String makichhoat = holder.edtMakichhoat.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("mamay", mamay);
                bundle.putString("makichhoat", makichhoat);
                intent.putExtra("", bundle);
                context1.startActivity(intent);

            }
        });



        return view;
    }
}
