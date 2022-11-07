package com.example.problem2.data

import java.io.Serializable

class Product(var title: String,
              var price: Double,
              var color: String,
              var image: Int,
              var itemid: String,
              var desc: String,
              var category: String
)
    : Serializable {
    override fun toString(): String{
        return "Product(title=${title}, category=${category}, price=${price}, color=${color}), image=${image}, itemid=${itemid}, desc=${desc}";
    }
}