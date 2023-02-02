package com.troy.actionbutton.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.troy.actionbutton.R
import com.troy.actionbutton.viewmodel.ActionButtonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val actionViewModel: ActionButtonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        findViewById<View>(R.id.action_btn).setOnClickListener {
            actionViewModel.getAvailableAction()?.executeAction()
        }
    }

    private fun initData() {
        actionViewModel.initAvailableActions()
    }
}