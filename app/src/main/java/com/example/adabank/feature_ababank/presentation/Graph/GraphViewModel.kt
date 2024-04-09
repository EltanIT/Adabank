package com.example.adabank.feature_ababank.presentation.Graph

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.R
import com.example.adabank.feature_ababank.domain.model.TransactionData
import com.example.adabank.feature_ababank.presentation.GraphDetails.GraphDetailsState
import com.example.adabank.feature_ababank.presentation.Receipt.ReceiptState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.util.Identity.decode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GraphViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(GraphState())
    val state: State<GraphState> = _state


}