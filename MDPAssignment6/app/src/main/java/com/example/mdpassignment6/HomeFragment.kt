package com.example.mdpassignment6

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.example.mdpassignment6.adapter.MySkillAdapter
import com.example.mdpassignment6.data.*
import com.example.mdpassignment6.util.APIService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.component_education_brief.view.*
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var skillList = ArrayList<Skill>();
    private lateinit var skillAdapter: MySkillAdapter;

    
    private var username = "";
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        val spf = context?.getSharedPreferences("myspf", Context.MODE_PRIVATE);
        username = spf?.getString("username", "")!!;
        parseAccountJSON();

//        skillList.add(Skill("Languages", arrayListOf("COBOL", "JCL", "CICS", "CL", "SQL", "Java")));
//        skillList.add(Skill("Web", arrayListOf("HTML", "CSS", "JavaScript", "Node.JS", "Angular", "JSON", "XML")));
//        skillList.add(Skill("Web Services", arrayListOf("REST API", "SOAP")));
//
//        fh_rv_skill.layoutManager = LinearLayoutManager(context);
//        skillAdapter = MySkillAdapter(requireContext(), skillList);
//        fh_rv_skill.adapter = skillAdapter;


    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun parseAccountJSON(){
        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(APIService::class.java)
        service.getAccounts().enqueue(object : Callback<List<Account>>{
            override fun onResponse(
                call: Call<List<Account>>,
                response: Response<List<Account>>
            ) {
//                val gson = GsonBuilder().setPrettyPrinting().create()
//                val prettyJson = gson.toJson(response.body())
//                Log.d("Pretty Printed JSON :", prettyJson)
                val accounts = response.body()!!;
                if(accounts != null){
                    for(account in accounts){
                        if(!username.isNullOrEmpty() && account.email.equals(username)){
                            fh_full_name.text = account.fullname;
                            fh_position.text = account.fullname;
                            fh_profile_image.setImageResource(context!!.resources!!.getIdentifier("drawable/"+accounts[0].avatar, null, context!!.packageName));

                            //Career Note
                            fh_career_note_details.text = account.career_note;

                            //Skills
                            skillList = account.skills as ArrayList<Skill>;
                            fh_rv_skill.layoutManager = LinearLayoutManager(context);
                            skillAdapter = MySkillAdapter(requireContext(), skillList);
                            skillAdapter.notifyDataSetChanged();
                        }
                    }
                }
                fh_rv_skill.adapter = skillAdapter;
            }

            override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                Log.e("RETROFIT_ERROR", "FAILURE TO LOAD JSON")
            }

        });
    }
}