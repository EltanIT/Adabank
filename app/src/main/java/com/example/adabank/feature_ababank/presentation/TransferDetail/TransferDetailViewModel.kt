package com.example.adabank.feature_ababank.presentation.TransferDetail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.model.ReceiptData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TransferDetailViewModel @Inject constructor(

): ViewModel() {


    private val _state = mutableStateOf(TransferDetailState())
    val state: State<TransferDetailState> = _state

    private var widthSwipeBox = 0f
    private var widthSwipe = 0f
    private lateinit var localDensity: Density

    fun onEvent(event: TransferDetailEvent){
        when(event){
            is TransferDetailEvent.EnteredAmount -> {
                _state.value = state.value.copy(
                    amount = state.value.amount+event.value
                )
            }
            TransferDetailEvent.DeleteAmount -> {
                _state.value = state.value.copy(
                    amount = state.value.amount.dropLast(1)
                )
            }


            is TransferDetailEvent.EnteredSwipeCoordinate -> {
                val value = event.value
                val halfWidthSwipe = widthSwipe/2

                if (value in widthSwipeBox-halfWidthSwipe..widthSwipeBox){
                    _state.value = state.value.copy(
                        coordinate = with(localDensity){value.toDp()-halfWidthSwipe.toDp()},
                        stateSwap = true
                    )

                }else if (value in halfWidthSwipe..widthSwipeBox){
                    _state.value = state.value.copy(
                        coordinate = with(localDensity){value.toDp()-halfWidthSwipe.toDp()}
                    )
                }
            }
        }
    }




    fun setContact(contactData: ContactData) {
        _state.value = state.value.copy(
            contact = contactData
        )
    }

    fun setWidthSwipeBox(width: Float, localDensity: Density) {
        this.localDensity = localDensity
        val dp = with(localDensity){width.toDp()-24.dp}
        widthSwipeBox = with(localDensity){dp.toPx()}

        Log.i("pay", widthSwipeBox.toString())
        Log.i("pay", dp.toString())
    }

    fun setWidthSwipe(width: Float) {
        widthSwipe = width
    }


    fun getData(): String {
        return Gson().toJson(
            ReceiptData(
            total = state.value.amount,
            recipient_id = state.value.contact.number,
        ))
    }

    fun getContact(): String {
        return Gson().toJson(state.value.contact)
    }
}