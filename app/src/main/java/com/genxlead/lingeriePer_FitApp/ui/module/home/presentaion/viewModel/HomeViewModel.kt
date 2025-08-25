package com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion.viewModel


import android.util.Log
import androidx.lifecycle.ViewModel
import com.genxlead.lingeriePer_FitApp.R
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.BraSize
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.CurrentBrand
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.CurrentFit
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.DoYouKnow
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.HomeState
import com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion.YesIdoOnBoard
import androidx.lifecycle.viewModelScope
import com.gxl.lingerieperfit.R
import com.gxl.lingerieperfit.ui.base.network.ApiResult
import com.gxl.lingerieperfit.ui.module.home.data.BraSize
import com.gxl.lingerieperfit.ui.module.home.data.CurrentBrand
import com.gxl.lingerieperfit.ui.module.home.data.CurrentFit
import com.gxl.lingerieperfit.ui.module.home.data.DoYouKnow
import com.gxl.lingerieperfit.ui.module.home.data.HomeState
import com.gxl.lingerieperfit.ui.module.home.data.HomeUseCase
import com.gxl.lingerieperfit.ui.module.home.data.OnBoardData
import com.gxl.lingerieperfit.ui.module.home.presentaion.SelectedFit
import com.gxl.lingerieperfit.ui.module.home.presentaion.YesIdoOnBoard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    val homeUseCase: HomeUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    fun getSizeChart(category: String) {
        viewModelScope.launch {
            homeUseCase.getSizeChart(category).collect { result ->
                when (result) {
                    is ApiResult.Error -> {
                        _state.update {
                            it.copy(sizeChartError = result.errorBody, sizeChartLoading = false)
                        }
                        Log.d("TAGss!!", "getSizeChart: ${result.errorBody}")
                    }

                    ApiResult.Loading -> {
                        _state.update {
                            it.copy(sizeChartLoading = true)
                        }
                        Log.d("TAGss!!", "getSizeLoading")
                    }

                    is ApiResult.Success -> {
                        if (category != "panty") {
                            _state.update {
                                it.copy(sizeChartLoading = false, chartDataBra = result.data)
                            }
                        } else {
                            _state.update {
                                it.copy(sizeChartLoading = false, chartDataHip = result.data)
                            }
                        }
                    }
                }
            }
        }
    }

    fun getOnBoarData(){
        _state.update {
            it.copy(
                onBoardData = isOnBoardData()
            )
        }
    }

    fun resetFit(yesIdoOnBoard: YesIdoOnBoard) {
        when (yesIdoOnBoard) {
            YesIdoOnBoard.WomenHood -> {
                _state.update {
                    it.copy(womenHood = state.value.womenHood.map { item ->
                        item.copy(
                            status = false
                        )
                    })
                }
            }

            YesIdoOnBoard.MenstrualCycle -> {
                _state.update {
                    it.copy(menstrualCycle = state.value.menstrualCycle.map { item ->
                        item.copy(
                            status = false
                        )
                    })
                }
            }

            YesIdoOnBoard.WhichBRAND -> {
                _state.update {
                    it.copy(currentBrand = state.value.currentBrand.map { item ->
                        item.copy(
                            status = false
                        )
                    })
                }
            }

            YesIdoOnBoard.WhichSize -> {
                _state.update { it ->
                    it.copy(bandSize = state.value.bandSize.map {
                        it.copy(status = false)
                    }, cupSize = state.value.cupSize.map {
                        it.copy(status = false)
                    })
                }
            }

            YesIdoOnBoard.DoYoKnow -> {

            }

            YesIdoOnBoard.BandFit -> {
                _state.update {
                    it.copy(
                        bandFit = state.value.bandFit?.map { item ->
                            if (item.title == "RIDES UP") {
                                item.copy(status = true)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            YesIdoOnBoard.CupFit -> {
                _state.update {
                    it.copy(
                        cupFit = state.value.cupFit.map { item ->
                            if (item.title == "LOOSE") {
                                item.copy(status = true)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            YesIdoOnBoard.HookFit -> {
                _state.update {
                    it.copy(
                        hookFit = state.value.hookFit.map { item ->
                            if (item.title == "LOOSEST") {
                                item.copy(status = true)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            YesIdoOnBoard.StrapFit -> {
                _state.update {
                    it.copy(
                        strapFit = state.value.strapFit.map { item ->
                            if (item.title == "FALLS OFF") {
                                item.copy(status = true)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            YesIdoOnBoard.ShoulderType -> {

            }

            YesIdoOnBoard.BustFallType -> {
                _state.update {
                    it.copy(
                        bustFallType = state.value.bustFallType.map { item ->
                            if (item.title == "FIRM") {
                                item.copy(status = true)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            YesIdoOnBoard.BustShapeType -> {
                _state.update {
                    it.copy(
                        bustShapeType = state.value.bustShapeType.map { item ->
                            if (item.title == "SHALLOW") {
                                item.copy(status = true)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            YesIdoOnBoard.PlacementType -> {
                _state.update {
                    it.copy(
                        bustPlacementType = state.value.bustPlacementType.map { item ->
                            if (item.title == "SPACED") {
                                item.copy(status = true)
                            } else {
                                item
                            }
                        }
                    )
                }
            }

            YesIdoOnBoard.Final -> {

            }

            YesIdoOnBoard.ChartCalculationBra -> {

            }

            YesIdoOnBoard.ChartCalculationHip -> {

            }
        }
    }


    fun resetAllFit() {
        _state.update {
            it.copy(
                fitWomanHood = null,
                fitMenstrual = null,
                fitBrand = null,
                fitBraSize = null,
                fitBandSize = null,
                fitCupSize = null,
                fitHookSize = null,
                fitStrapSize = null,
                fitBustFall = null,
                fitBustShape = null,
                fitBustPlacement = null
            )
        }
    }

    fun restStart() {
        _state.update {
            it.copy(
                fitBrand = null,
                fitBraSize = null,
                fitBandSize = null,
                fitCupSize = null,
                fitHookSize = null,
                fitStrapSize = null,
            )
        }
    }

    fun setTopBarInc(){
        val barBodyType = state.value.fitBustFall != null
        val barWomanHood = state.value.fitWomanHood != null
        val barCurrentBrand = state.value.fitBrand != null
        val barCurrentSize = state.value.fitBraSize != null
        val barCurrentFit = state.value.fitBandSize != null
        _state.update {
            it.copy(
                topBarInc = listOf(barBodyType, barWomanHood, barCurrentBrand,barCurrentSize,barCurrentFit)
            )
        }
    }

    fun getSelecters(currentOnBoard: YesIdoOnBoard) {
        val hood = listOf(
            Pair("TEENAGE", "Initial stage of bust development after puberty"),
            Pair("PRIME ADULT", "Woman between 18-50 years of age"),
            Pair("MATERNITY / NURSING", "Pregnant women breast feeding women"),
            Pair("PRE-MENOPAUSE / POST-MENOPAUSE", "Woman undergoing stopping of menstrual cycle"),
            Pair("SENIORS", "Woman aged 50 above"),
        )
        if (state.value.fitWomanHood == null) {
            _state.update {
                it.copy(womenHood = hood.map { item ->
                    DoYouKnow(
                        title = item.first,
                        label = item.second
                    )
                })
            }
        }


        val menstrual = listOf(
            Pair("15 DAYS BEFORE PERIODS", "Menstruation coming up in 15 days"),
            Pair("PERIODS", "Currently in menstruation"),
            Pair("15 DAYS AFTER PERIODS", "15 days after menstrual cycle"),
            Pair("PCOD,IRREGULAR PERIODS,PCOS", "Irregular menstruation"),
            Pair("NO PERIODS", "No menstrual cycle"),
        )
        if (state.value.fitMenstrual == null) {
            _state.update {
                it.copy(menstrualCycle = menstrual.map { item ->
                    DoYouKnow(
                        title = item.first,
                        label = item.second
                    )
                })
            }
        }


        val list = listOf(
            "TRIUMPH",
            "Zivame",
            "Jockey",
            "ENAMOR",
            "Nykd by Nykaa",
            "Amante",
            "Clovia",
            "SHYAWAY",
            "MARKS & SPENCER",
            "VAN HEUSEN",
            "OTHERS"
        )

        if (state.value.fitBrand == null) {
            val brand = list.map {
                CurrentBrand(
                    brand = it,
                )
            }
            _state.update {
                it.copy(currentBrand = brand)
            }
        }


        val band = listOf("28", "30", "32", "34", "36", "38", "40", "42", "44", "46", "48")
        val cup = listOf("AA", "A", "B", "C", "D", "DD", "E", "F", "G")
        if (state.value.fitBraSize == null) {
            _state.update {
                it.copy(
                    cupSize = cup.map { cupSize ->
                        BraSize(size = cupSize)
                    },
                    bandSize = band.map { bandSize ->
                        BraSize(size = bandSize)
                    }
                )
            }
        }


        _state.update {
            it.copy(
                doYouSelect = listOf(
                    DoYouKnow(
                        title = "YES, I DO!",

                        ),
                    DoYouKnow(
                        title = "NO, I NEED TO GET MY RIGHT SIZE!",
                    ),
                )
            )
        }


        if (state.value.fitBandSize == null) {
            _state.update {
                it.copy(
                    bandFit = listOf(
                        CurrentFit(
                            image = R.drawable.band_fit_rides_up,
                            title = "RIDES UP",
                            description = "Bra band feels loose",
                            status = true
                        ),
                        CurrentFit(
                            image = R.drawable.band_fit_fits_well,
                            title = "FITS WELL",
                            description = "Bra band fits perfectly",
                        ),
                        CurrentFit(
                            image = R.drawable.band_fit_too_tight,
                            title = "TOO TIGHT",
                            description = "Bra band feels tight",
                        ),

                        )
                )
            }
        }

        if (state.value.fitCupSize == null) {
            _state.update {
                it.copy(
                    cupFit = listOf(
                        CurrentFit(
                            image = R.drawable.cup_fit_loose,
                            title = "LOOSE",
                            description = "Bra cup feels loose",
                            status = true
                        ),
                        CurrentFit(
                            image = R.drawable.cup_fit_fits_well,
                            title = "FITS WELL",
                            description = "Bra cup fits perfectly",
                        ),
                        CurrentFit(
                            image = R.drawable.cup_fit_too_tight,
                            title = "TOO TIGHT",
                            description = "Bra cup feels tight",
                        ),
                    )
                )
            }
        }


        if (state.value.fitHookSize == null) {
            _state.update {
                it.copy(
                    hookFit = listOf(
                        CurrentFit(
                            image = R.drawable.hook_fit_loose,
                            title = "LOOSEST",
                            description = "Bra fits in the first hook",
                            status = true
                        ),
                        CurrentFit(
                            image = R.drawable.hook_fit_middle,
                            title = "MIDDLE",
                            description = "Bra fits in the middle hook",
                        ),
                        CurrentFit(
                            image = R.drawable.hook_fit_tight,
                            title = "TIGHTEST",
                            description = "Bra fits in the last hook",
                        ),
                    )
                )
            }
        }


        if (state.value.fitStrapSize == null) {
            _state.update {
                it.copy(
                    strapFit = listOf(
                        CurrentFit(
                            image = R.drawable.strap_fit_falls_off,
                            title = "FALLS OFF",
                            description = "Shoulder straps falls off",
                            status = true
                        ),
                        CurrentFit(
                            image = R.drawable.strap_fit_on_place,
                            title = "ON PLACE",
                            description = "Shoulder straps fits perfectly",
                        ),
                        CurrentFit(
                            image = R.drawable.strap_fit_diggs_in,
                            title = "DIGS IN",
                            description = "Shoulder straps too tight",
                        ),
                    )
                )
            }
        }



        if (state.value.fitBustFall == null) {
            _state.update {
                it.copy(
                    bustFallType = listOf(
                        CurrentFit(
                            image = R.drawable.bust_fall_firm,
                            title = "FIRM",
                            description = "Breast is firm",
                        ),
                        CurrentFit(
                            image = R.drawable.bust_fall_saggy,
                            title = "SAGGY",
                            description = "Breast is saggy",
                        ),
                    )
                )
            }
        }

        if (state.value.fitBustShape == null) {
            _state.update {
                it.copy(
                    bustShapeType = listOf(
                        CurrentFit(
                            image = R.drawable.bust_shape_shallow,
                            title = "SHALLOW",
                            description = "Breast is shallow",
                        ),
                        CurrentFit(
                            image = R.drawable.bust_shape_ample,
                            title = "AMPLE",
                            description = "Breast is ample",
                        ),
                    )
                )
            }
        }

        if (state.value.fitBustPlacement == null)
            _state.update {
                it.copy(
                    bustPlacementType = listOf(
                        CurrentFit(
                            image = R.drawable.bust_placement_space,
                            title = "SPACED",
                            description = "Breasts are spaced",
                        ),
                        CurrentFit(
                            image = R.drawable.bust_placement_centered,
                            title = "CENTERED",
                            description = "Breasts are centered",
                        ),
                    )
                )
            }

    }


    fun resetDoYouKnowSelect(state: List<DoYouKnow>, type: String) {
        when (type) {
            "hood" -> {
                _state.update { it ->
                    it.copy(womenHood = state, fitWomanHood = state.find { it.status }?.title)
                }
            }

            "menstrual" -> {
                _state.update { it ->
                    it.copy(menstrualCycle = state, fitMenstrual = state.find { it.status }?.title)
                }
            }

            else -> {
                _state.update { it ->
                    it.copy(doYouSelect = state, fitYouKnow = state.find { it.status }?.title)
                }
            }
        }
    }

    fun getTheCurrentFit(value: String, type: SelectedFit) {
        when (type) {
            SelectedFit.BandFit -> {
                _state.update {
                    it.copy(fitBandSize = value, bandFit = state.value.bandFit?.map { fit ->
                        if (fit.title == value) {
                            fit.copy(status = true)
                        } else {
                            fit.copy(status = false)
                        }
                    })
                }
            }

            SelectedFit.CupFit -> {
                _state.update {
                    it.copy(fitCupSize = value, cupFit = state.value.cupFit.map { fit ->
                        if (fit.title == value) {
                            fit.copy(status = true)
                        } else {
                            fit.copy(status = false)
                        }
                    })
                }
            }

            SelectedFit.HookFit -> {
                _state.update {
                    it.copy(fitHookSize = value, hookFit = state.value.hookFit.map { fit ->
                        if (fit.title == value) {
                            fit.copy(status = true)
                        } else {
                            fit.copy(status = false)
                        }
                    })
                }
            }

            SelectedFit.StrapFit -> {
                _state.update {
                    it.copy(fitStrapSize = value, strapFit = state.value.strapFit.map { fit ->
                        if (fit.title == value) {
                            fit.copy(status = true)
                        } else {
                            fit.copy(status = false)
                        }
                    })
                }
            }

            SelectedFit.BustFallType -> {
                _state.update {
                    it.copy(
                        fitBustFall = value,
                        bustFallType = state.value.bustFallType.map { fit ->
                            if (fit.title == value) {
                                fit.copy(status = true)
                            } else {
                                fit.copy(status = false)
                            }
                        })
                }
            }

            SelectedFit.BustShapeType -> {
                _state.update {
                    it.copy(
                        fitBustShape = value,
                        bustShapeType = state.value.bustShapeType.map { fit ->
                            if (fit.title == value) {
                                fit.copy(status = true)
                            } else {
                                fit.copy(status = false)
                            }
                        })
                }
            }

            SelectedFit.PlacementType -> {
                Log.d("Result!!", "calculation: ${state.value.fitFinalSize}")
                _state.update {
                    it.copy(
                        fitBustPlacement = value,
                        bustPlacementType = state.value.bustPlacementType.map { fit ->
                            if (fit.title == value) {
                                fit.copy(status = true)
                            } else {
                                fit.copy(status = false)
                            }
                        })
                }
            }
        }
    }

    fun braSizeCalculation() {
        Log.d("Result!!", "calculation: ${state.value.fitFinalSize}")
        val band = state.value.bandSize.find { it.status }?.size
        val cup = state.value.cupSize.find { it.status }?.size
        when (state.value.fitBandSize) {
            "RIDES UP" -> {
                when (state.value.fitHookSize) {
                    "TIGHTEST" -> {
                        if (band != null && band != "20") {
                            _state.update {
                                it.copy(bandPosition = state.value.bandPosition?.plus(1))
                            }
                            _state.update {
                                it.copy(bustPosition = state.value.bustPosition?.minus(1))
                            }
                            when (state.value.fitCupSize) {
                                "LOOSE" -> {
                                    _state.update {
                                        it.copy(bustPosition = state.value.bustPosition?.minus(1))
                                    }
                                }

                                "TOO TIGHT" -> {
                                    _state.update {
                                        it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                    }
                                }

                            }
                        } else {
                            Log.d("TAG", "Provided size is invalid")
                        }
                    }

                    "MIDDLE" -> {
                        when (state.value.fitCupSize) {
                            "LOOSE" -> {
                                if (cup != "AA") {
                                    _state.update {
                                        it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                    }
                                } else {
                                    Log.d("TAG", "Provided size is invalid")
                                }
                            }

                            "TOO TIGHT" -> {
                                _state.update {
                                    it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                }
                            }

                        }
                    }

                    "LOOSEST" -> {
                        when (state.value.fitCupSize) {
                            "LOOSE" -> {
                                if (cup != "AA") {
                                    _state.update {
                                        it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                    }
                                } else {
                                    Log.d("TAG", "Provided size is invalid")
                                }
                            }

                            "TOO TIGHT" -> {
                                _state.update {
                                    it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                }
                            }

                        }
                    }
                }
            }

            "FITS WELL" -> {
                when (state.value.fitCupSize) {
                    "LOOSE" -> {
                        if (cup != "AA") {
                            _state.update {
                                it.copy(bustPosition = state.value.bustPosition?.plus(1))
                            }
                        } else {
                            Log.d("TAG", "Provided size is invalid")
                        }
                    }

                    "TOO TIGHT" -> {
                        _state.update {
                            it.copy(bustPosition = state.value.bustPosition?.plus(1))
                        }
                    }

                }
            }

            "TOO TIGHT" -> {
                when (state.value.fitHookSize) {
                    "LOOSEST" -> {
                        _state.update {
                            it.copy(bandPosition = state.value.bandPosition?.plus(1))
                        }
                        _state.update {
                            it.copy(bustPosition = state.value.bustPosition?.minus(1))
                        }

                        when (state.value.fitCupSize) {
                            "LOOSE" -> {
                                if (cup != "AA") {
                                    _state.update {
                                        it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                    }
                                } else {
                                    Log.d("TAG", "Provided size is invalid")
                                }
                            }

                            "TOO TIGHT" -> {
                                _state.update {
                                    it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                }
                            }
                        }
                    }

                    "MIDDLE" -> {
                        when (state.value.fitCupSize) {
                            "LOOSE" -> {
                                if (cup != "AA") {
                                    _state.update {
                                        it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                    }
                                } else {
                                    Log.d("TAG", "Provided size is invalid")
                                }
                            }

                            "TOO TIGHT" -> {
                                _state.update {
                                    it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                }
                            }

                        }
                    }

                    "TIGHTEST" -> {
                        when (state.value.fitCupSize) {
                            "LOOSE" -> {
                                if (cup != "AA") {
                                    _state.update {
                                        it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                    }
                                } else {
                                    Log.d("TAG", "Provided size is invalid")
                                }
                            }

                            "TOO TIGHT" -> {
                                _state.update {
                                    it.copy(bustPosition = state.value.bustPosition?.plus(1))
                                }
                            }
                        }
                    }
                }
            }
        }
        calculation()
    }

    fun calculation() {
        val finalBand = state.value.bandSize.getOrNull(state.value.bandPosition ?: 0)?.size
        val finalBust = state.value.cupSize.getOrNull(state.value.bustPosition ?: 0)?.size
        _state.update {
            it.copy(fitFinalSize = finalBand + finalBust)
        }
    }


    fun resetCurrentBrand(state: List<CurrentBrand>) {
        _state.update { it ->
            it.copy(currentBrand = state, fitBrand = state.find { it.status }?.brand)
        }
    }

    fun resetBandSize(state: List<BraSize>, i: Int) {
        _state.update {
            it.copy(bandSize = state, bandPosition = i)
        }
    }

    fun resetCupSize(state: List<BraSize>, i: Int) {
        _state.update {
            it.copy(cupSize = state, bustPosition = i)
        }
    }

    fun switchCurrentScreen(screen: YesIdoOnBoard) {
        _state.update {
            it.copy(currentOnBoard = screen)
        }
    }

    fun knowCurrentBraSize() {
        _state.update { it ->
            it.copy(fitBraSize = state.value.cupSize.find { it.status }?.size + state.value.bandSize.find { it.status }?.size)
        }
    }

    fun onOtherBrandChange(item: String) {
        _state.update {
            it.copy(
                otherBrand = if (isCharValid(item)) item else "",
                errorOtherBrand = if (isCharValid(item)) null else "Invalid input"
            )
        }
    }

    fun onInputBand(item: String) {
        _state.update {
            it.copy(
                onInputBand = item,
            )
        }
    }

    fun onInputBust(item: String) {
        _state.update {
            it.copy(
                onInputBust = item,
            )
        }
    }

    fun onInputHip(item: String) {
        _state.update {
            it.copy(
                onInputHip = item,
            )
        }
    }


    fun setError(error: String? = null) {
        _state.update {
            it.copy(error = error)
        }
    }

    fun setCardTop(screen: YesIdoOnBoard) {
        _state.update {
            it.copy(
                cardHead = when (screen) {
                    YesIdoOnBoard.WhichBRAND -> Triple(
                        "Which brand are you currently buying from?",
                        "This helps us understand more about your preference",
                        "Don't worry, we'll help you find your perfect size!"
                    )

                    YesIdoOnBoard.WhichSize -> Triple(
                        "Which brand are you currently buying from?",
                        "This helps us understand more about your preference",
                        "Don't worry, we'll help you find your perfect size!"
                    )

                    YesIdoOnBoard.DoYoKnow -> Triple(
                        "Do you know your current size?",
                        "This helps us recommend right and perfect size for you",
                        "Don't worry, we'll help you find your perfect size!"
                    )

                    YesIdoOnBoard.BandFit -> {
                        Triple(
                            "How does the band fit?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.CupFit -> {
                        Triple(
                            "How do your cups fit?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.HookFit -> {
                        Triple(
                            "In which hook does your bra fit?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.StrapFit -> {
                        Triple(
                            "How does the strap fit?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.ShoulderType -> {
                        Triple(
                            "How does the shoulder fit?",
                            "This helps us recommend right and perfect size for you",
                            "Don't worry, we'll help you find your perfect size!"
                        )
                    }

                    YesIdoOnBoard.BustFallType -> {
                        Triple(
                            "How do you describe your breast fall?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.BustShapeType -> {
                        Triple(
                            "How do you describe your breast shape?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.PlacementType -> {
                        Triple(
                            "How close your breasts are?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.WomenHood -> {
                        Triple(
                            "Which phase of womanhood are you in?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.MenstrualCycle -> {
                        Triple(
                            "Which phase of menstrual cycle are you in?",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.Final -> {
                        Triple(
                            "",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.ChartCalculationBra -> {
                        Triple(
                            "",
                            "",
                            ""
                        )
                    }

                    YesIdoOnBoard.ChartCalculationHip -> {
                        Triple(
                            "",
                            "",
                            ""
                        )
                    }
                }
            )
        }
    }
}

private fun isCharValid(state: String): Boolean {
    val regex = Regex("^[A-Za-z]+$")
    return regex.matches(state)
}

private fun isOnBoardData(): List<OnBoardData> {
    return listOf(
        OnBoardData(
            R.drawable.cup_fit_fits_well,
            "how to well if my bra cup fit correctly?",
            "Whether you're dealing with cup spillage of breast overflow, don't worry there is help!"
        ),
        OnBoardData(
            R.drawable.strap_fit_diggs_in,
            "is a adjusting adjusting my bra straps a real solution",
            "Your bra straps should carry no more than 10% of your breast weight? How much does your bear?"
        ),
        OnBoardData(
            R.drawable.band_fit_fits_well,
            "is your bra band doing its job properly?",
            "Did you know your bra gets a whopping 80% support from the bra band? Are you getting the support you deserve? Find out now on our app!"
        ),

    )
}