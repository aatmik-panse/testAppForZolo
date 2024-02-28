package com.example.test.api

data class ListBookEntity (
    var id: Int = 0,
    var name: String = "",
    var status: String = "",
    var thumbnail: String = "",
    var owner: Int = 0,
):java.io.Serializable