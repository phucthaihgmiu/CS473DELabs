package com.example.mdpassignment6

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.adapter.MySkillAdapter
import com.example.mdpassignment6.data.Account
import com.example.mdpassignment6.data.Certification
import com.example.mdpassignment6.data.Education
import com.example.mdpassignment6.data.Skill
import com.example.mdpassignment6.util.APIService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_home.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parseAccountJSON();

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

    private fun parseAccountJSON(){
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)
        service.getAccounts().enqueue(object : Callback<List<Account>> {
            override fun onResponse(
                call: Call<List<Account>>,
                response: Response<List<Account>>
            ) {
//                val gson = GsonBuilder().setPrettyPrinting().create()
//                val prettyJson = gson.toJson(response.body())
//                Log.d("Pretty Printed JSON :", prettyJson)
                val accounts = response.body()!!;
                if(accounts != null && accounts.size > 0){
                    //About Me
                    fa_about_me_detail.text = accounts[0].about_me;

                    //Education degrees
                    educationList = accounts[0].education as ArrayList<Education>;
                    fa_rv_education.layoutManager = LinearLayoutManager(context);
                    educationAdapter = MyEducationAdapter(requireContext(), educationList);
                    educationAdapter.notifyDataSetChanged();


                    //Certifications
                    certicationList = accounts[0].certifications as ArrayList<Certification>;
                    fa_rv_certification.layoutManager = LinearLayoutManager(context);
                    certificationAdapter = MyCertificationAdapter(requireContext(), certicationList);
                    certificationAdapter.notifyDataSetChanged();
                }
                fa_rv_education.adapter = educationAdapter;
                fa_rv_certification.adapter = certificationAdapter;
            }

            override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                Log.e("RETROFIT_ERROR", "FAILURE TO LOAD JSON")
            }

        });
    }
}