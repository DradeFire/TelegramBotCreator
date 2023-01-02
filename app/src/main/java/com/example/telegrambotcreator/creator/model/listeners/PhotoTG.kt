package com.example.telegrambotcreator.creator.model.listeners

import com.example.telegrambotcreator.creator.model.ActionTG

data class PhotoTG(
    override var id: Int,
    override var typeAnswer: String,
    override var fatherId: Int? = null,
    override var action: ActionTG? = null,
    override var elseAction: ActionTG? = null,
    override var isTrue: Boolean? = null,
    override var inListeners: List<ListenerTgBase>? = null,
    override var inCallBack: List<CallBackTG>? = null

//    override var inAnimation: List<AnimationTG>? = null,
//    override var inCommand: List<CommandTG>? = null,
//    override var inContact: List<ContactTG>? = null,
//    override var inDocument: List<DocumentTG>? = null,
//    override var inLocation: List<LocationTG>? = null,
//    override var inPhoto: List<PhotoTG>? = null,
//    override var inSticker: List<StickerTG>? = null,
//    override var inText: List<TextTG>? = null,
//    override var inVideoNote: List<VideoNoteTG>? = null,
//    override var inVideo: List<VideoTG>? = null,
//    override var inVoice: List<VoiceTG>? = null,
//    override var inCallBack: List<CallBackTG>? = null,
): ListenerTgBase()
