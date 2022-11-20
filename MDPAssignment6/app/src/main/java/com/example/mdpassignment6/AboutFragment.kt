package com.example.mdpassignment6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mdpassignment6.data.Certification
import com.example.mdpassignment6.data.Education
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : Fragment(R.layout.fragment_about) {

    private var educationList = ArrayList<Education>();
    private lateinit var educationAdapter: MyEducationAdapter;

    private var certicationList = ArrayList<Certification>();
    private lateinit var certificationAdapter: MyCertificationAdapter;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//       //Education degrees
        educationList.add(Education(R.drawable.miu, "Maharishi International University", "Master of Computer Science"));
        educationList.add(Education(R.drawable.khtn, "University of Science", "Bachelor of Computer Science"));
        educationList.add(Education(R.drawable.khtn, "Nguyen Thuong Hien High School", "Diploma"));

        fa_rv_education.layoutManager = LinearLayoutManager(context);
        educationAdapter = MyEducationAdapter(requireContext(), educationList);
        fa_rv_education.adapter = educationAdapter;


        //Certifications
        certicationList.add(Certification(R.drawable.loma, "LOMA 280 — Principles of Insurance"));
        certicationList.add(Certification(R.drawable.loma, "LOMA 290 – Insurance Company Operations"));
        certicationList.add(Certification(R.drawable.theinstitutes, "INS 21: Property and Liability Insurance Principles"));

        fa_rv_certification.layoutManager = LinearLayoutManager(context);
        certificationAdapter = MyCertificationAdapter(requireContext(), certicationList);
        fa_rv_certification.adapter = certificationAdapter;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

}