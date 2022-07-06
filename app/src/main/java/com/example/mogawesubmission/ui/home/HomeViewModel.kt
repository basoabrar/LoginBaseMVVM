package com.example.mogawesubmission.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mogawesubmission.data.network.Res
import com.example.mogawesubmission.data.reponses.DataResponse
import com.example.mogawesubmission.data.reponses.LoginResponse
import com.example.mogawesubmission.data.repository.UserRepository
import com.example.mogawesubmission.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(private  val repository : UserRepository) : BaseViewModel(repository) {

    private val _user : MutableLiveData<Res<DataResponse>> = MutableLiveData()
    val user : LiveData<Res<DataResponse>>
    get() = _user

    fun getuser() = viewModelScope.launch {
        _user.value = Res.Loading
        _user.value = repository.getUser()
    }
}