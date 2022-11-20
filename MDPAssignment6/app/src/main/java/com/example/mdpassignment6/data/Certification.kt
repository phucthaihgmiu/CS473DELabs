package com.example.mdpassignment6.data

import java.io.Serializable

class Certification(
    var logo: Int,
    var title: String
)
    : Serializable {
    override fun toString(): String{
        return "Certification(title=${title})";
    }
}