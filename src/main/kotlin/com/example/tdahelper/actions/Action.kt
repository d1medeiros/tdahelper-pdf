package com.example.tdahelper.actions

import com.example.tdahelper.model.Logger

interface Action {
    fun getLogger(): Logger
    fun getName(): String
}


