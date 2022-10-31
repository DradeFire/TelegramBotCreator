package com.example.telegrambotcreator.model.creator.model

sealed class ListenerTgBase {
    abstract var id: Int
    abstract var typeAnswer: String
    abstract var fatherId: Int?
    abstract var action: ActionTG?
    abstract var elseAction: ActionTG?
    abstract var isTrue: Boolean?
    abstract var inListeners: List<ListenerTgBase>?
    abstract var inCallBack: List<CallBackTG>?

//    open var inAnimation: List<AnimationTG>? = null
//    open var inCommand: List<CommandTG>? = null
//    open var inContact: List<ContactTG>? = null
//    open var inDocument: List<DocumentTG>? = null
//    open var inLocation: List<LocationTG>? = null
//    open var inPhoto: List<PhotoTG>? = null
//    open var inSticker: List<StickerTG>? = null
//    open var inText: List<TextTG>? = null
//    open var inVideoNote: List<VideoNoteTG>? = null
//    open var inVideo: List<VideoTG>? = null
//    open var inVoice: List<VoiceTG>? = null
}
