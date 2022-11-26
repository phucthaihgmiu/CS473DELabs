package com.example.mdpassignment6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mdpassignment6.adapter.MyPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragments
        val myPageAdapter = MyPageAdapter(this)
        // Set the Adapter to your Viewpager UI
        vPage.adapter = myPageAdapter
        // Will align the space according to the Screen size to equally spread
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        /* Setting up Tab Layout with the ViewPageg2 is handled by the TabLayoutMediator class
       * by passing your tablayout id and viewpager id*/
        TabLayoutMediator(tabLayout,vPage){tab,position->
            when(position){
                0->{
                    tab.text="Home"
                    tab.setIcon(R.drawable.ic_baseline_home_24)
                }
                1->{
                    tab.text="Me"
                    tab.setIcon(R.drawable.ic_baseline_person_24)
                }
                2->{
                    tab.text="Work"
                    tab.setIcon(R.drawable.ic_baseline_work_24)
                }
                3->{
                    tab.text = "Contact"
                    tab.setIcon(R.drawable.ic_baseline_phone_24)
                }
            }
        }.attach()

    }
}