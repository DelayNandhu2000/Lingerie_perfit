package com.gxl.lingerieperfit.ui.module.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gxl.lingerieperfit.R
import com.gxl.lingerieperfit.ui.base.navGraph.Screen
import com.gxl.lingerieperfit.ui.module.home.data.OnBoardData
import com.gxl.lingerieperfit.ui.module.home.presentaion.viewModel.HomeViewModel
import com.gxl.lingerieperfit.ui.theme.ShyawayTextMedium
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun OnBoardScreen(navController: NavHostController, viewModel: HomeViewModel = koinViewModel()) {

    LaunchedEffect(key1 = Unit){
        viewModel.getOnBoarData()
    }

    val state by viewModel.state.collectAsState()
    val pageState = rememberPagerState { 3 }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFCED4),
                        Color(0xFFFFF6F7)
                    )
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 60.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.perfit_logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(56.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.height(16.dp))
                ShyawayTextMedium(
                    text = "LINGERIE - PERFIT",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))

                HorizontalPager(
                    state = pageState,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) { page ->
                    if (state.onBoardData.isNotEmpty()) {
                        PagerItem(item = state.onBoardData[page])
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page indicators that sync with pager
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                color = if (pageState.currentPage == index)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                shape = CircleShape
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(62.dp))

            Button(
                onClick = {
                    when (pageState.currentPage) {
                        0, 1 -> {
                            // First and second page: scroll to next page
                            coroutineScope.launch {
                                pageState.animateScrollToPage(pageState.currentPage + 1)
                            }
                        }
                        2 -> {
                            // Third page: navigate to home
                            navController.navigate(Screen.Home) {
                                popUpTo(Screen.OnBoard) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .width(144.dp)
                    .height(48.dp)
            ) {
                ShyawayTextMedium(
                    text = if (pageState.currentPage == 2) "Get Started" else "Next",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}

@Composable
fun PagerItem(item: OnBoardData) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = "image",
            modifier = Modifier.size(264.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        ShyawayTextMedium(
            text = item.title.uppercase(),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        ShyawayTextMedium(
            text = item.dec,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}