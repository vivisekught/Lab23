package com.example.lab2.domain

import androidx.lifecycle.LiveData

interface Repository {

        suspend fun addUserChoice(choice: UserChoice)

        fun getUserChoicesList(): LiveData<List<UserChoice>>

}