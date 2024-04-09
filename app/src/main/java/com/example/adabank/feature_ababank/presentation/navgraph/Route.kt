package com.example.adabank.feature_ababank.presentation.navgraph

open class Route(val route: String){

    object SplashScreen: Route("SplashScreen")
    object Login: Route("Login")
    object Verification: Route("Verification")
    object SetPin: Route("SetPin")
    object FingerPrint: Route("FingerPrint")



    object NavigationHome: Route("NavigationHome")


    object Home: Route("Home")
    object Notification: Route("Notification")
    object Wallet: Route("Wallet")
    object Graph: Route("Graph")
    object GraphDetails: Route("GraphDetails")



    object Menu: Route("Menu")
    object TopUpWallet: Route("TopUpWallet")
    object Transfer: Route("Transfer")
    object TransferDetail: Route("TransferDetail?contact={contact}")
    object Receipt: Route("Receipt?receipt={receipt}?contact={contact}")
    object Nearby: Route("Nearby")
    object CodeQr: Route("CodeQr")
    object CodeOrScan: Route("CodeOrScan")


    object ChangePin: Route("ChangePin?balance={balance}")
    object TopUpCard: Route("TopUpCard?balance={balance}")


    object Profile: Route("Profile")
    object ProfileSettings: Route("ProfileSettings")



    object AuthGraph: Route("AuthGraph")
    object AppGraph: Route("AppGraph")


}
