package com.example.lab2.domain

import androidx.lifecycle.LiveData

class AddUserChoiceUseCase(private val repository: Repository) {

    suspend operator fun invoke(choice: UserChoice) {
        repository.addUserChoice(choice)
    }
}