package com.example.neglectapp.di

import com.example.neglectapp.data.datastore.IDataStore
import com.example.neglectapp.data.datastore.LocalDataStore
import org.koin.dsl.module

val dependenciesAppModule = module {
    single { LocalDataStore() }
}