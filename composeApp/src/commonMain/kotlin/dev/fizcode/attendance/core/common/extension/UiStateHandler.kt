package dev.fizcode.attendance.core.common.extension

sealed class UiState<out T> {
    data object Empty : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(
        val exception: Exception? = null,
        val message: String? = exception?.message,
        val code: Int? = null,
        val type: String? = null
    ) : UiState<Nothing>()
}
