package com.example.mogawesubmission.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mogawesubmission.data.repository.AuthRepository
import com.example.mogawesubmission.data.repository.BaseRepository
import com.example.mogawesubmission.data.repository.UserRepository
import com.example.mogawesubmission.ui.auth.AuthViewModel
import com.example.mogawesubmission.ui.home.HomeViewModel

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository ) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModel Class not Found")
        }
    }
}