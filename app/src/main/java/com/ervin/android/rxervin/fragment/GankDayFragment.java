package com.ervin.android.rxervin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ervin.android.rxervin.GankInfoActivity;
import com.ervin.android.rxervin.R;
import com.ervin.android.rxervin.adapter.GankDayAdapter;
import com.ervin.android.rxervin.adapter.OnItemClickListener;
import com.ervin.android.rxervin.api.ApiRequest;
import com.ervin.android.rxervin.entity.AndroidGankEntity;
import com.ervin.android.rxervin.entity.Meizhis;
import com.ervin.android.rxervin.utils.TimeHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankDayFragment extends Fragment {

    @BindView(R.id.rv_gank)
    RecyclerView rvGank;

    private GankDayAdapter mAdapter;

    private List<Meizhis> mAndroidData;
    private List<Meizhis> mIOSData;

    public GankDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gankday, container, false);
        ButterKnife.bind(this,view);
        initView();
        initData();
        return view;
    }

    private void initView(){
        mAdapter = new GankDayAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvGank.setLayoutManager(manager);
        rvGank.setAdapter(mAdapter);

        mAdapter.setOnClickedListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(position == 0 || position == mAndroidData.size()) return;

                String url;
                if(position < mAndroidData.size()){
                    url = mAndroidData.get(position).url;
                }else{
                    url = mIOSData.get(position - mAndroidData.size()).url;
                }
                Intent intent = new Intent(getActivity(), GankInfoActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }

    private void initData(){
        String today = TimeHelper.getToday();
        ApiRequest.getMeizhiApi().getAndroidDayGank(today.substring(0,4),today.substring(5,7),today.substring(8,10))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AndroidGankEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AndroidGankEntity androidGankEntity) {
                        if(androidGankEntity != null){
                            /*List<Meizhis> data = new ArrayList<>();
                            data.addAll(androidGankEntity.results.Android);
                            data.addAll(androidGankEntity.results.iOS);*/
                            mAndroidData = androidGankEntity.results.Android;
                            mIOSData = androidGankEntity.results.iOS;

                            mAdapter.setData(androidGankEntity.results.Android,androidGankEntity.results.iOS);
                        }
                    }
                });
    }
}
