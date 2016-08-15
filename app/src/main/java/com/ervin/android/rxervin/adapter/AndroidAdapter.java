package com.ervin.android.rxervin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ervin.android.rxervin.R;
import com.ervin.android.rxervin.entity.Meizhis;
import com.ervin.android.rxervin.utils.TimeHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ervin on 2016/8/12.
 */
public class AndroidAdapter extends RecyclerView.Adapter<AndroidAdapter.ViewHolder> {

    private List<Meizhis> mData;
    public static OnItemClickListener mListener;
    private Context mContext;

    public AndroidAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public AndroidAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_android_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AndroidAdapter.ViewHolder holder, int position) {
        Meizhis meizhis = mData.get(position);
        holder.tvTitle.setText(meizhis.desc);
        holder.tvTime.setText(TimeHelper.millisToYYYYMMddHHmm(meizhis.publishedAt.getTime()));
        //holder.tvTime.setText(meizhis.publishedAt.toString());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<Meizhis> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void setOnclickedListener(OnItemClickListener listener){
        mListener = listener;
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mListener != null) {
                mListener.onClick(view, getAdapterPosition());
            }
        }
    }
}
