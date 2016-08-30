package com.ervin.android.rxervin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ervin.android.rxervin.GankInfoActivity;
import com.ervin.android.rxervin.R;
import com.ervin.android.rxervin.SchedulerHelper;
import com.ervin.android.rxervin.adapter.AndroidAdapter;
import com.ervin.android.rxervin.adapter.OnItemClickListener;
import com.ervin.android.rxervin.api.ApiRequest;
import com.ervin.android.rxervin.entity.MeizhiEntity;
import com.ervin.android.rxervin.entity.Meizhis;
import com.ervin.android.rxervin.utils.RecycleViewDivider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rv_android)
    RecyclerView rvAndroid;
    @BindView(R.id.sr_android)
    SwipeRefreshLayout srRefresh;

    AndroidAdapter mAdapter;
    public List<Meizhis> data;

    private static int page = 1;
    public AndroidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        ButterKnife.bind(this,view);

        initView();
        return view;
    }


    /*@Override
    public void onResume() {
        super.onResume();
        initData(page);
    }*/

    private void initView(){
        mAdapter = new AndroidAdapter(getActivity());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rvAndroid.setLayoutManager(manager);
        rvAndroid.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        rvAndroid.setAdapter(mAdapter);

        mAdapter.setOnClickedListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                // TODO: 2016/8/12 跳转webview
                Intent intent = new Intent(getActivity(), GankInfoActivity.class);
                intent.putExtra("url",data.get(position).url);
                startActivity(intent);
            }
        });
        srRefresh.setOnRefreshListener(this);
        initData(page);
    }

    private void initData(int page){
        srRefresh.setRefreshing(true);
        ApiRequest.getMeizhiApi().getAndroidGank(10,page)
                .compose(SchedulerHelper.<MeizhiEntity>applySchedulers())
                .subscribe(new Subscriber<MeizhiEntity>() {
                    @Override
                    public void onCompleted() {
                        srRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(),e.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(MeizhiEntity meizhiEntity) {
                        if(meizhiEntity != null){
                            mAdapter.setData(meizhiEntity.results);
                            data = meizhiEntity.results;
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        page ++;
        initData(page);
    }
}
