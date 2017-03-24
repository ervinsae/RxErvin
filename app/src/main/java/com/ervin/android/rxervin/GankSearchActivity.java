package com.ervin.android.rxervin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.ervin.android.rxervin.api.ApiRequest;
import com.ervin.android.rxervin.entity.GankEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by Ervin on 2017/3/13.
 */

@SuppressLint("ParcelCreator")
public class GankSearchActivity extends BaseActivity implements SearchSuggestion {

    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;

    @BindView(R.id.wv_info)
    WebView webView;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.parent_view)
    RelativeLayout parentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_gank_search);
        ButterKnife.bind(this);

        //setToolbar();
        initView();
    }

    @Override
    protected void setToolbar() {
        toolbarTitle.setText("搜索");
        toolbarRight.setVisibility(View.GONE);
    }

    private void initView() {
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                //Toast.makeText(GankSearchActivity.this,"onSearchTextChanged",Toast.LENGTH_SHORT).show();
            }
        });

        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                searchData(currentQuery);
            }
        });
    }

    @Override
    public String getBody() {
        return null;
    }

    @Override
    public int describeContents() {
        return 3;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    private void searchData(String search) {
        floatingSearchView.showProgress();
        ApiRequest.getMeizhiApi().getAllGank(search, 1, 1)
                .compose(SchedulerHelper.<GankEntity>applySchedulers())
                .subscribe(new Subscriber<GankEntity>() {
                    @Override
                    public void onCompleted() {
                        floatingSearchView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        floatingSearchView.hideProgress();
                    }

                    @Override
                    public void onNext(GankEntity gankEntity) {
                        //webView.loadData(gankEntity.results.get(0).readability,"text/html","utf-8");
                        //Html.fromHtml(gankEntity.results.get(0));
                        webView.loadUrl(gankEntity.results.get(0).url);
                    }
                });
    }
}
