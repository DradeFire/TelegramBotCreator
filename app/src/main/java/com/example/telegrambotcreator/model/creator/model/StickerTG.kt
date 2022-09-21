package com.example.telegrambotcreator.model.creator.model

data class StickerTG(
    override var id: Int,
    override var fatherId: Int? = null,
    override var typeAnswer: String,
    override var answerText: String? = null,
    override var answerTGFile: String? = null,
    override var tgFileType: String? = null,
    override var lat: Float? = null,
    override var lon: Float? = null,
    override var question: String? = null,
    override var pollList: List<String>? = null,
    override var title: String? = null,
    override var address: String? = null,
    override var phoneNumber: String? = null,
    override var firstName: String? = null,

    override var inAnimation: List<AnimationTG>? = null,
    override var inCommand: List<CommandTG>? = null,
    override var inContact: List<ContactTG>? = null,
    override var inDocument: List<DocumentTG>? = null,
    override var inLocation: List<LocationTG>? = null,
    override var inPhoto: List<PhotoTG>? = null,
    override var inSticker: List<StickerTG>? = null,
    override var inText: List<TextTG>? = null,
    override var inVideoNote: List<VideoNoteTG>? = null,
    override var inVideo: List<VideoTG>? = null,
    override var inVoice: List<VoiceTG>? = null,
    override var inCallBack: List<CallBackTG>? = null
): BaseTgContainer()
