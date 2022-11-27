package com.example.mdpassignment6.data

import java.io.Serializable

data class Education(
    var logo: String,
    var name: String,
    var degree: String
)
    : Serializable {
    override fun toString(): String{
        return "Education Institution(name=${name}, degree=${degree})";
    }
}