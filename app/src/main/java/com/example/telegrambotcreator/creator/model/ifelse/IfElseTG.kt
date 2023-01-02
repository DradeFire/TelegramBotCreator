package com.example.telegrambotcreator.creator.model.ifelse

import com.example.telegrambotcreator.creator.helper.CompareOperation
import com.example.telegrambotcreator.creator.model.ActionTG

data class IfElseTG (
    var valueToCompare: Comparable<*>,
    var operation: CompareOperation<Comparable<*>>,
    var action: ActionTG,
    var elseAction: ActionTG,
    var isTrue: Boolean? = null,
    var isInt: Boolean = false
)
