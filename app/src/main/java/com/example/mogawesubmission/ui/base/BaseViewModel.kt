package com.example.mogawesubmission.ui.base

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.mogawesubmission.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
): ViewModel() {


}