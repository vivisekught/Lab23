package com.example.lab2.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.lab2.data.RepositoryImpl
import com.example.lab2.domain.GetUserChoicesUseCase

class UserChoicesHistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repositoryImpl = RepositoryImpl(application)

    private val getUserChoicesUseCase = GetUserChoicesUseCase(repositoryImpl)

    val userChoices = getUserChoicesUseCase.invoke()

}