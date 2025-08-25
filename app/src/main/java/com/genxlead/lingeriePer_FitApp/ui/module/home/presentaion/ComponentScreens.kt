package com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.CurrentFit
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.HomeState
import com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion.viewModel.HomeViewModel
import com.genxlead.lingeriePer_FitApp.ui.theme.FitSlider
import com.genxlead.lingeriePer_FitApp.ui.theme.OutlinedEditText
import com.genxlead.lingeriePer_FitApp.ui.theme.SelectionBox
import com.genxlead.lingeriePer_FitApp.ui.theme.ShyawayTextMedium
import com.genxlead.lingeriePer_FitApp.ui.theme.ShyawayTextSmall
import com.genxlead.lingeriePer_FitApp.ui.theme.ViewBraSize
import com.genxlead.lingeriePer_FitApp.ui.theme.ViewBrand

@Composable
fun DoYouKnow(viewModel: HomeViewModel, state: HomeState) {
    when(state.currentOnBoard){
        YesIdoOnBoard.WomenHood -> {
            state.womenHood.forEachIndexed { position, it ->
                val isSelected = it.status
                SelectionBox(it) {
                    val stateChange = state.womenHood.mapIndexed { index, s ->
                        s.copy(status = if (index == position) !isSelected else false)
                    }
                    viewModel.resetDoYouKnowSelect(stateChange,"hood")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        YesIdoOnBoard.MenstrualCycle -> {
            state.menstrualCycle.forEachIndexed { position, it ->
                val isSelected = it.status
                SelectionBox(it) {
                    val stateChange = state.menstrualCycle.mapIndexed { index, s ->
                        s.copy(status = if (index == position) !isSelected else false)
                    }
                    viewModel.resetDoYouKnowSelect(stateChange,"menstrual")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        YesIdoOnBoard.DoYoKnow -> {
            state.doYouSelect.forEachIndexed { position, it ->
                val isSelected = it.status
                SelectionBox(it) {
                    val stateChange = state.doYouSelect.mapIndexed { index, s ->
                        s.copy(status = if (index == position) !isSelected else false)
                    }
                    viewModel.resetDoYouKnowSelect(stateChange,"doYou")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        YesIdoOnBoard.WhichBRAND -> {}
        YesIdoOnBoard.WhichSize -> {}
        YesIdoOnBoard.BandFit -> {}
        YesIdoOnBoard.CupFit -> {}
        YesIdoOnBoard.HookFit -> {}
        YesIdoOnBoard.StrapFit -> {}
        YesIdoOnBoard.ShoulderType -> {}
        YesIdoOnBoard.BustFallType -> {}
        YesIdoOnBoard.BustShapeType -> {}
        YesIdoOnBoard.PlacementType -> {}
    }
}

@Composable
fun CurrentBrand(viewModel: HomeViewModel, state: HomeState) {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.currentBrand.size) {
                ViewBrand(state.currentBrand[it]) {
                    val stateChange = state.currentBrand.mapIndexed { index, s ->
                        s.copy(status = if (it == index) !s.status else false)
                    }
                    viewModel.resetCurrentBrand(stateChange)
                }
            }
        }
        if (state.currentBrand.find { it.status }?.brand == "OTHERS") {
            OutlinedEditText(
                value = state.otherBrand.toString(),
                onValueChange = {
                    viewModel.onOtherBrandChange(it)
                },
                label = "",
                placeholder = "Please specify here",
                error = state.errorOtherBrand,
                keyboardType = KeyboardType.Text,
            )
        }
    }
}

@Composable
fun CurrentBraSize(viewModel: HomeViewModel, state: HomeState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShyawayTextSmall(
            "Bust Size",
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.error)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.bandSize.size) {
                ViewBraSize(state.bandSize[it]) {
                    val stateChange = state.bandSize.mapIndexed { index, s ->
                        s.copy(status = if (it == index) !s.status else false)
                    }
                    viewModel.resetBandSize(stateChange)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ShyawayTextSmall(
            "Bust Size",
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.error)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.cupSize.size) {
                ViewBraSize(state.cupSize[it]) {
                    val stateChange = state.cupSize.mapIndexed { index, s ->
                        s.copy(status = if (it == index) !s.status else false)
                    }
                    viewModel.resetCupSize(stateChange)
                }
            }
        }
    }
}


@Composable
fun FitScreen(
    fitList: List<CurrentFit>,
    onFitSelected: (CurrentFit) -> Unit
) {
    FitSlider(
        bandFitList = fitList,
        onSelectionChanged = { index ->
            onFitSelected(fitList[index])
        }
    )
}

@Composable
fun BustFitScreen(
    bustFitList: List<CurrentFit>,
    onFitSelected: (CurrentFit) -> Unit
) {
    FitSlider(
        bandFitList = bustFitList,
        onSelectionChanged = { index ->
            onFitSelected(bustFitList[index])
        }
    )
}


@Composable
fun YourCurrentBra(state: HomeState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )
            ),
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShyawayTextSmall(
                "Your Bra Size",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.error
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            val cup = state.cupSize.find { it.status }?.size
            val band = state.bandSize.find { it.status }?.size
            ShyawayTextMedium(
                "$band$cup",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        }
    }
}