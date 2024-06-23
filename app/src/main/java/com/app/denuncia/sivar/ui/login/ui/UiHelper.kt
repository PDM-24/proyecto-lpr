package com.denuncia.sivar.ui.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isSmallScreenHeight(): Boolean{
    val conf = LocalConfiguration.current
    return conf.screenHeightDp <= 786
}