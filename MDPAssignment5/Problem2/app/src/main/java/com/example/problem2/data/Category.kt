package com.example.problem2.data

import java.io.Serializable

class Category(var title: String?,
              var image: String?,
): Serializable {
    override fun toString(): String{
        return "Product(title=${title}, image=${image}";
    }
}