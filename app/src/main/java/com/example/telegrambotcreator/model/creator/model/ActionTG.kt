package com.example.telegrambotcreator.model.creator.model

data class ActionTG(
    //var id: Int,
    //var typeAnswer: String,
    //var fatherId: Int? = null,
    var answerText: String? = null,
    var answerTGFile: String? = null,
    var tgFileType: String? = null,
    var lat: Float? = null,
    var lon: Float? = null,
    var question: String? = null,
    var pollList: List<String>? = null,
    var title: String? = null,
    var address: String? = null,
    var phoneNumber: String? = null,
    var firstName: String? = null,
)
