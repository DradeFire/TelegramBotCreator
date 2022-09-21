package com.example.telegrambotcreator.model.creator.model

import com.google.gson.annotations.SerializedName

data class AnimationTG(
    @SerializedName("id")
    override var id: Int,
    @SerializedName("fatherId")
    override var fatherId: Int? = null,
    @SerializedName("typeAnswer")
    override var typeAnswer: String,
    @SerializedName("answerText")
    override var answerText: String? = null,
    @SerializedName("answerTGFile")
    override var answerTGFile: String? = null,
    @SerializedName("tgFileType")
    override var tgFileType: String? = null,
    @SerializedName("lat")
    override var lat: Float? = null,
    @SerializedName("lon")
    override var lon: Float? = null,
    @SerializedName("question")
    override var question: String? = null,
    @SerializedName("pollList")
    override var pollList: List<String>? = null,
    @SerializedName("title")
    override var title: String? = null,
    @SerializedName("address")
    override var address: String? = null,
    @SerializedName("phoneNumber")
    override var phoneNumber: String? = null,
    @SerializedName("firstName")
    override var firstName: String? = null,

    @SerializedName("inAnimation")
    override var inAnimation: List<AnimationTG>? = null,
    @SerializedName("inCommand")
    override var inCommand: List<CommandTG>? = null,
    @SerializedName("inContact")
    override var inContact: List<ContactTG>? = null,
    @SerializedName("inDocument")
    override var inDocument: List<DocumentTG>? = null,
    @SerializedName("inLocation")
    override var inLocation: List<LocationTG>? = null,
    @SerializedName("inPhoto")
    override var inPhoto: List<PhotoTG>? = null,
    @SerializedName("inSticker")
    override var inSticker: List<StickerTG>? = null,
    @SerializedName("inText")
    override var inText: List<TextTG>? = null,
    @SerializedName("inVideoNote")
    override var inVideoNote: List<VideoNoteTG>? = null,
    @SerializedName("inVideo")
    override var inVideo: List<VideoTG>? = null,
    @SerializedName("inVoice")
    override var inVoice: List<VoiceTG>? = null,
    override var inCallBack: List<CallBackTG>? = null
): BaseTgContainer()
