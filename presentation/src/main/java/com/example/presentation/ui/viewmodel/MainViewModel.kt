package com.example.presentation.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.domain.exception.NetworkAccessException
import com.example.domain.interactor.IAstronomyPictureInteractor
import com.example.domain.model.APODEntity
import com.example.presentation.utils.DateUtils.getDateOffset
import kotlinx.coroutines.launch

/**
 * ViewModel предоставления астронамической картинки
 */
class MainViewModel(
        /**
         * @constructor interactor                 экземпляр интерактора
         * @constructor schedulerProvider          обертка над планировщиками RxJava
         * @constructor currentPositionPageAdapter текущая позиция PageAdapter'a на экране
         */
        private val interactor: IAstronomyPictureInteractor,
        private val currentPositionViewPage: Int
) : ViewModel() {

    companion object {
        private const val JPG = ".jpg"
        private const val PNG = ".png"
    }

    private val _isNetworkError = MutableLiveData(false)
    private val _isLoadingPicture = MutableLiveData(false)
    private val _isLoadingData = MutableLiveData(false)
    private val _isPictureView = MutableLiveData(true)

    /**
     * Состояние видимости сетевой ошибки
     */
    val isNetworkError: LiveData<Boolean> = _isNetworkError

    /**
     * Состояние загрузки картинки (используется для progress bar)
     */
    val isLoadingPicture: LiveData<Boolean> = _isLoadingPicture

    /**
     * Состояние загрузки данных
     */
    val isLoadingData: LiveData<Boolean> = _isLoadingData

    /**
     * Соятояние для определения картинки или ролика на экране
     */
    val isPictureView: LiveData<Boolean> = _isPictureView

    /**
     * Строка с заголовком
     */
    val title = ObservableField<String>()

    /**
     * Строка с описанием
     */
    val explanation = ObservableField<String>()

    /**
     * Строка с url картинки
     */
    val urlPicture = ObservableField<String>()

    /**
     * Строка с полем copyright
     */
    val copyright = ObservableField<String>()

    /**
     * Состояние видимости ошибки
     */
    val isErrorVisible = ObservableBoolean(false)

    /**
     * @return возвращает листенер для реагирования на нажатие imageView
     */
    var clickListener: View.OnClickListener? = null

    /**
     * Загрузка и отображение данных на экране
     */
    fun showInformation() {
        viewModelScope.launch {
            _isLoadingData.value = true
            _isLoadingPicture.value = true
            isErrorVisible.set(false)
            try {
                val entity = interactor.getAstronomyPicture(getDateOffset(currentPositionViewPage))
                bindView(entity)
            } catch (exception: Exception) {
                if (exception is NetworkAccessException) {
                    _isNetworkError.value = true
                }
                isErrorVisible.set(true)
            } finally {
                _isLoadingData.value = false
            }
        }
    }

    private fun bindView(apodEntity: APODEntity) {
        val url = apodEntity.url
        if (isPictureUrl(url)) {
            urlPicture.set(url)
            _isPictureView.postValue(true)
        } else {
            urlPicture.set(getYouTubeUrl(url))
            _isPictureView.postValue(false)
        }
        title.set(apodEntity.title)
        explanation.set(apodEntity.explanation)
        copyright.set(apodEntity.copyright)
    }

    /**
     * Проверка является ли url на картинку или видео
     *
     * @param url картинки
     */
    private fun isPictureUrl(url: String) = url.endsWith(JPG) || url.endsWith(PNG)


    /**
     * @param url картинки
     * @return распарсенный url для youtube
     */
    private fun getYouTubeUrl(url: String): String {
        val endSizeUrl = 6
        val array = url.split("/").toTypedArray()
        val youTubeUrl = array[array.size - 1]
        return youTubeUrl.subSequence(0, youTubeUrl.length - endSizeUrl).toString()
    }

    /**
     * Обновление данных на экране в случае возникновения ошибки
     *
     * @return возвращает OnRefreshListener
     */
    fun getOnRefreshListener() = OnRefreshListener { showInformation() }
}