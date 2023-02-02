package com.troy.actionbutton.model

import com.google.gson.annotations.SerializedName

abstract class Action(
    val type: String = "",
    val enabled: Boolean = false,
    val priority: Int = 0,
    @SerializedName("valid_days")
    val validDays: List<Int> = mutableListOf(),
    @SerializedName("cool_down")
    val coolDown: Int = Int.MAX_VALUE
): IAction
