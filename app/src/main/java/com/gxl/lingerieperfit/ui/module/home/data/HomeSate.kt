package com.gxl.lingerieperfit.ui.module.home.data

import androidx.annotation.DrawableRes
import com.gxl.lingerieperfit.ui.module.home.presentaion.YesIdoOnBoard

data class HomeState(
    val currentOnBoard: YesIdoOnBoard = YesIdoOnBoard.BustFallType,
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

    val onInputBand :String = "",
    val onInputBust :String = "",
    val onInputHip :String ="",

    val fitWomanHood:String?=null,
    val fitMenstrual:String?=null,
    val fitBrand :String?=null,
    val fitBraSize:String?=null,
    val fitBandSize:String?=null,
    val fitCupSize:String?=null,
    val fitHookSize:String?=null,
    val fitStrapSize:String?=null,
    val fitBustFall:String?=null,
    val fitBustShape:String?=null,
    val fitBustPlacement:String?=null,
    val fitYouKnow: String?=null,


    val bandPosition:Int?=null,
    val bustPosition:Int?=null,

    val fitFinalSize:String?=null,

    val sizeChartLoading:Boolean = false,
    val chartDataBra : SizeChartContent?=null,
    val chartDataHip : SizeChartContent?=null,
    val sizeChartError:String?=null,
    val chartImage : String?=null,

    val onBoardData :List<OnBoardData> = emptyList(),
    val topBarInc : List<Boolean> = emptyList()
)


data class CurrentFit(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
    val status: Boolean = false
)

data class SliderValue(
    val label: String,
    val status: Boolean = false
)
data class DoYouKnow(
    val title: String,
    val label : String = "",
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

data class SizeChartContent(
    val sizeImage: String?=null,
    val staticBlock: List<String?>?=null,
)

data class OnBoardData(
    @DrawableRes val image:Int,
    val title: String,
    val dec: String
)