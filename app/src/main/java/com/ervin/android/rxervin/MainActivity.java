package com.ervin.android.rxervin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ervin.android.rxervin.fragment.ElementFragment;
import com.ervin.android.rxervin.fragment.GankDayFragment;
import com.ervin.android.rxervin.fragment.AndroidFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_fragment)
    TabLayout mTab;
    @BindView(R.id.vp_fragment)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setToolbar();
        initView();
    }

    private void initView(){
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new ElementFragment();
                    case 1:
                        return new AndroidFragment();
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
    }

    @Override
    protected void setToolbar() {
        toolbarTitle.setText("主页");
        toolbarLeft.setVisibility(View.INVISIBLE);
    }
}
