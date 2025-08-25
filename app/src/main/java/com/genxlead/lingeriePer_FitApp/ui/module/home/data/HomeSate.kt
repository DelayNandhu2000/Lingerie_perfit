package com.genxlead.lingeriePer_FitApp.ui.module.home.data

import androidx.annotation.DrawableRes
import com.google.android.gms.common.images.WebImage
import com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion.YesIdoOnBoard

data class HomeState(
    val currentOnBoard: YesIdoOnBoard = YesIdoOnBoard.WomenHood,
    val womenHood: List<DoYouKnow> = emptyList(),
    val menstrualCycle: List<DoYouKnow> = emptyList(),
    val doYouSelect: List<DoYouKnow> = emptyList(),
    val cupSize:List<BraSize> = emptyList(),
    val bandSize:List<BraSize> = emptyList(),
    val currentBrand: List<CurrentBrand> = emptyList(),
    val cardHead: Triple<String, String , String>?=null,
    val error: String? = null,
    val otherBrand:String?="",
    val errorOtherBrand :String?=null,
    val bandFit:List<CurrentFit> ?= null,
    val cupFit:List<CurrentFit> = emptyList(),
    val hookFit:List<CurrentFit> = emptyList(),
    val strapFit:List<CurrentFit> = emptyList(),
    val bustFallType:List<CurrentFit> = emptyList(),
    val bustShapeType:List<CurrentFit> = emptyList(),
    val bustPlacementType:List<CurrentFit> = emptyList(),
)


data class CurrentFit(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
)

data class SliderValue(
    val label: String,
    val status: Boolean = false
)
data class DoYouKnow(
    val title: String,
    val status: Boolean = false
)

data class CurrentBrand(
    val brand: String,
    val status: Boolean = false
)

data class BraSize(
    val size : String,
    val status: Boolean = false
)

data class Hood(
    val hood : String,
    val status: Boolean = false
)

data class Menstrual(
    val menstrual : String,
    val status: Boolean = false
)