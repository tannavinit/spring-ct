package com.springct.ui

sealed class LoaderState {
    object Idle : LoaderState()
    object Loading : LoaderState()
    object ErrorDismissed : LoaderState()
    object SuccessDismissed : LoaderState()
}
