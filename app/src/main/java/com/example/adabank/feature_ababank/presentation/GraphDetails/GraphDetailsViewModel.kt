package com.example.adabank.feature_ababank.presentation.GraphDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.R
import com.example.adabank.feature_ababank.domain.model.TransactionData
import com.example.adabank.feature_ababank.presentation.Graph.GraphState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GraphDetailsViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(GraphDetailsState())
    val state: State<GraphDetailsState> = _state

}