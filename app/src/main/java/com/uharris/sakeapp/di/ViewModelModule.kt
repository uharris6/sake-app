package com.uharris.sakeapp.di

import com.uharris.sakeapp.detail.DetailViewModel
import com.uharris.sakeapp.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::DetailViewModel)
}