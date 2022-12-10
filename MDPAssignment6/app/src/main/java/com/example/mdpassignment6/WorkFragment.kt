package com.example.mdpassignment6

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdpassignment6.data.Account
import com.example.mdpassignment6.data.Experience
import com.example.mdpassignment6.util.RestApi
import com.example.mdpassignment6.util.RestApiService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_work.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WorkFragment : Fragment() {

    private var workExperienceList = ArrayList<Experience>();
    private lateinit var workExperienceAdapter: MyExperienceAdapter;

    private var username = "";

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val spf = context?.getSharedPreferences("myspf", Context.MODE_PRIVATE);
        username = spf?.getString("username", "")!!;
        getAccounts();

////       //Education degrees
//        workExperienceList.add(Experience(R.drawable.prime, "PRIME CONSULTING SERVICES LTD", "Application Consultant", "2018 - 2022", "Singapore, Singapore", "Consulting service for information technology department at Prudential Assurance Singapore"));
//        workExperienceList.add(Experience(R.drawable.aia, "AIA", "Application Consultant", "2017 - 2018", "Singapore, Singapore", "A multinational insurance and investment service corporation"));
//        workExperienceList.add(Experience(R.drawable.u3, "U3 Infotech", "Senior Application Analyst", "2016 - 2017", "Singapore, Singapore", "Staffing companies for information technology department at AVIVA ASIA PTE LTD Singapore"));
//        workExperienceList.add(Experience(R.drawable.gma, "GMA Tech Consulting Sdn Bhd", "Technical Analyst", "2015 - 2016", "Kuala Lumpur, Malaysia", "Staffing company for information technology department at GREAT EASTERN LIFE"));
//
//
//        fw_rv_experience.layoutManager = LinearLayoutManager(context);
//        workExperienceAdapter = MyExperienceAdapter(requireContext(), workExperienceList);
//        fw_rv_experience.adapter = workExperienceAdapter;

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work, container, false)
    }

    private fun getAccounts(){
        val apiService = RestApiService()
        apiService.getAccounts (){
            if(it != null){
                val accounts = it.filter { account -> account.isActive };
                for(account in accounts){
                    if(!username.isNullOrEmpty() && account.email.equals(username)){
                        //Experiences
                        workExperienceList = account.experiences as ArrayList<Experience>;
                        fw_rv_experience.layoutManager = LinearLayoutManager(context);
                        workExperienceAdapter = MyExperienceAdapter(requireContext(), workExperienceList);
                    }
                }
                fw_rv_experience.adapter = workExperienceAdapter;
            }else{
                Log.e("RETROFIT_ERROR", "FAILURE TO GET ACCOUNT")
            }
        }
    }
}