package com.example.icardemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdminAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Admin> adminList;

    public AdminAdapter(Context context, int layout, List<Admin> adminList) {
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

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTaikhoan = (TextView) view.findViewById(R.id.tv_taikhoan);
            holder.txtSdt = (TextView) view.findViewById(R.id.tv_sdt);
            holder.txtTendaili = (TextView) view.findViewById(R.id.tv_tendaili);
            holder.imgDelete = (ImageView) view.findViewById(R.id.img_del);
            holder.imgEdit = (ImageView) view.findViewById(R.id.img_edit);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Admin admin = adminList.get(i);

        holder.txtTaikhoan.setText(admin.getTaikhoan());
        holder.txtSdt.setText("Số điện thoại: " + admin.getSDT());
        holder.txtTendaili.setText(admin.getTenDaiLi());
        return view;
    }
}
