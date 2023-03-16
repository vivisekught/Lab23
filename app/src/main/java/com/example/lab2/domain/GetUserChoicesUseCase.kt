package com.example.lab2.domain

import androidx.lifecycle.LiveData

class GetUserChoicesUseCase(private val repository: Repository) {

    operator fun invoke(): LiveData<List<UserChoice>> {
        return repository.getUserChoicesList()
    }
}