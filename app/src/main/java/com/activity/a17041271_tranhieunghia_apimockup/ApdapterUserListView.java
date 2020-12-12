package com.activity.a17041271_tranhieunghia_apimockup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ApdapterUserListView extends BaseAdapter {
    Context ctx;
    int layout_item;
    ArrayList<User> arrayList;

    public ApdapterUserListView(Context ctx, int layout_item, ArrayList<User> arrayList) {
        this.ctx = ctx;
        this.layout_item = layout_item;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        User user= arrayList.get(position);

        if(convertView==null) {

            convertView = LayoutInflater.from(ctx).inflate(layout_item, parent, false);
            viewHolder=new ViewHolder();

            viewHolder.tvFristName = (TextView) convertView.findViewById(R.id.tvItemFristName);
            viewHolder.tvLastName = (TextView) convertView.findViewById(R.id.tvItemLastName);
            viewHolder.tvGender = (TextView) convertView.findViewById(R.id.tvItemGender);
            viewHolder.tvSalary = (TextView) convertView.findViewById(R.id.tvItemSalary);


            convertView.setTag(viewHolder);


        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        viewHolder.tvFristName.setText(user.getFIRSTNAME());
        viewHolder.tvLastName.setText(user.getLASTNAME());
        viewHolder.tvGender.setText(user.getGENDER());
        viewHolder.tvSalary.setText(user.getSALARY());

        return convertView;
    }
    public class ViewHolder{
        private TextView tvFristName,tvLastName,tvGender,tvSalary;
    }
}
