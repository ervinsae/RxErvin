package com.ervin.android.rxervin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ervin.android.rxervin.R;
import com.ervin.android.rxervin.entity.BaseEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ervin on 2016/8/10.
 */
public class ZhuangbiAdapter extends RecyclerView.Adapter<ZhuangbiAdapter.ViewHolder> {

    private List<BaseEntity> data;
    private Context mContext;
    public ZhuangbiAdapter(Context context){
        mContext = context;
    }
    @Override
    public ZhuangbiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_zhuangbi_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ZhuangbiAdapter.ViewHolder holder, int position) {
        Picasso.with(mContext).load(data.get(position).image_url).into(holder.imageIv);
        holder.descriptionTv.setText(data.get(position).description);
        //holder.fileSize.setText(data.get(position).file_size);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<BaseEntity> data){
        this.data = data;
        Log.d("ervin","data size is" + data.size());
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageIv)
        ImageView imageIv;
        @BindView(R.id.descriptionTv)
        TextView descriptionTv;
        @BindView(R.id.fileSize)
        TextView fileSize;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
