package com.example.lab2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(): ViewModel() {

    private var _selectionError = MutableLiveData<Boolean>()
    val selectionError: LiveData<Boolean>
        get() = _selectionError

    private var flags = mutableListOf<Flag>()
    private var figure: Figure? = null


    fun addFigureToResult(figure: Figure){
        this.figure = figure
    }

    fun addFlag(flag: Flag){
        flags.add(flag)
    }

    fun removeFlag(flag: Flag){
        flags.remove(flag)
    }

    private fun checkAnswers() = (flags.isEmpty() || figure == null)


    fun getResult(): String{

        Log.d("Nikita", flags.toString())

        val result =  if (checkAnswers()){
            _selectionError.value = true
            EMPTY_STRING
        }
        else {
            _selectionError.value = false
            "${figure}\n${flags.toString()
                .replace("[", "")
                .replace("]", "")}"
        }
        return result
    }

    companion object {

        private const val EMPTY_STRING = ""

        enum class Figure{
            Circle, Square, Triangle, Trapezoid
        }

        enum class Flag{
            Perimeter, Area
        }
    }
}