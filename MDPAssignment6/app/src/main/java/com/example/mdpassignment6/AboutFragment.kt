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
import com.example.mdpassignment6.data.Certification
import com.example.mdpassignment6.data.Education
import com.example.mdpassignment6.util.RestApi
import com.example.mdpassignment6.util.RestApiService
import kotlinx.android.synthetic.main.fragment_about.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AboutFragment : Fragment(R.layout.fragment_about) {

    private var educationList = ArrayList<Education>();
    private lateinit var educationAdapter: MyEducationAdapter;

    private var certicationList = ArrayList<Certification>();
    private lateinit var certificationAdapter: MyCertificationAdapter;

    private var username = "";
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val spf = context?.getSharedPreferences("myspf", Context.MODE_PRIVATE);
        username = spf?.getString("username", "")!!;
        getAccounts();

////       //Education degrees
    //        educationList.add(Education(R.drawable.miu, "Maharishi International University", "Master of Computer Science"));
//        educationList.add(Education(R.drawable.khtn, "University of Science", "Bachelor of Computer Science"));
//        educationList.add(Education(R.drawable.khtn, "Nguyen Thuong Hien High School", "Diploma"));
//
//        fa_rv_education.layoutManager = LinearLayoutManager(context);
//        educationAdapter = MyEducationAdapter(requireContext(), educationList);
//        fa_rv_education.adapter = educationAdapter;
//
//
//        //Certifications
//        certicationList.add(Certification(R.drawable.loma, "LOMA 280 — Principles of Insurance"));
//        certicationList.add(Certification(R.drawable.loma, "LOMA 290 – Insurance Company Operations"));
//        certicationList.add(Certification(R.drawable.theinstitutes, "INS 21: Property and Liability Insurance Principles"));
//
//        fa_rv_certification.layoutManager = LinearLayoutManager(context);
//        certificationAdapter = MyCertificationAdapter(requireContext(), certicationList);
//        fa_rv_certification.adapter = certificationAdapter;


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    private fun getAccounts(){
        val apiService = RestApiService()
        apiService.getAccounts (){
            if(it != null){
                val accounts = it.filter { account -> account.isActive };
                for(account in accounts){
                    if(!username.isNullOrEmpty() && account.email.equals(username)){
                        //About Me
                        fa_about_me_detail.text = account.about_me;

                        //Education degrees
                        educationList = account.education as ArrayList<Education>;
                        fa_rv_education.layoutManager = LinearLayoutManager(context);
                        educationAdapter = MyEducationAdapter(requireContext(), educationList);
                        educationAdapter.notifyDataSetChanged();

                        //Certifications
                        certicationList = account.certifications as ArrayList<Certification>;
                        fa_rv_certification.layoutManager = LinearLayoutManager(context);
                        certificationAdapter = MyCertificationAdapter(requireContext(), certicationList);
                        certificationAdapter.notifyDataSetChanged();
                    }
                }
                fa_rv_education.adapter = educationAdapter;
                fa_rv_certification.adapter = certificationAdapter;
            }else{
                Log.e("RETROFIT_ERROR", "FAILURE TO GET ACCOUNT")
            }
        }
    }
}