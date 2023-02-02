package com.troy.actionbutton.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.troy.actionbutton.model.*
import com.troy.actionbutton.network.RESTClient
import com.troy.actionbutton.network.Response
import com.troy.actionbutton.repository.dao.ActionDAO
import com.troy.actionbutton.repository.entity.ActionEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActionButtonViewModel @Inject constructor(private val restClient: RESTClient, private val actionDAO: ActionDAO): BaseViewModel() {

    private val currentAvailableActions: ArrayDeque<Action> = ArrayDeque()
    private val _availableActionExist: MutableLiveData<Boolean> = MutableLiveData(false)
    val availableActionsExist: LiveData<Boolean> = _availableActionExist

    fun getAvailableAction(): Action? {
        return if (currentAvailableActions.size > 0) currentAvailableActions.first()
        else null
    }

    fun initAvailableActions() {
        job.cancelIfActive()

        job = viewModelScope.launch(Dispatchers.IO) {
            val allActions = actionDAO.getAllActions()

            if (allActions.isEmpty()) {
                restClient.getAllActions().let { response ->
                    when(response) {
                        is Response.Success -> {
                            actionDAO.insertAll(response.result.map {
                                ActionEntity(
                                    type = it.type,
                                    enabled = it.enabled,
                                    priority = it.priority,
                                    validDays = it.validDays,
                                    coolDown = it.coolDown
                                )
                            })
                            currentAvailableActions.addAll(getCurrentlyActiveActions(response.result).reversed())
                        }
                        is Response.Error -> {
                            error.postValue(response.message)
                        }
                    }
                    _availableActionExist.postValue(currentAvailableActions.size > 0)
                }
            } else {
                currentAvailableActions.addAll(getCurrentlyActiveActionsFromEntities(allActions).reversed())
                _availableActionExist.postValue(currentAvailableActions.size > 0)
            }
        }
    }

    private fun getCurrentlyActiveActionsFromEntities(actionsList: List<ActionEntity>): List<Action> {
        // TODO: validate currently active action and put them to the Stack
        val validActions = mutableListOf<Action>()
        actionsList.map {
            when (it.type) {
                ActionType.ANIMATION.actionType -> {
                    validActions.add(AnimationAction(
                        type = it.type,
                        enabled = it.enabled,
                        priority = it.priority,
                        validDays = it.validDays,
                        coolDown = it.coolDown
                    ))
                }
                ActionType.TOAST.actionType -> {
                    validActions.add(ToastAction(
                        type = it.type,
                        enabled = it.enabled,
                        priority = it.priority,
                        validDays = it.validDays,
                        coolDown = it.coolDown
                    ))
                }
                ActionType.CALL.actionType -> {
                    validActions.add(CallAction(
                        type = it.type,
                        enabled = it.enabled,
                        priority = it.priority,
                        validDays = it.validDays,
                        coolDown = it.coolDown
                    ))
                }
                ActionType.NOTIFICATION.actionType -> {
                    validActions.add(NotificationAction(
                        type = it.type,
                        enabled = it.enabled,
                        priority = it.priority,
                        validDays = it.validDays,
                        coolDown = it.coolDown
                    ))
                }
                else -> {

                }
            }
        }
        return validActions
    }

    private fun getCurrentlyActiveActions(actionsList: List<Action>): List<Action> {
        // TODO: validate currently active action and put them to the Stack
        return actionsList
    }

    fun executeAction(action: Action) {
        // TODO: implement logic for update certain action in DB
    }
}