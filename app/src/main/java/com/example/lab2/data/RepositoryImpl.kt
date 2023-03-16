package com.example.lab2.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.lab2.domain.Repository
import com.example.lab2.domain.UserChoice

class RepositoryImpl(
    application: Application
) : Repository {

    private val userChoiceDao = AppDatabase.getInstance(application).userChoiceDao()
    private val mapper = Mapper()

    override suspend fun addUserChoice(choice: UserChoice) {
        userChoiceDao.addUserChoice(mapper.mapEntityToDbModel(choice))
    }

    override fun getUserChoicesList(): LiveData<List<UserChoice>> {
        return MediatorLiveData<List<UserChoice>>().apply {
            addSource(userChoiceDao.getUserChoicesList()) {
                value = mapper.mapListDbModelToListEntity(it)
            }
        }
    }
}