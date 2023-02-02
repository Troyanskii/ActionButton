package com.troy.actionbutton.model

class CallAction(
    type: String,
    enabled: Boolean,
    priority: Int,
    validDays: List<Int>,
    coolDown: Int = Int.MAX_VALUE
) : Action(
    type,
    enabled,
    priority,
    validDays,
    coolDown
) {

    override fun executeAction() {

    }
}