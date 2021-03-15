package am.tatev.news.presentation.usecase.news

import am.tatev.news.data.dto.Article
import am.tatev.news.data.repository.NewsRepository
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val repository: NewsRepository,
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _news = liveData(Dispatchers.IO) {
        _isLoading.postValue(true)
        emit(repository.fetchNews())
        _isLoading.postValue(false)
    }
    val news: LiveData<List<Article>> = _news

    fun onArticleSeen(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setArticleSeen(article)
        }
    }
}