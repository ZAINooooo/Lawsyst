//package com.example.lawsyst;
//
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Matters_Tabs_Activity extends AppCompatActivity {
//
//    private Toolbar toolbar;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
//
//
//    private int[] tabIcons = {
//            R.drawable.ic_tab_favourite,
//            R.drawable.ic_tab_call,
//            R.drawable.ic_tab_contacts
//    };
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_simple_tabs);
//
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
//
//        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//        setupTabIcons();
//    }
//
//    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//
//        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
//        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
//        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
//        tabLayout.getTabAt(6).setIcon(tabIcons[6]);
//        tabLayout.getTabAt(7).setIcon(tabIcons[7]);
//        tabLayout.getTabAt(8).setIcon(tabIcons[8]);
//
//    }
//
//
//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new Matter_Tab(), "Matter");
//        adapter.addFragment(new Contacts_Tab(), "Contacts");
//        adapter.addFragment(new Communication_Tab(), "Communication");
//
//        adapter.addFragment(new Times_Tab(), "Times");
//        adapter.addFragment(new Bills_Tab(), "Bills/Disburses");
//        adapter.addFragment(new Invoices_Tab(), "Invoices");
//
//        adapter.addFragment(new Matter_Ledger_Tab(), "Matter Ledger");
//        adapter.addFragment(new Tasks_Tab(), "Tasks");
//        adapter.addFragment(new Notes_Tab(), "Notes");
//
//        viewPager.setAdapter(adapter);
//    }
//
//    class ViewPagerAdapter extends FragmentPagerAdapter {
//        private final List<Fragment> mFragmentList = new ArrayList<>();
//        private final List<String> mFragmentTitleList = new ArrayList<>();
//
//        public ViewPagerAdapter(FragmentManager manager) {
//            super(manager);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragmentList.size();
//        }
//
//        public void addFragment(Fragment fragment, String title) {
//            mFragmentList.add(fragment);
//            mFragmentTitleList.add(title);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mFragmentTitleList.get(position);
//        }
//    }
//}
