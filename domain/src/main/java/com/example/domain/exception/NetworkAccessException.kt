package com.example.domain.exception

/**
 * Exception определяющий сетевую ошибку в работе приложения
 */
class NetworkAccessException(throwable: Throwable) : Exception(throwable)