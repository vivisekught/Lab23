package com.example.lab2.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab2.data.AppDatabase
import com.example.lab2.data.Mapper
import com.example.lab2.domain.UserChoice
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val userChoiceDao = AppDatabase.getInstance(application).userChoiceDao()
    private val mapper = Mapper()


    private var _selectionError = MutableLiveData(false)
    val selectionError: LiveData<Boolean>
        get() = _selectionError

    private var _addedToDb = MutableLiveData<Boolean>(false)
    val addedToDb: LiveData<Boolean>
        get() = _addedToDb


    private var flags = mutableListOf<Flag>()
    private var figure: Figure? = null


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
            userChoiceDao.addUserChoice(mapper.mapEntityToDbModel(userChoice))
            _addedToDb.value = true
            _addedToDb.value = false
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