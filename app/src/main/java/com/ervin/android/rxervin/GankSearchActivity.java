package com.ervin.android.rxervin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ervin on 2017/3/13.
 */

@SuppressLint("ParcelCreator")
public class GankSearchActivity extends BaseActivity implements SearchSuggestion{

    @BindView(R.id.floating_search_view)
    FloatingSearchView floatingSearchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_search);
        ButterKnife.bind(this);

        setToolbar();
        initView();
    }

    @Override
    protected void setToolbar() {
        toolbarTitle.setText("搜索");
        toolbarRight.setVisibility(View.GONE);
    }

    private void initView(){
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                Toast.makeText(GankSearchActivity.this,"onSearchTextChanged",Toast.LENGTH_SHORT).show();
            }
        });

        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {

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
}
