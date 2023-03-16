package com.example.lab2.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab2.data.RepositoryImpl
import com.example.lab2.domain.AddUserChoiceUseCase
import com.example.lab2.domain.UserChoice
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _selectionError = MutableLiveData<Boolean>()
    val selectionError: LiveData<Boolean>
        get() = _selectionError

    private var _addedToDb = MutableLiveData<Boolean>()
    val addedToDb: LiveData<Boolean>
        get() = _addedToDb


    private var flags = mutableListOf<Flag>()
    private var figure: Figure? = null


    private val repositoryImpl = RepositoryImpl(application)
    private val addUserChoiceUseCase = AddUserChoiceUseCase(repositoryImpl)

    fun addFigureToResult(figure: Figure) {
        this.figure = figure
    }

    fun addFlag(flag: Flag) {
        flags.add(flag)
    }

    fun removeFlag(flag: Flag) {
        flags.remove(flag)
    }

    private fun addUserChoice() {
        viewModelScope.launch {
            val userChoice = UserChoice(flags = flags.joinToString(), figure.toString())
            addUserChoiceUseCase(userChoice)
            _addedToDb.value = true
        }
    }

    private fun checkAnswers() = (flags.isEmpty() || figure == null)


    fun getResult(): String {

        val result = if (checkAnswers()) {
            _selectionError.value = true
            EMPTY_STRING
        } else {
            addUserChoice()
            _selectionError.value = false
            "${figure}\n${flags.joinToString()}"
        }
        return result
    }

    companion object {

        private const val EMPTY_STRING = ""

        enum class Figure {
            Circle, Square, Triangle, Trapezoid
        }

        enum class Flag {
            Perimeter, Area
        }
    }
}