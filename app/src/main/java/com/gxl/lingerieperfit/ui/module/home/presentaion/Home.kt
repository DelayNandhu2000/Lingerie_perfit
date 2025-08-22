package com.gxl.lingerieperfit.ui.module.home.presentaion

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import com.gxl.lingerieperfit.ui.module.home.data.HomeState
import com.gxl.lingerieperfit.ui.module.home.presentaion.viewModel.HomeViewModel
import com.gxl.lingerieperfit.ui.theme.CardTop
import com.gxl.lingerieperfit.ui.theme.FinalButtons
import com.gxl.lingerieperfit.ui.theme.FitVideoPlay
import com.gxl.lingerieperfit.ui.theme.HomeTopBar
import com.gxl.lingerieperfit.ui.theme.NavigationBtn
import com.gxl.lingerieperfit.ui.theme.ShyawayTextMedium
import org.koin.compose.viewmodel.koinViewModel
import androidx.core.net.toUri

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.currentOnBoard) {
        viewModel.setCardTop(state.currentOnBoard)
        viewModel.getSelecters(state.currentOnBoard)
        viewModel.setTopBarInc()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeTopBar(state){
                fitBackNavigationController(state, viewModel)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(state.currentOnBoard == YesIdoOnBoard.Final) {
                        FitVideoPlay{
                            val intent = Intent(Intent.ACTION_VIEW,
                                "https://m.youtube.com/watch?v=uyaFHAWd1S8".toUri())
                            intent.setPackage("com.google.android.youtube")
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                context.startActivity(Intent(Intent.ACTION_VIEW,
                                    "https://m.youtube.com/watch?v=uyaFHAWd1S8".toUri()))
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    state.cardHead?.let {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                        ) {
                            Column {
                                FitScreens(state, viewModel)
                                if (state.cupSize.any { it.status } && state.bandSize.any { it.status } && state.currentOnBoard == YesIdoOnBoard.WhichSize) {
                                    YourCurrentBra(state)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        state.cardHead?.let {
                            ShyawayTextMedium(
                                state.cardHead?.third.toString(),
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }
        }

        if (state.womenHood.isNotEmpty()) {
            if(state.currentOnBoard != YesIdoOnBoard.Final) {
                NavigationBtn(
                    currentVisible = state.currentOnBoard,
                    clickForward = {
                        fitNavigationController(state, context, viewModel)
                        // navController.navigate("brand")
                    },
                    clickBackward = {
                        fitBackNavigationController(state, viewModel)
                    }
                )
            }else {
              FinalButtons(clickShop = {
                  viewModel.resetAllFit()
                  val intent = Intent(Intent.ACTION_VIEW,
                      "https://www.shyaway.com/bra-online/${state.fitFinalSize}-bra/".toUri())
                  intent.setPackage("gxl.shyawaynew")
                  try {
                      context.startActivity(intent)
                  } catch (e: Exception) {
                      context.startActivity(Intent(Intent.ACTION_VIEW,
                          "https://www.shyaway.com/bra-online/${state.fitFinalSize}-bra/".toUri()))
                  }
              }, clickStart = {
                  viewModel.resetAllFit()
                  viewModel.switchCurrentScreen(YesIdoOnBoard.DoYoKnow)
              })
            }

        }

        BackHandler {

        }

//        state.error?.let {
//            CustomToaster(false, it)
//            LaunchedEffect(Unit) {
//                delay(2000)
//                viewModel.setError()
//            }
//
//        }
    }
}


@Composable
fun FitScreens(state: HomeState, viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(state.currentOnBoard !in setOf(YesIdoOnBoard.ChartCalculationBra, YesIdoOnBoard.ChartCalculationHip, YesIdoOnBoard.Final)) {
            state.cardHead?.first?.let {
                CardTop(state.cardHead)
            }
        }
//        Spacer(modifier = Modifier.height(16.dp))
        when (state.currentOnBoard) {
            YesIdoOnBoard.WhichBRAND -> {
                CurrentBrand(viewModel, state)
            }

            YesIdoOnBoard.WhichSize -> {
                CurrentBraSize(viewModel, state)
            }

            YesIdoOnBoard.DoYoKnow -> {
                if (state.doYouSelect.isNotEmpty()) {
                    DoYouKnow(viewModel, state)
                }
            }

            YesIdoOnBoard.BandFit -> {
                FitScreen(state.bandFit ?: emptyList(), onFitSelected = {bandFit->
                    viewModel.getTheCurrentFit(bandFit.title, SelectedFit.BandFit)
                })
                Log.d("FiFit", "FitScreens: ${state.bandFit}")
            }

            YesIdoOnBoard.CupFit -> {
                FitScreen(state.cupFit, onFitSelected = {cupFit->
                    viewModel.getTheCurrentFit(cupFit.title,SelectedFit.CupFit)
                })
            }

            YesIdoOnBoard.HookFit -> {
                FitScreen(state.hookFit, onFitSelected = {hookFit->
                    viewModel.getTheCurrentFit(hookFit.title,SelectedFit.HookFit)
                })

            }

            YesIdoOnBoard.StrapFit -> {
                FitScreen(state.strapFit, onFitSelected = {strapFit->
                    viewModel.getTheCurrentFit(strapFit.title,SelectedFit.StrapFit)
                })
            }

            YesIdoOnBoard.ShoulderType -> {}
            YesIdoOnBoard.BustFallType -> {
                FitScreen (state.bustFallType, onFitSelected = {bustFallFit->
                    Log.d("ResultPosition", "calculation: $bustFallFit")
                    viewModel.getTheCurrentFit(bustFallFit.title,SelectedFit.BustFallType)
                })
            }

            YesIdoOnBoard.BustShapeType -> {
                FitScreen(state.bustShapeType, onFitSelected = {bustShapeFit->
                    viewModel.getTheCurrentFit(bustShapeFit.title,SelectedFit.BustShapeType)
                })
            }

            YesIdoOnBoard.PlacementType -> {
                FitScreen(state.bustPlacementType, onFitSelected = {placementFit->
                    viewModel.getTheCurrentFit(placementFit.title,SelectedFit.PlacementType)
                })
                Log.d("ResultPosition", "calculation: ${state.bustPosition}")
            }

            YesIdoOnBoard.WomenHood -> {
                DoYouKnow(viewModel, state)
            }

            YesIdoOnBoard.MenstrualCycle -> {
                DoYouKnow(viewModel, state)
            }

            YesIdoOnBoard.Final -> {
                FinalScreen(viewModel,state)
            }

            YesIdoOnBoard.ChartCalculationBra -> {
                LaunchedEffect(Unit) {
                    if(state.chartDataBra == null)
                    viewModel.getSizeChart("bra")
                }
                SetSizeCalculator(state,viewModel)
            }

            YesIdoOnBoard.ChartCalculationHip -> {
                LaunchedEffect(Unit) {
                    if(state.chartDataHip == null)
                    viewModel.getSizeChart("panty")
                }
                Log.d("ResultPosition", "calculation: ${state.bustPosition}")
                SetSizeCalculator(state,viewModel)
            }
        }
    }
}

fun fitNavigationController(state: HomeState, context: Context, viewModel: HomeViewModel) {
    when (state.currentOnBoard) {
        YesIdoOnBoard.WhichBRAND -> {
            if (state.currentBrand.any { it.status }) {
                viewModel.switchCurrentScreen(YesIdoOnBoard.WhichSize)
            } else {
                Toast.makeText(context, "Please select the first", Toast.LENGTH_SHORT).show()
            }
        }

        YesIdoOnBoard.WhichSize -> {
            if (state.cupSize.any { it.status } && state.bandSize.any { it.status }) {
                viewModel.switchCurrentScreen(YesIdoOnBoard.BandFit)
            } else {
                Toast.makeText(context, "Please select the first", Toast.LENGTH_SHORT).show()
            }
        }

        YesIdoOnBoard.DoYoKnow -> {
            when(state.doYouSelect.find { it.status }?.title){
                "YES, I DO!" -> viewModel.switchCurrentScreen(YesIdoOnBoard.WhichBRAND)
                "NO, I NEED TO GET MY RIGHT SIZE!" -> viewModel.switchCurrentScreen(YesIdoOnBoard.ChartCalculationBra)
                else -> Toast.makeText(context, "Please select the first", Toast.LENGTH_SHORT).show()
            }
        }

        YesIdoOnBoard.BandFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.CupFit)
            // viewModel.switchCurrentScreen(YesIdoOnBoard.DoYoKnow)
        }

        YesIdoOnBoard.CupFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.HookFit)
        }

        YesIdoOnBoard.HookFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.StrapFit)
        }

        YesIdoOnBoard.StrapFit -> {
            viewModel.braSizeCalculation()
            viewModel.switchCurrentScreen(YesIdoOnBoard.ChartCalculationHip)
        }

        YesIdoOnBoard.ShoulderType -> {}
        YesIdoOnBoard.BustFallType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.BustShapeType)
        }

        YesIdoOnBoard.BustShapeType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.PlacementType)
        }

        YesIdoOnBoard.PlacementType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.WomenHood)
        }
        YesIdoOnBoard.WomenHood -> {
            if(state.womenHood.any { it.status })
            viewModel.switchCurrentScreen(YesIdoOnBoard.MenstrualCycle)
            else Toast.makeText(context, "Please select the first", Toast.LENGTH_SHORT).show()

        }

        YesIdoOnBoard.MenstrualCycle -> {
            if(state.menstrualCycle.any { it.status })
            viewModel.switchCurrentScreen(YesIdoOnBoard.DoYoKnow)
            else Toast.makeText(context, "Please select the first", Toast.LENGTH_SHORT).show()
        }

        YesIdoOnBoard.Final -> {}
        YesIdoOnBoard.ChartCalculationBra -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.ChartCalculationHip)

        }

        YesIdoOnBoard.ChartCalculationHip -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.Final)
        }
    }
}

fun fitBackNavigationController(state: HomeState, viewModel: HomeViewModel) {
    when (state.currentOnBoard) {
        YesIdoOnBoard.WhichBRAND -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.DoYoKnow)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.WhichBRAND)
        }

        YesIdoOnBoard.WhichSize -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.WhichBRAND)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.WhichSize)
        }

        YesIdoOnBoard.BandFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.WhichSize)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.BandFit)
        }

        YesIdoOnBoard.CupFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.BandFit)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.CupFit)
        }

        YesIdoOnBoard.HookFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.CupFit)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.HookFit)
        }

        YesIdoOnBoard.StrapFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.HookFit)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.StrapFit)
        }

        YesIdoOnBoard.ShoulderType -> {

        }

        YesIdoOnBoard.BustFallType -> {
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.BustFallType)
        }

        YesIdoOnBoard.BustShapeType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.BustFallType)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.BustShapeType)
        }

        YesIdoOnBoard.PlacementType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.BustShapeType)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.PlacementType)
        }

        YesIdoOnBoard.DoYoKnow -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.MenstrualCycle)
        }

        YesIdoOnBoard.WomenHood -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.PlacementType)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.WomenHood)
        }

        YesIdoOnBoard.MenstrualCycle -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.WomenHood)
            viewModel.resetFit(yesIdoOnBoard = YesIdoOnBoard.MenstrualCycle)
        }

        YesIdoOnBoard.Final -> {

        }

        YesIdoOnBoard.ChartCalculationBra -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.DoYoKnow)
        }

        YesIdoOnBoard.ChartCalculationHip -> {
            if(state.fitYouKnow != "YES, I DO!") {
                viewModel.switchCurrentScreen(YesIdoOnBoard.ChartCalculationBra)
            }else{
                viewModel.switchCurrentScreen(YesIdoOnBoard.StrapFit)
            }
        }
    }
}


enum class SelectedFit {
    BandFit, CupFit, HookFit, StrapFit ,BustFallType, BustShapeType, PlacementType
}

enum class YesIdoOnBoard {
    WomenHood, MenstrualCycle, ChartCalculationBra, ChartCalculationHip,WhichBRAND, WhichSize, DoYoKnow, BandFit, CupFit, HookFit, StrapFit, ShoulderType, BustFallType, BustShapeType, PlacementType,Final
}