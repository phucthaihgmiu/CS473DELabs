package com.example.mdpassignment6.data

import java.io.Serializable

class Experience(
    var logo: Int,
    var company: String,
    var position: String,
    var period: String,
    var location: String,
    var shortDesc: String
)
    : Serializable {
    override fun toString(): String{
        return "Experience(company=${company}, position=${position}, period=${period}, location=${location}, shortDesc=${shortDesc})";
    }
}