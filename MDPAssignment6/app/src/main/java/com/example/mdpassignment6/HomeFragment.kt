package com.example.mdpassignment6

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdpassignment6.adapter.MySkillAdapter
import com.example.mdpassignment6.data.*
import com.example.mdpassignment6.util.RestApi
import com.example.mdpassignment6.util.RestApiService
import kotlinx.android.synthetic.main.fragment_home.*
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
        getAccounts();

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

    private fun getAccounts(){
        val apiService = RestApiService()
        apiService.getAccounts (){
            if(it != null){
                val accounts = it.filter { account -> account.isActive };
                for(account in accounts){
                    if(!username.isNullOrEmpty() && account.email.equals(username)){
                        fh_full_name.text = account.fullname;
                        fh_position.text = account.fullname;
                        fh_profile_image.setImageResource(requireContext().resources!!.getIdentifier("drawable/"+account.avatar, null, requireContext().packageName));

                        //Career Note
                        fh_career_note_details.text = account.career_note;

                        //Skills
                        skillList = account.skills as ArrayList<Skill>;
                        fh_rv_skill.layoutManager = LinearLayoutManager(context);
                        skillAdapter = MySkillAdapter(requireContext(), skillList);
                        skillAdapter.notifyDataSetChanged();
                    }
                }

                fh_rv_skill.adapter = skillAdapter;
            }else{
                Log.e("RETROFIT_ERROR", "FAILURE TO GET ACCOUNT")
            }
        }
    }
}