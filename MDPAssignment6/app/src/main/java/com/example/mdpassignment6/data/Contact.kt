package com.example.mdpassignment6.data

import java.io.Serializable

data class Contact(
    var icon: Int,
    var title: String,
    var type: String
)
    : Serializable {
    override fun toString(): String{
        return "Contact(title=${title}, type=${type})";
    }
}