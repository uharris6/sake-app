package com.uharris.sakeapp.di

import com.uharris.sakeapp.data.ShopServiceImpl
import com.uharris.sakeapp.domain.ShopService
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single(named("ioDispatcher")) {
        Dispatchers.IO
    }
    single<ShopService> {
        ShopServiceImpl(get(), get(named("ioDispatcher")))
    }
}