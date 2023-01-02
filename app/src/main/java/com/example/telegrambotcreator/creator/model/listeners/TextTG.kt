package com.example.telegrambotcreator.creator.model.listeners

import com.example.telegrambotcreator.creator.model.ActionTG
import com.example.telegrambotcreator.creator.model.ifelse.IfElseTG

data class TextTG(
    override var id: Int,
    var text: String,
    override var typeAnswer: String,
    override var fatherId: Int? = null,
    override var action: ActionTG? = null,
    var inIfElse: List<IfElseTG>? = null,
    override var inListeners: List<ListenerTgBase>? = null,
    override var inCallBack: List<CallBackTG>? = null,
    override var elseAction: ActionTG? = null,
    override var isTrue: Boolean? = null,
) : ListenerTgBase()
