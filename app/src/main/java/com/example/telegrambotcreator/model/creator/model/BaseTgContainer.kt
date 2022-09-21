package com.example.telegrambotcreator.model.creator.model

sealed class BaseTgContainer {
    abstract var id: Int
    abstract var fatherId: Int?
    abstract var typeAnswer: String
    abstract var answerText: String?
    abstract var answerTGFile: String?
    abstract var tgFileType: String?
    abstract var lat: Float?
    abstract var lon: Float?
    abstract var question: String?
    abstract var pollList: List<String>?
    abstract var title: String?
    abstract var address: String?
    abstract var phoneNumber: String?
    abstract var firstName: String?

    abstract var inAnimation: List<AnimationTG>?
    abstract var inCommand: List<CommandTG>?
    abstract var inContact: List<ContactTG>?
    abstract var inDocument: List<DocumentTG>?
    abstract var inLocation: List<LocationTG>?
    abstract var inPhoto: List<PhotoTG>?
    abstract var inSticker: List<StickerTG>?
    abstract var inText: List<TextTG>?
    abstract var inVideoNote: List<VideoNoteTG>?
    abstract var inVideo: List<VideoTG>?
    abstract var inVoice: List<VoiceTG>?
    abstract var inCallBack: List<CallBackTG>?
}
