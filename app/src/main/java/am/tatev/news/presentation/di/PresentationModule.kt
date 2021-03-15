package am.tatev.news.presentation.di

import am.tatev.news.presentation.usecase.news.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val PresentationModule = module {

    viewModel { NewsViewModel(repository = get()) }

}