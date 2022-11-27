package com.example.mdpassignment6

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mdpassignment6.data.Account
import com.example.mdpassignment6.data.Contact
import com.example.mdpassignment6.data.Experience
import com.example.mdpassignment6.util.APIService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.fragment_work.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContactFragment : Fragment() {

    private var contactList = ArrayList<Contact>();
    private lateinit var contactAdapter: MyContactAdapter;


    private val callid:Int = 1;
    private val smsid:Int = 2;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parseAccountJSON();
//        //Test data
//        contactList.add(Contact(R.drawable.ic_baseline_phone_24, "641-819-2508", "Mobile"));
//        contactList.add(Contact(R.drawable.ic_baseline_email_24, "phucthaihg@gmail.com", "Email"));
//        contactList.add(Contact(R.drawable.linkedin, "https://www.linkedin.com/in/phucthaihg/", "Linkedin"));
//        contactList.add(Contact(R.drawable.github, "https://github.com/phucthaihgmiu/CS473DELabs", "Github"));
//        contactList.add(Contact(R.drawable.ic_baseline_picture_as_pdf_24, "Resume", "Resume Pdf"));
//
//        //Adapter
//        fc_rv_contact.layoutManager = LinearLayoutManager(context);
//        contactAdapter = MyContactAdapter(requireContext(), contactList);
//        fc_rv_contact.adapter = contactAdapter;
//
//        registerForContextMenu(fc_rv_contact);
    }


//    override fun onCreateContextMenu(
//        menu: ContextMenu,
//        v: View,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        menu!!.setHeaderTitle("Context Menu Options: ");
//
//        menu.add(0, callid, 0, "Call");
//        menu.add(0, smsid, 0, "Sms");
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
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
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(response.body())
                Log.d("Pretty Printed JSON :", prettyJson)
                val accounts = response.body()!!;
                if(accounts != null){
                    //Contacts
                    contactList = accounts[0].contact as ArrayList<Contact>;
                    fc_rv_contact.layoutManager = LinearLayoutManager(context);
                    contactAdapter = MyContactAdapter(requireContext(), contactList);
                }
                fc_rv_contact.adapter = contactAdapter;
            }

            override fun onFailure(call: Call<List<Account>>, t: Throwable) {
                Log.e("RETROFIT_ERROR", "FAILURE TO LOAD JSON")
            }

        });
    }
}