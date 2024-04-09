package com.example.adabank.feature_ababank.presentation.MenuScreen

import com.example.adabank.R

data class MenuState(
    val search: String = ""
){

    val shortcuts = listOf(
        MenuItem(
            "Send Money",
            R.drawable.ic_menu_send_money
        ),
        MenuItem(
            "Top-up Wallet",
            R.drawable.ic_menu_wallet
        ),
        MenuItem(
            "Bill Payment",
            R.drawable.ic_menu_bill_payment
        ),
        MenuItem(
            "Code Qr",
            R.drawable.ic_menu_code_qr
        ),
    )
    val otherMenu = listOf(
        MenuItem(
            "History Transactions",
            R.drawable.ic_menu_history_tran
        ),
        MenuItem(
            "Request Payment",
            R.drawable.ic_menu_request_payment
        ),
        MenuItem(
            "Change Money",
            R.drawable.ic_menu_change_money
        ),
        MenuItem(
            "Savings",
            R.drawable.ic_menu_savings
        ),
        MenuItem(
            "Investment",
            R.drawable.ic_menu_investment
        ),
        MenuItem(
            "Settings",
            R.drawable.ic_menu_settings
        ),
    )
}