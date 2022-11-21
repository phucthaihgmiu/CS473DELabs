package com.example.mdpassignment6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdpassignment6.adapter.MySkillAdapter
import com.example.mdpassignment6.data.Certification
import com.example.mdpassignment6.data.Education
import com.example.mdpassignment6.data.Skill
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var skillList = ArrayList<Skill>();
    private lateinit var skillAdapter: MySkillAdapter;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        skillList.add(Skill("Languages", arrayListOf("COBOL", "JCL", "CICS", "CL", "SQL", "Java")));
        skillList.add(Skill("Web", arrayListOf("HTML", "CSS", "JavaScript", "Node.JS", "Angular", "JSON", "XML")));
        skillList.add(Skill("Web Services", arrayListOf("REST API", "SOAP")));

        fh_rv_skill.layoutManager = LinearLayoutManager(context);
        skillAdapter = MySkillAdapter(requireContext(), skillList);
        fh_rv_skill.adapter = skillAdapter;
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}