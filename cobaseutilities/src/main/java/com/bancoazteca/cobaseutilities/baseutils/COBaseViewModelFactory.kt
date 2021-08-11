package com.bancoazteca.cobaseutilities.baseutils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class COBaseViewModelFactory(private val inneriClass: Any) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = inneriClass as T
}