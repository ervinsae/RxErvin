package com.ervin.android.rxervin.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ervin.android.rxervin.BaseFragment;
import com.ervin.android.rxervin.MeizhiImageActivity;
import com.ervin.android.rxervin.OnItemClickedListener;
import com.ervin.android.rxervin.R;
import com.ervin.android.rxervin.SchedulerHelper;
import com.ervin.android.rxervin.adapter.ZhuangbiAdapter;
import com.ervin.android.rxervin.api.ApiRequest;
import com.ervin.android.rxervin.entity.BaseEntity;
import com.ervin.android.rxervin.entity.MeizhiEntity;
import com.ervin.android.rxervin.entity.Meizhis;
import com.ervin.android.rxervin.entity.ZhuangbiEntity;
import com.ervin.android.rxervin.utils.ScrollHelper;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.rv_zhuangbi)
    RecyclerView rvZhuangbi;
    @BindView(R.id.sr_refresh)
    SwipeRefreshLayout mRefresh;
    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.sv_search)
    SearchView svSearch;

    ZhuangbiAdapter mAdapter;
    List<BaseEntity> mData;
    private static int index = 1;
    private static String searchText = "110";

    public ElementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_element, container, false);
        ButterKnife.bind(this,view);
        initView();

        mRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        },200);
        return view;
    }

    @Override
    protected void setToolbar() {

    }

    private void initView(){
        final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new ZhuangbiAdapter(getActivity());

        rvZhuangbi.setLayoutManager(manager);
        rvZhuangbi.setAdapter(mAdapter);

        mRefresh.setOnRefreshListener(this);
        //etSearch.setFocusable(false);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    // the user is done typing.
                    Toast.makeText(getActivity(),"输入完成了……",Toast.LENGTH_SHORT).show();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

                    searchText = textView.getText().toString();
                    initData(searchText,index);
                    return true;
                }
                return false;
            }
        });

        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchText = query;
                initData(searchText,index);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        ScrollHelper.init(rvZhuangbi, manager, new ScrollHelper.OnScrollStateChangedListener() {
            @Override
            public void onScrollToBottom() {
                Logger.d("拉到最下面了。。。");
                index++;
                ApiRequest.getMeizhiApi().getMeizhiData(10,index)
                        .compose(SchedulerHelper.<MeizhiEntity>applySchedulers())
                        .subscribe(new Subscriber<MeizhiEntity>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Logger.e("ElementFragment","scroll",e);
                            }

                            @Override
                            public void onNext(MeizhiEntity meizhiEntity) {
                                List<BaseEntity> baseList = new ArrayList<>();
                                List<Meizhis> meizhiList = meizhiEntity.results;

                                for(Meizhis meizhi : meizhiList){
                                    BaseEntity baseItem = new BaseEntity();
                                    baseItem.description = meizhi.desc;
                                    baseItem.image_url = meizhi.url;

                                    baseList.add(baseItem);
                                }

                                mData.addAll(baseList);

                                mAdapter.setData(mData);
                            }
                        });
            }
        });

        mAdapter.setOnclickListener(new OnItemClickedListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<BaseEntity> data = mAdapter.getData();
                Intent intent = new Intent(getContext(), MeizhiImageActivity.class);
                //intent.putExtra("url",data.get(position).image_url);
                intent.putExtra("data",data.get(position));
                startActivity(intent);

            }
        });
    }

    private void initData(String search,int page){
        /*ApiRequest.getZhuangbiApi().search("110")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ZhuangbiEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<ZhuangbiEntity> zhuangbiApis) {
                        if(zhuangbiApis!=null){

                        }
                    }
                });

        ApiRequest.getMeizhiApi().getMeizhiData(10,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeizhiEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MeizhiEntity meizhiEntity) {

                    }
                });
*/
        mRefresh.setRefreshing(true);
        subscription = Observable.zip(ApiRequest.getZhuangbiApi().search(search),
                ApiRequest.getMeizhiApi().getMeizhiData(10, page),
                ApiRequest.getMeizhiApi().getAndroidGank(10, page),
                new Func3<List<ZhuangbiEntity>, MeizhiEntity , MeizhiEntity ,List<BaseEntity>>() {
                    @Override
                    public List<BaseEntity> call(List<ZhuangbiEntity> zhuangbiEntities, MeizhiEntity meizhiEntity,MeizhiEntity meizhiEntity2) {
                        List<BaseEntity> baseList = new ArrayList<>();
                        List<Meizhis> meizhiList = meizhiEntity.results;
                        List<Meizhis> meizhiList2 = meizhiEntity2.results;
                        for(ZhuangbiEntity zhuangbi : zhuangbiEntities){
                            baseList.add(zhuangbi);
                        }
                        for(int i = 0 ;i < Math.min(meizhiList.size(),meizhiList2.size());i++){
                            BaseEntity baseItem = new BaseEntity();

                            baseItem.description = meizhiList.get(i).desc + " " + meizhiList2.get(i).desc;
                            baseItem.image_url = meizhiList.get(i).url;

                            baseList.add(baseItem);
                        }
                        return baseList;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BaseEntity>>() {
                    @Override
                    public void onCompleted() {
                        mRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Element","error",e);
                        mRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onNext(List<BaseEntity> baseEntities) {
                        if(baseEntities!=null){
                            mData = baseEntities;
                            mAdapter.setData(baseEntities);
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        index = 1;
        initData(searchText,index);
    }

}
