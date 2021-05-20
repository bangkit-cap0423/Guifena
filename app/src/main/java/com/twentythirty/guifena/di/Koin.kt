package com.twentythirty.guifena.di

import com.twentythirty.guifena.data.MainRepository
import com.twentythirty.guifena.network.RetroBuilder
import com.twentythirty.guifena.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Koin {
    val appModule = module {
        single { RetroBuilder.guifenaApi }
        single { MainRepository(get()) }
        viewModel { HomeViewModel(get()) }
    }
}