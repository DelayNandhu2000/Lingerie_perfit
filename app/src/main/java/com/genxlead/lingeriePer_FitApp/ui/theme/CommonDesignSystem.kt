package com.genxlead.lingeriePer_FitApp.ui.theme

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Text(text, style = style, modifier = modifier)
}

@Composable
fun ShyawayTextSmall(text: String, style: TextStyle = MaterialTheme.typography.bodySmall) {
    Text(text, style = style, textAlign = TextAlign.Center)
}


@Composable
fun SelectionBox(item: DoYouKnow, selection: () -> Unit) {
    val bck =
        if (item.status) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
    val border =
        if (item.status) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSecondaryContainer
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
                    color = MaterialTheme.colorScheme.secondary,
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
        BtnBackward { clickBackward() }
        Spacer(modifier = Modifier.width(24.dp))
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
                fontSize = 14.sp
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
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    // Calculate current fit index based on slider position (safe calculation)
    val maxIndex = (bandFitList.size - 1).coerceAtLeast(0)
    val currentFitIndex = sliderPosition.roundToInt().coerceIn(0, maxIndex)
    val currentFit = bandFitList[currentFitIndex]

    // Trigger callback when selection changes
    LaunchedEffect(currentFitIndex) {
        onSelectionChanged(currentFitIndex)
    }

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
                    fadeIn(animationSpec = tween(400)).togetherWith(
                        fadeOut(
                            animationSpec = tween(
                                200
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
                modifier = Modifier.fillMaxWidth()
            ) {
                // Background track
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color.Gray.copy(alpha = 0.2f))
                        .align(Alignment.Center)
                )

                // Active track
                val progress = if (maxIndex > 0) sliderPosition / maxIndex else 0f
                Box(
                    modifier = Modifier
                        .fillMaxWidth(progress)
                        .height(6.dp)
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
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isActive) MaterialTheme.colorScheme.primary else Color(
                                        0xFFFF99A5
                                    )
                                )
                                .border(
                                    width = if (index == currentFitIndex) 2.dp else 0.dp,
                                    color = Color.White,
                                    shape = CircleShape
                                )
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
                        .clickable { /* Handle tap to set position */ }
                        .align(Alignment.Center)
                )
            }
            // Slider labels (showing all options)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
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
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}


@Composable
fun OutlinedEditText(
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
            modifier = Modifier
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


@Preview(showBackground = false)
@Composable
fun SelectionBoxPreview() {
//    SelectionBox("SFASF",
//        true){}

    //NavigationBtn()
}