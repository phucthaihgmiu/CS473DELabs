package com.example.mdpassignment6.data

import java.io.Serializable

class Skill(
    var title: String,
    var details: ArrayList<String>
)
    : Serializable {
    override fun toString(): String{
        return "Skill(title=${title}: ${details.toString()})";
    }
}