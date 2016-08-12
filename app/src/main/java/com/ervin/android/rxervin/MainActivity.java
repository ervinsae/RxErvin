package com.ervin.android.rxervin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ervin.android.rxervin.fragment.ElementFragment;
import com.ervin.android.rxervin.fragment.ThreeFragment;
import com.ervin.android.rxervin.fragment.TwoFragmen;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_fragment)
    TabLayout mTab;
    @BindView(R.id.vp_fragment)
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
                        return new TwoFragmen();
                    case 2:
                        return new ThreeFragment();
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
                        return "TwoFragment";
                    case 2:
                        return "ThreeFragment";
                    default:
                        return "ElementFragment";
                }
            }
        });

        mTab.setupWithViewPager(mPager);
    }
}
