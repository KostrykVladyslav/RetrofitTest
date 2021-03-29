package com.example.retrofittest.questList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.retrofittest.data.remote.quest.QuestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class QuestListViewModel (application: Application): AndroidViewModel(application){

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchQuestList(questApi: QuestApi?){
        questApi?.let {
            compositeDisposable.add(questApi.getQuestList()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },{

                }))
        }
    }
}