package com.example.icardemo.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icardemo.Main.Admin;
import com.example.icardemo.Main.AdminActivity;
import com.example.icardemo.R;
import com.example.icardemo.Main.UpdateADActivity;

import java.util.List;

public class AdminAdapter extends BaseAdapter {
    private AdminActivity context;
    private int layout;
    private List<Admin> adminList;

    public AdminAdapter(AdminActivity context, int layout, List<Admin> adminList) {
        this.context = context;
        this.layout = layout;
        this.adminList = adminList;
    }

    @Override
    public int getCount() {
        return adminList.size();
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
        TextView txtTaikhoan, txtSdt, txtTendaili;
        ImageView imgDelete, imgEdit;
        EditText edtTentaikhoan, edtSolancon;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTaikhoan =  view.findViewById(R.id.tv_taikhoan);
            holder.txtSdt =  view.findViewById(R.id.tv_sdt);
            holder.txtTendaili =  view.findViewById(R.id.tv_tendaili);
            holder.imgDelete =  view.findViewById(R.id.img_del);
            holder.imgEdit =  view.findViewById(R.id.img_edit);



            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        final Admin admin = adminList.get(i);

        holder.txtTaikhoan.setText(admin.getTaikhoan());
        holder.txtSdt.setText("Số điện thoại: " + admin.getSDT());
        holder.txtTendaili.setText(admin.getTenDaiLi());

        //bắt sự kiện xóa và sửa
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateADActivity.class);
                intent.putExtra("dataAdmin", admin);
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(admin.getTaikhoan(), admin.getId());

            }
        });


        return view;
    }
    private void XacNhanXoa(String ten, final int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(context);
        dialogXoa.setMessage("Bạn có muốn xóa tài khoản " + ten + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteAdmin(id);
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }
}
