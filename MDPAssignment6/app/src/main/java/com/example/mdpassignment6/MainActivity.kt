package com.example.mdpassignment6

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.menu.MenuBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdpassignment6.adapter.MyPageAdapter
import com.example.mdpassignment6.data.Experience
import com.example.mdpassignment6.util.RestApiService
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_work.*

class MainActivity : AppCompatActivity() {

    private var username = "";

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

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu is MenuBuilder){
            menu.setOptionalIconsVisible(true);
        }

        menuInflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.om_signout -> {
                var intent = Intent(this, LoginActivity::class.java);
                startActivity(intent);
            }
            R.id.om_deleteAccount -> {
                val spf = this.getSharedPreferences("myspf", Context.MODE_PRIVATE);
                username = spf?.getString("username", "")!!;
                invalidateAccount()
            }
            else -> return false;
        }
        return true;
    }

    private fun invalidateAccount(){
        val apiService = RestApiService()
        apiService.getAccounts (){
            if(it != null){
                val accounts = it.filter { account -> account.isActive };
                for(account in accounts){
                    if(!username.isNullOrEmpty() && account.email.equals(username)){
                        account.isActive = false;
                        apiService.updateAccount(account) {
                            if (it != null) {
                                Log.e("RETROFIT_SUCCESS", "SUCCESS TO UPDATE ACCOUNT")
                                var intent = Intent(this, LoginActivity::class.java);
                                startActivity(intent);
                            } else {
                                Log.e("RETROFIT_ERROR", "FAILURE TO UPDATE ACCOUNT")
                            }
                        }
                    }
                }
            }else{
                Log.e("RETROFIT_ERROR", "FAILURE TO GET ACCOUNT")
            }
        }
    }
}