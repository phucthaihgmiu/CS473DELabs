package com.example.mdpassignment6.data

data class Account(
    val about_me: String,
    val career_note: String,
    val certifications: List<Certification>,
    val contact: List<Contact>,
    val education: List<Education>,
    val email: String,
    val experiences: List<Experience>,
    val fullname: String,
    val password: String,
    val position: String,
    val avatar: String,
    val skills: List<Skill>
)

