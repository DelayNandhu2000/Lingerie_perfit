package com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion

import android.content.Context
import android.widget.Toast
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
import androidx.navigation.NavHostController
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.HomeState
import com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion.viewModel.HomeViewModel
import com.genxlead.lingeriePer_FitApp.ui.theme.CardTop
import com.genxlead.lingeriePer_FitApp.ui.theme.NavigationBtn
import com.genxlead.lingeriePer_FitApp.ui.theme.ShyawayTextMedium
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = koinViewModel()) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.currentOnBoard) {
        viewModel.setCardTop(state.currentOnBoard)
        viewModel.getSelecters(state.currentOnBoard)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
        state.cardHead?.let {
            CardTop(state.cardHead)
        }
        Spacer(modifier = Modifier.height(16.dp))
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
                FitScreen(state.bandFit ?: emptyList(), onFitSelected = {})
            }

            YesIdoOnBoard.CupFit -> {
                FitScreen(state.cupFit, onFitSelected = {})
            }

            YesIdoOnBoard.HookFit -> {
                FitScreen(state.hookFit, onFitSelected = {})
            }

            YesIdoOnBoard.StrapFit -> {
                FitScreen(state.strapFit, onFitSelected = {})
            }

            YesIdoOnBoard.ShoulderType -> {}
            YesIdoOnBoard.BustFallType -> {
                BustFitScreen(state.bustFallType, onFitSelected = {})
            }

            YesIdoOnBoard.BustShapeType -> {
                BustFitScreen(state.bustShapeType, onFitSelected = {})
            }

            YesIdoOnBoard.PlacementType -> {
                BustFitScreen(state.bustPlacementType, onFitSelected = {})
            }

            YesIdoOnBoard.WomenHood -> {
                DoYouKnow(viewModel, state)
            }
            YesIdoOnBoard.MenstrualCycle -> {
                DoYouKnow(viewModel, state)
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
            if (state.doYouSelect.find { it.status }?.title == "YES, I DO!") {
                viewModel.switchCurrentScreen(YesIdoOnBoard.WhichBRAND)
            } else {
                Toast.makeText(context, "toast", Toast.LENGTH_SHORT).show()
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
            viewModel.switchCurrentScreen(YesIdoOnBoard.BustFallType)
        }

        YesIdoOnBoard.ShoulderType -> {}
        YesIdoOnBoard.BustFallType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.BustShapeType)
        }

        YesIdoOnBoard.BustShapeType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.PlacementType)
        }

        YesIdoOnBoard.PlacementType -> {}
        YesIdoOnBoard.WomenHood -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.MenstrualCycle)
        }
        YesIdoOnBoard.MenstrualCycle -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.DoYoKnow)
        }
    }
}

fun fitBackNavigationController(state: HomeState, viewModel: HomeViewModel) {
    when (state.currentOnBoard) {
        YesIdoOnBoard.WhichBRAND -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.DoYoKnow)
        }

        YesIdoOnBoard.WhichSize -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.WhichBRAND)
        }

        YesIdoOnBoard.BandFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.WhichSize)
        }

        YesIdoOnBoard.CupFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.BandFit)
        }

        YesIdoOnBoard.HookFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.CupFit)
        }

        YesIdoOnBoard.StrapFit -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.HookFit)
        }

        YesIdoOnBoard.ShoulderType -> {

        }

        YesIdoOnBoard.BustFallType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.StrapFit)
        }

        YesIdoOnBoard.BustShapeType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.BustFallType)
        }

        YesIdoOnBoard.PlacementType -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.BustShapeType)
        }

        YesIdoOnBoard.DoYoKnow -> {
            viewModel.switchCurrentScreen(YesIdoOnBoard.MenstrualCycle)
        }
        YesIdoOnBoard.WomenHood -> {

        }
        YesIdoOnBoard.MenstrualCycle ->{
            viewModel.switchCurrentScreen(YesIdoOnBoard.WomenHood)
        }
    }
}

enum class YesIdoOnBoard {
    WomenHood,MenstrualCycle,WhichBRAND, WhichSize, DoYoKnow, BandFit, CupFit, HookFit, StrapFit, ShoulderType, BustFallType, BustShapeType, PlacementType
}