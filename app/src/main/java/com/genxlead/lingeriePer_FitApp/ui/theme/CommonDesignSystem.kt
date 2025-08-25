package com.genxlead.lingeriePer_FitApp.ui.theme

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.genxlead.lingeriePer_FitApp.R
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.BraSize
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.CurrentBrand
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.CurrentFit
import com.genxlead.lingeriePer_FitApp.ui.module.home.data.DoYouKnow
import com.genxlead.lingeriePer_FitApp.ui.module.home.presentaion.YesIdoOnBoard
import com.gxl.lingerieperfit.R
import com.gxl.lingerieperfit.ui.module.home.data.BraSize
import com.gxl.lingerieperfit.ui.module.home.data.CurrentBrand
import com.gxl.lingerieperfit.ui.module.home.data.CurrentFit
import com.gxl.lingerieperfit.ui.module.home.data.DoYouKnow
import com.gxl.lingerieperfit.ui.module.home.data.HomeState
import com.gxl.lingerieperfit.ui.module.home.presentaion.YesIdoOnBoard
import com.gxl.lingerieperfit.ui.module.home.presentaion.viewModel.HomeViewModel
import kotlin.math.roundToInt


@Composable
fun ShyawayTextLarge(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    modifier: Modifier = Modifier
) {
    Text(text, style = style, modifier = modifier)
}


@Composable
fun ShyawayTextMedium(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    mLine:Int = 1
) {
    Text(text, style = style, modifier = modifier  , maxLines = mLine)
}

@Composable
fun ShyawayTextSmall(text: String, style: TextStyle = MaterialTheme.typography.bodySmall) {
    Text(text, style = style, textAlign = TextAlign.Center)
}


@Composable
fun SelectionBox(item: DoYouKnow, selection: () -> Unit) {

    val bck = if (item.status) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
    val border = if (item.status) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiaryContainer

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                bck,
                RoundedCornerShape(24.dp)
            )
            .border(1.dp, border, RoundedCornerShape(24.dp))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                selection()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShyawayTextMedium(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            SelectionInc(isSelect = item.status)
        }
    }
}

@Composable
fun SelectionInc(isSelect: Boolean) {
    val base =
        if (isSelect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
    val border =
        if (isSelect) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
            .border(1.dp, border, CircleShape),
        contentAlignment = Alignment.Center
    )
    {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(base, CircleShape)
        )
    }
}

@Composable
fun SelectionLabel(know: DoYouKnow) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.onPrimaryContainer,
                RoundedCornerShape(24.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent, RoundedCornerShape(24.dp))
                .padding(vertical = 10.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ShyawayTextMedium(
                text = know.label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}


@Composable
fun NavigationBtn(
    currentVisible: YesIdoOnBoard,
    clickForward: () -> Unit,
    clickBackward: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 44.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (currentVisible != YesIdoOnBoard.BustFallType) {
            BtnBackward { clickBackward() }
            Spacer(modifier = Modifier.width(24.dp))
        }
        BtnForward {
            clickForward()
        }
    }
}

@Composable
fun BtnForward(clickForward: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary, CircleShape)
            .clickable {
                clickForward()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun BtnBackward(clickBackward: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary, CircleShape)
            .clickable {
                clickBackward()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun CardTop(cardHead: Triple<String, String, String>?) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShyawayTextMedium(
            cardHead?.first.toString(),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        ShyawayTextMedium(
            cardHead?.second.toString(),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CustomToaster(isSuccess: Boolean, message: String, modifier: Modifier = Modifier) {
    val backgroundColor = if (isSuccess) Color(0xFFE0FFEC) else Color(0xFFFDE8E8)
    val textColor = if (isSuccess) Color(0xFF008000) else Color(0xFFEF4444)
    val icon = if (isSuccess) R.drawable.ic_toast_sucess else R.drawable.ic_error_toaster

    val bottomPadding = 32.dp
    val density = LocalDensity.current

    Popup(
        alignment = Alignment.BottomCenter,
        offset = IntOffset(30, with(density) { -bottomPadding.toPx().toInt() })
    ) {
        Row(
            modifier
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(20.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                message,
                style = TextStyle(
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}


@Composable
fun ViewBrand(item: CurrentBrand, brandClick: () -> Unit) {
    val bck =
        if (item.status) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
    val border =
        if (item.status) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer
    val text =
        if (item.status) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    Box(
        modifier = Modifier
            .width(140.dp)
            .height(38.dp)
            .background(
                bck,
                RoundedCornerShape(38.dp)
            )
            .border(1.dp, border, RoundedCornerShape(38.dp))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                brandClick()
            },
        contentAlignment = Alignment.Center
    ) {
        ShyawayTextMedium(
            item.brand,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = text,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        )
    }
}

@Composable
fun ViewBraSize(item: BraSize, onSizeClick: () -> Unit) {
    val bck =
        if (item.status) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
    val border =
        if (item.status) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer
    val text =
        if (item.status) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary

    Box(
        modifier = Modifier
            .width(58.dp)
            .height(36.dp)
            .background(bck, RoundedCornerShape(24.dp))
            .border(1.dp, border, RoundedCornerShape(24.dp))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onSizeClick()
            },
        contentAlignment = Alignment.Center
    ) {
        ShyawayTextMedium(
            item.size,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = text,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
            )
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FitSlider(
    bandFitList: List<CurrentFit>,
    onSelectionChanged: (Int) -> Unit = {}
) {
    // Early return if list is empty
    if (bandFitList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No fit data available")
        }
        return
    }

    // State for current slider position
    val currentFitIndex = bandFitList.indexOfFirst { it.status }.takeIf { it >= 0 } ?: 0
    val maxIndex = (bandFitList.size - 1).coerceAtLeast(0)

    // 👇 sliderPosition must survive recomposition
    var sliderPosition by remember { mutableFloatStateOf(currentFitIndex.toFloat()) }

    // Keep sliderPosition in sync when ViewModel changes the selection
    LaunchedEffect(currentFitIndex) {
        sliderPosition = currentFitIndex.toFloat()
    }

    // Update ViewModel when slider position changes
    LaunchedEffect(sliderPosition) {
        val newIndex = sliderPosition.roundToInt()
        onSelectionChanged(newIndex)
    }

    val currentFit = bandFitList[currentFitIndex]

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Main image and content area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Animated image transition
            AnimatedContent(
                targetState = currentFitIndex,
                transitionSpec = {
                    fadeIn(animationSpec = tween(2000)).togetherWith(
                        fadeOut(
                            animationSpec = tween(
                                2000
                            )
                        )
                    )
                },
                label = "fit_transition"
            ) { fitIndex ->
                val fit = bandFitList[fitIndex]

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Image
                    Image(
                        painter = painterResource(id = fit.image),
                        contentDescription = fit.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Title
                    ShyawayTextMedium(
                        fit.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Description
                    ShyawayTextMedium(
                        fit.description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }

        // Bottom slider section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {

            // Custom slider track with labels
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                // Background track
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.Gray.copy(alpha = 0.2f))
                        .align(Alignment.Center)
                )

                // Active track
                val progress = if (maxIndex > 0) sliderPosition / maxIndex else 0f
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(4.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                                )
                            )
                        )
                        .align(Alignment.CenterStart)
                )

                // Step indicators
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    repeat(bandFitList.size) { index ->
                        val isActive = index <= currentFitIndex
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isActive) MaterialTheme.colorScheme.primary else Color(
                                        0xFFFF99A5
                                    )
                                )
                                .clickable {
                                    sliderPosition = index.toFloat()
                                }
                        )
                    }
                }

                // Touch detector for dragging
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .pointerInput(bandFitList.size) {
                            detectDragGestures(
                                onDragEnd = {
                                    // Snap to nearest step
                                    sliderPosition = sliderPosition.roundToInt().toFloat()
                                }
                            ) { _, dragAmount ->
                                if (maxIndex > 0) {
                                    val screenWidth = size.width.toFloat()
                                    val sensitivity = maxIndex / screenWidth
                                    val newPosition = sliderPosition + (dragAmount.x * sensitivity)
                                    sliderPosition = newPosition.coerceIn(0f, maxIndex.toFloat())
                                }
                            }
                        }
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { /* Handle tap to set position */ }
                        .align(Alignment.Center)
                )
            }
            // Slider labels (showing all options)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                bandFitList.forEachIndexed { index, fit ->
                    val isSelected = index == currentFitIndex
                    Text(
                        text = fit.title,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable {
                            sliderPosition = index.toFloat()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}


@Composable
fun OutlinedEditText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    error: String? = "",
    keyboardType: KeyboardType
) {
    // Track focus state
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        ShyawayTextMedium(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary)
        )

        Spacer(modifier = Modifier.height(getHeight(8)))
        // Border and background
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(getHeight(44))
                .clip(RoundedCornerShape(12.dp))
                .border(
                    border = BorderStroke(
                        width = if (isFocused) 2.dp else 1.dp,
                        color = Color(0xFFE8E8E8)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxSize()
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.tertiary,
                ),
                cursorBrush = SolidColor(Color(0xFF7E7E7E)),
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Next
                ),
                maxLines = 1,
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.CenterStart,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        if (value.isEmpty() && !isFocused) {
                            ShyawayTextMedium(
                                text = placeholder,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Normal
                                ),
                            )
                        }
                        innerTextField()
                    }
                },
            )
        }
        Spacer(modifier = Modifier.height(getHeight(4)))
        if (!error.isNullOrEmpty())
            ShyawayTextSmall(
                error.toString(),
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.error)
            )

        // Text field
    }
}

@Composable
fun HomeTopBar(state: HomeState, backClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.perfit_arrow_back),
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = remember {  MutableInteractionSource() }
                ) {
                    backClick()
                },
                contentDescription = null,
                tint = Color.Unspecified,
            )
            TopBarStatus(
                status = state.topBarInc,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 24.dp)
            )

            ShyawayTextMedium(
                text = "${state.topBarInc.count { it }}/${state.topBarInc.size}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Composable
fun TopBarStatus(modifier: Modifier = Modifier, status: List<Boolean>) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        status.forEachIndexed { index, bool ->
            BarStatus(
                status = bool,
                modifier = Modifier.weight(1f)
            )
            if (index != 4) Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun BarStatus(modifier: Modifier = Modifier, status: Boolean) {
    HorizontalDivider(
        modifier = modifier
            .height(4.dp)
            .clip(RoundedCornerShape(2.dp)),
        thickness = 4.dp,
        color = if (status) MaterialTheme.colorScheme.primary else Color(0xFFE8E8E8)
    )
}

@Composable
fun FinalButtons(clickStart: () -> Unit, clickShop: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 44.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BtnStartAgain(clickStart)
        Spacer(modifier = Modifier.width(16.dp))
        BtnShop(clickShop)
    }
}

@Composable
fun BtnStartAgain(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .width(148.dp)
            .height(40.dp)
    ) {
        ShyawayTextMedium(
            text = "START AGAIN",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        )
    }
}

@Composable
fun BtnShop(
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .width(148.dp)
            .height(40.dp),

        ) {
        ShyawayTextMedium(
            text = "SHOP NOW",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        )
    }
}


@Composable
fun FitInputEdit(state: HomeState, viewModel: HomeViewModel) {
    val value =
        if (state.currentOnBoard == YesIdoOnBoard.ChartCalculationBra) state.onInputBand.toString() else state.onInputHip.toString()
    val placeHolder =
        if (state.currentOnBoard == YesIdoOnBoard.ChartCalculationBra) "Band (eg. 80cm)" else "Hip (eg. 95cm)"

    var isFocused by remember { mutableStateOf(false) }
    var isFocused2 by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(142.dp)
                .height(getHeight(36))
                .clip(RoundedCornerShape(24.dp))
                .border(
                    border = BorderStroke(
                        width = if (isFocused) 2.dp else 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(24.dp)
                )
        ) {
            BasicTextField(
                value = value,
                onValueChange = {
                    if (state.currentOnBoard == YesIdoOnBoard.ChartCalculationBra) {
                        viewModel.onInputBand(it)
                    } else {
                        viewModel.onInputHip(it)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.tertiary,
                ),
                cursorBrush = SolidColor(Color(0xFF7E7E7E)),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                maxLines = 1,
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        if (!isFocused) {
                            ShyawayTextMedium(
                                text = placeHolder,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Normal
                                ),
                            )
                        }
                        innerTextField()
                    }
                },
            )
        }
        if (state.currentOnBoard == YesIdoOnBoard.ChartCalculationBra) {
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .width(142.dp)
                    .height(getHeight(36))
                    .clip(RoundedCornerShape(24.dp))
                    .border(
                        border = BorderStroke(
                            width = if (isFocused) 2.dp else 1.dp,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(24.dp)
                    )
            ) {
                BasicTextField(
                    value = state.onInputBust,
                    onValueChange = {
                        viewModel.onInputBust(it)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .onFocusChanged { focusState ->
                            isFocused2 = focusState.isFocused
                        },
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.tertiary,
                    ),
                    cursorBrush = SolidColor(Color(0xFF7E7E7E)),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    maxLines = 1,
                    decorationBox = { innerTextField ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            if (!isFocused2 && state.onInputBust.isEmpty()) {
                                ShyawayTextMedium(
                                    text = "Bust (eg. 80cm)",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = MaterialTheme.colorScheme.primary,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Normal
                                    ),
                                )
                            }
                            innerTextField()
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun SetSizeCalculatorShimmer() {

    // Create shimmer brush
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .padding(horizontal = 100.dp)
                .background(brush = brush, shape = RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            item {
                // Image shimmer
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .width(172.dp)
                            .height(164.dp)
                            .background(brush = brush, shape = RoundedCornerShape(8.dp))
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 12.dp, end = 12.dp)
                ) {
                    // Static block shimmer - 3 items
                    repeat(6) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            // Bullet point shimmer
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(brush = brush, shape = CircleShape),
                            )
                            Spacer(Modifier.width(8.dp))

                            // Text shimmer
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(14.dp)
                                    .background(brush = brush, shape = RoundedCornerShape(12.dp))
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun FitVideoPlay(playClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(12.dp))

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                ShyawayTextMedium(
                    text = "Need More Help?",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                Spacer(modifier = Modifier.height(4.dp))
                ShyawayTextMedium(
                    text = "Watch our measurement guide",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal
                    ),
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.onPrimary, CircleShape)
                    .clickable {
                        playClick()
                    }
                    .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.fit_play_video),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun SelectionBoxPreview() {
//    FitInputEdit()
//    SelectionBox("SFASF",
//        true){}

    //NavigationBtn()
}