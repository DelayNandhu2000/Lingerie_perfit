package com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
import coil.compose.AsyncImage
import com.gxl.lingerieperfit.R
import com.gxl.lingerieperfit.ui.module.home.data.CurrentFit
import com.gxl.lingerieperfit.ui.module.home.data.HomeState
import com.gxl.lingerieperfit.ui.module.home.presentaion.viewModel.HomeViewModel
import com.gxl.lingerieperfit.ui.theme.FitInputEdit
import com.gxl.lingerieperfit.ui.theme.FitSlider
import com.gxl.lingerieperfit.ui.theme.OutlinedEditText
import com.gxl.lingerieperfit.ui.theme.SelectionBox
import com.gxl.lingerieperfit.ui.theme.SelectionLabel
import com.gxl.lingerieperfit.ui.theme.SetSizeCalculatorShimmer
import com.gxl.lingerieperfit.ui.theme.ShyawayTextMedium
import com.gxl.lingerieperfit.ui.theme.ShyawayTextSmall
import com.gxl.lingerieperfit.ui.theme.ViewBraSize
import com.gxl.lingerieperfit.ui.theme.ViewBrand

@Composable
fun DoYouKnow(viewModel: HomeViewModel, state: HomeState) {
    when (state.currentOnBoard) {
        YesIdoOnBoard.WomenHood -> {
            state.womenHood.forEachIndexed { position, it ->
                val isSelected = it.status
                Column {
                    SelectionBox(it) {
                        val stateChange = state.womenHood.mapIndexed { index, s ->
                            s.copy(status = if (index == position) !isSelected else false)
                        }
                        viewModel.resetDoYouKnowSelect(stateChange, "hood")
                    }
                    if (it.status) {
                        Spacer(modifier = Modifier.height(8.dp))
                        SelectionLabel(it)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        YesIdoOnBoard.MenstrualCycle -> {
            state.menstrualCycle.forEachIndexed { position, it ->
                val isSelected = it.status
                Column {
                    SelectionBox(it) {
                        val stateChange = state.menstrualCycle.mapIndexed { index, s ->
                            s.copy(status = if (index == position) !isSelected else false)
                        }
                        viewModel.resetDoYouKnowSelect(stateChange, "menstrual")
                    }
                    if (it.status) {
                        Spacer(modifier = Modifier.height(8.dp))
                        SelectionLabel(it)
                    }
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
                    viewModel.resetDoYouKnowSelect(stateChange, "doYou")
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
        YesIdoOnBoard.Final -> {}
        YesIdoOnBoard.ChartCalculationBra -> {}
        YesIdoOnBoard.ChartCalculationHip -> {}
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

    LaunchedEffect(state.cupSize, state.bandSize) {
        if (state.cupSize.any { it.status } && state.bandSize.any { it.status }) {
            viewModel.knowCurrentBraSize()
        }
    }
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
                    viewModel.resetBandSize(stateChange, it)
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
                    viewModel.resetCupSize(stateChange, it)
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

@Composable
fun FinalScreen(viewModel: HomeViewModel, state: HomeState) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            FinalTop()
            FinalResultBox(state.fitFinalSize)
        }
    }
}


@Composable
fun FinalTop() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.perfit_logo),
            contentDescription = null,
            modifier = Modifier.padding(top = 12.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ShyawayTextMedium(
            "LINGERIE - PERFIT",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        ShyawayTextMedium(
            "THE PER-FIT SIZE",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        ShyawayTextMedium(
            "Final Result",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        )
    }
}

@Composable
fun FinalResultBox(fitFinalSize: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth().padding(start = 36.dp , end = 36.dp , top = 24.dp)
            .background(MaterialTheme.colorScheme.onPrimaryContainer, RoundedCornerShape(8.dp))
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResultFirstBox(fitFinalSize)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
            )
            ResultSecondBox()
        }
    }
}

@Composable
fun ResultFirstBox(fitFinalSize: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShyawayTextMedium(
            "BRA SIZE",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            ShyawayTextMedium(
                fitFinalSize.toString(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ShyawayTextMedium(
            "YOUR BODY SHAPE",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        ResultLabels("Centered Rounded")

    }
}

@Composable
fun ResultSecondBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShyawayTextMedium(
            "RECOMMENED STYLES",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ResultLabels("Padded")
            Spacer(modifier = Modifier.width(12.dp))
            ResultLabels("Non-Padded")
        }
    }
}

@Composable
fun ResultLabels(label: String) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .background(
                MaterialTheme.colorScheme.onPrimaryContainer,
                RoundedCornerShape(24.dp)
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                RoundedCornerShape(24.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .background(Color.Transparent, RoundedCornerShape(24.dp))
                .padding(vertical = 10.dp, horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ShyawayTextMedium(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
fun SetSizeCalculator(
    state: HomeState,
    viewModel: HomeViewModel
){
    val data = if(state.currentOnBoard == YesIdoOnBoard.ChartCalculationBra)  state.chartDataBra else state.chartDataHip
    if(state.sizeChartLoading){
        SetSizeCalculatorShimmer()
    }else{
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ShyawayTextMedium(
                    text = "Measure Yourself",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(Modifier.height(8.dp))
                ShyawayTextMedium(
                    text = "We recommend to you measure yourself",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                LazyColumn {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            AsyncImage(
                                model = data?.sizeImage,
                                contentDescription = "image",
                                modifier = Modifier
                                    .height(178.dp)
                                    .height(164.dp),
                                contentScale = ContentScale.Fit,
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            data?.staticBlock?.forEach { item ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .background(
                                                color = MaterialTheme.colorScheme.primary,
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {}
                                    Spacer(Modifier.width(8.dp))
                                    ShyawayTextMedium(
                                        text = item.toString(),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = MaterialTheme.colorScheme.tertiary,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                                Spacer(Modifier.height(12.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                FitInputEdit(state,viewModel)
            }
        }
    }
}

@Preview
@Composable
fun Final() {
//    FinalScreen(viewModel, state)
}