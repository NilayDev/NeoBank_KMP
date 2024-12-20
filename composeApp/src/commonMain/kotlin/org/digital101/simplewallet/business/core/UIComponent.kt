package org.digital101.simplewallet.business.core

import org.digital101.simplewallet.business.network.common.JAlertResponse

sealed class UIComponent {

    data class Toast(
        val alert: JAlertResponse
    ) : UIComponent()

    data class Dialog(
        val alert: JAlertResponse
    ) : UIComponent()


    data class ToastSimple(
        val title: String,
    ) : UIComponent()

    data class DialogSimple(
        val title: String,
        val description: String
    ) : UIComponent()


    data class None(
        val message: String,
    ) : UIComponent()


}