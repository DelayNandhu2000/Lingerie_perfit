package com.gxl.lingerieperfit.ui.module.home.presentaion.viewModel


import androidx.lifecycle.ViewModel
import com.gxl.lingerieperfit.R
import com.gxl.lingerieperfit.ui.module.home.data.BraSize
import com.gxl.lingerieperfit.ui.module.home.data.CurrentBrand
import com.gxl.lingerieperfit.ui.module.home.data.CurrentFit
import com.gxl.lingerieperfit.ui.module.home.data.DoYouKnow
import com.gxl.lingerieperfit.ui.module.home.data.HomeState
import com.gxl.lingerieperfit.ui.module.home.presentaion.YesIdoOnBoard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel() : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    fun getSelecters(currentOnBoard: YesIdoOnBoard) {
        when (currentOnBoard) {

            YesIdoOnBoard.WomenHood -> {
              val hood = listOf(
                  "TEENAGE",
                  "PRIME ADULT",
                  "MATERNITY / NURSING",
                  "PRE-MENOPAUSE / POST-MENOPAUSE",
                  "SENIORS",
              )
               _state.update {
                   it.copy(womenHood = hood.map {item-> DoYouKnow(title = item) })
               }
            }
            YesIdoOnBoard.MenstrualCycle -> {

                val menstrual = listOf(
                    "15 DAYS BEFORE PERIODS",
                    "PERIODS",
                    "15 DAYS AFTER PERIODS",
                    "PCOD,IRREGULAR PERIODS","PCOS",
                    "NO PERIODS",
                )
                _state.update {
                    it.copy(menstrualCycle = menstrual.map {item-> DoYouKnow(title = item) })
                }
            }
            YesIdoOnBoard.WhichBRAND -> {
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
                val brand = list.map {
                    CurrentBrand(
                        brand = it,
                    )
                }
                _state.update {
                    it.copy(currentBrand = brand)
                }

            }

            YesIdoOnBoard.WhichSize -> {
                val band = listOf("28", "30", "32", "34", "36", "38", "40", "42", "44", "46", "48")
                val cup = listOf("AA", "A", "B", "C", "D", "DD", "E", "F", "G")
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


            YesIdoOnBoard.DoYoKnow -> {
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
            }

            YesIdoOnBoard.BandFit -> {
                _state.update {
                    it.copy(
                        bandFit = listOf(
                            CurrentFit(
                                image = R.drawable.band_fit_rides_up,
                                title = "RIDES UP",
                                description = "Bra band feels loose",
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
            YesIdoOnBoard.CupFit -> {
                _state.update {
                    it.copy(
                        cupFit = listOf(
                            CurrentFit(
                                image = R.drawable.cup_fit_loose,
                                title = "LOOSE",
                                description = "Bra cup feels loose",
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
            YesIdoOnBoard.HookFit -> {
                _state.update {
                    it.copy(
                        hookFit = listOf(
                            CurrentFit(
                                image = R.drawable.hook_fit_loose,
                                title = "LOOSEST",
                                description = "Bra fits in the first hook",
                            ),
                            CurrentFit(
                                image = R.drawable.hook_fit_middle,
                                title = "MIDDLE",
                                description = "Bra fits in the middle hook",
                            ),
                            CurrentFit(
                                image = R.drawable.hook_fit_tight,
                                title = "TIGHEST",
                                description = "Bra fits in the last hook",
                            ),
                        )
                    )
                }
            }
            YesIdoOnBoard.StrapFit -> {
                _state.update {
                    it.copy(
                        strapFit = listOf(
                            CurrentFit(
                                image = R.drawable.strap_fit_falls_off,
                                title = "FALLS OFF",
                                description = "Shoulder straps falls off",
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
            YesIdoOnBoard.ShoulderType -> {}
            YesIdoOnBoard.BustFallType -> {
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
            YesIdoOnBoard.BustShapeType -> {
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
            YesIdoOnBoard.PlacementType -> {
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
        }
    }

    fun resetDoYouKnowSelect(state: List<DoYouKnow>, type: String) {
        when(type){
          "hood" ->{
              _state.update {
                  it.copy(womenHood = state)
              }
          }
          "menstrual" ->{
              _state.update {
                  it.copy(menstrualCycle = state)
              }
          }
          else ->{
              _state.update {
                  it.copy(doYouSelect = state)
              }
          }
        }
    }

    fun resetCurrentBrand(state: List<CurrentBrand>) {
        _state.update {
            it.copy(currentBrand = state)
        }
    }

    fun resetBandSize(state: List<BraSize>) {
        _state.update {
            it.copy(bandSize = state)
        }
    }

    fun resetCupSize(state: List<BraSize>) {
        _state.update {
            it.copy(cupSize = state)
        }
    }

    fun switchCurrentScreen(screen: YesIdoOnBoard) {
        _state.update {
            it.copy(currentOnBoard = screen)
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

                    YesIdoOnBoard.WomenHood ->{
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
                }
            )
        }
    }

}

private fun isCharValid(state: String): Boolean {
    val regex = Regex("^[A-Za-z]+$")
    return regex.matches(state)
}