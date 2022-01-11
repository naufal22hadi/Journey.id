package com.example.journeyid.util

import com.example.journeyid.data.database.UserDatabase
import com.example.journeyid.domain.repository.UserRepository
import com.example.journeyid.domain.usecases.UserUseCase
import com.example.journeyid.presentation.createacc.CreateAccountViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CreateAccountViewModel(get())
    }
}

val usecaseModule = module {
    single{
        UserUseCase(get())
    }
}

val repositoryModule = module {
    single{
        UserRepository(get())
    }
}

val localModule = module {
    single {
        UserDatabase.getDatabase(get()).userDao()
    }
}

val appModule : List<Module> = listOf(
    viewModelModule,
    usecaseModule,
    repositoryModule,
    localModule
)
