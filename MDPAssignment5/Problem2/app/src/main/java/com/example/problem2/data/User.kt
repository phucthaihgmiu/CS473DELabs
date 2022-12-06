package com.example.problem2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.Serializable

class User(var firstname: String?, var lastname:String?, var username:String, var password:String)
    :Serializable {
    override fun toString(): String{
        return "User(firstname=${firstname}, lastname=${lastname}, username=${username})";
    }
}