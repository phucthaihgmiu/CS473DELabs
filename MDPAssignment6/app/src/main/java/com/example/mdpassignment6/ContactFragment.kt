package com.example.mdpassignment6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdpassignment6.data.Contact
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.fragment_work.*

class ContactFragment : Fragment() {

    private var contactList = ArrayList<Contact>();
    private lateinit var contactAdapter: MyContactAdapter;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//       //Education degrees
        contactList.add(Contact(R.drawable.ic_baseline_phone_24, "641-819-2508", "Mobile"));
        contactList.add(Contact(R.drawable.ic_baseline_email_24, "phucthaihg@gmail.com", "Email"));
        contactList.add(Contact(R.drawable.linkedin, "https://www.linkedin.com/in/phucthaihg/", "Linkedin"));
        contactList.add(Contact(R.drawable.github, "https://github.com/phucthaihgmiu/CS473DELabs", "Github"));
        contactList.add(Contact(R.drawable.ic_baseline_picture_as_pdf_24, "Resume", "Resume Pdf"));


        fc_rv_contact.layoutManager = LinearLayoutManager(context);
        contactAdapter = MyContactAdapter(requireContext(), contactList);
        fc_rv_contact.adapter = contactAdapter;

    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

}