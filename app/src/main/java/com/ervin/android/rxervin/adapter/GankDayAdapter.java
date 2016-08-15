package com.ervin.android.rxervin.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ervin.android.rxervin.R;
import com.ervin.android.rxervin.entity.Meizhis;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ervin on 2016/8/15.
 */
public class GankDayAdapter extends RecyclerView.Adapter<GankDayAdapter.ViewHolder> {
    private int TYPE_TITLE = 1;
    private int TYPE_CONTENT = 2;
    private int type;

    private List<Meizhis> mAndroidData;
    private List<Meizhis> mIOSData;
    private Context mContext;

    private static OnItemClickListener mListener;

    public GankDayAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public GankDayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_CONTENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_android_item, parent, false);
            //return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gank_item, parent, false);
            //return new ViewHolderTitle(view);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GankDayAdapter.ViewHolder holder, int position) {
        if(position == 0){
            holder.tvTitle.setText("Android:");
            return;
        }
        if(position == mAndroidData.size()){
            holder.tvTitle.setText("iOS:");
            return;
        }

        if(position < mAndroidData.size()){
            Meizhis meizhis = mAndroidData.get(position);
            holder.tvTitle.setText(meizhis.desc);
            holder.tvTime.setText(meizhis.publishedAt.toString());
        }else{
            Meizhis meizhis = mIOSData.get(position - mAndroidData.size());
            holder.tvTitle.setText(meizhis.desc);
            holder.tvTime.setText(meizhis.publishedAt.toString());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 || position == mAndroidData.size()){
            return TYPE_TITLE;
        }else{
            return TYPE_CONTENT;
        }
    }

    public void setData(List<Meizhis> android,List<Meizhis> ios){
        this.mIOSData = ios;
        this.mAndroidData = android;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mAndroidData == null && mIOSData == null){
            return 0;
        }else{
            if(mAndroidData == null){
                return mIOSData.size();
            }else if(mIOSData == null){
                return mAndroidData.size();
            }else{
                return mAndroidData.size() + mIOSData.size();
            }
        }
    }

    public void setOnClickedListener(OnItemClickListener listener){
        mListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_title)
        TextView tvTitle;

        @Nullable
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view,getAdapterPosition());
        }
    }

    /*static class ViewHolderTitle extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title)
        TextView tvTitle;
        public ViewHolderTitle(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }*/
}
