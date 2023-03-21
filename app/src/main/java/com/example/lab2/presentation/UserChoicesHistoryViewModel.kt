package com.example.lab2.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.example.lab2.data.AppDatabase
import com.example.lab2.data.Mapper
import com.example.lab2.domain.UserChoice

class UserChoicesHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val userChoiceDao = AppDatabase.getInstance(application).userChoiceDao()
    private val mapper = Mapper()

    val userChoices = MediatorLiveData<List<UserChoice>>().apply {
        addSource(userChoiceDao.getUserChoicesList()) {
            value = mapper.mapListDbModelToListEntity(it)
        }
    }

}