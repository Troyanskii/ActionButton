package com.troy.actionbutton.model

enum class ActionType(var actionType: String) {
    ANIMATION("animation"),
    TOAST("toast"),
    CALL("call"),
    NOTIFICATION("notification"),
    NEW_UNDEFINED("")
}