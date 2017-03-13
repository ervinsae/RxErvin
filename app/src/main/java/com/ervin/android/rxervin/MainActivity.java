package com.ervin.android.rxervin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.ervin.android.rxervin.fragment.AndroidFragment;
import com.ervin.android.rxervin.fragment.ElementFragment;
import com.ervin.android.rxervin.fragment.GankDayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_fragment)
    TabLayout mTab;
    @BindView(R.id.vp_fragment)
    ViewPager mPager;

    AndroidFragment androidFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initView(){
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new ElementFragment();
                    case 1:
                        return androidFragment = new AndroidFragment();
                    case 2:
                        return new GankDayFragment();
                    default:
                        return new ElementFragment();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "ElementFragment";
                    case 1:
                        return "AndroidFragment";
                    case 2:
                        return "GankDayFragment";
                    default:
                        return "ElementFragment";
                }
            }
        });

        mTab.setupWithViewPager(mPager);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("mainActivity",tab.getPosition()+"选中");
                switch (tab.getPosition()){
                    case 0:
                        toolbarRight.setText("Search");
                        break;
                    case 1:
                        toolbarRight.setText("回到今天");
                        break;
                    case 2:
                        toolbarRight.setText("Search");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void setToolbar() {
        toolbarTitle.setText("主页");
        toolbarLeft.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.toolbar_right) void onToolbarClicked(){
        if(mTab.getSelectedTabPosition() == 1){
            androidFragment.backToToday();
        }
    }
}
