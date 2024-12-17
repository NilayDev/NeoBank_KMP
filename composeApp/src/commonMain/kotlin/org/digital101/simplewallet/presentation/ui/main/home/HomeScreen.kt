package org.digital101.simplewallet.presentation.ui.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.digital101.simplewallet.presentation.component.DefaultScreenUI
import org.digital101.simplewallet.presentation.theme.BaseColors
import org.digital101.simplewallet.presentation.ui.main.home.viewModel.HomeViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import simplewallet.composeapp.generated.resources.Res
import simplewallet.composeapp.generated.resources.action_1
import simplewallet.composeapp.generated.resources.action_2
import simplewallet.composeapp.generated.resources.action_3
import simplewallet.composeapp.generated.resources.app_logo
import simplewallet.composeapp.generated.resources.arrow_right
import simplewallet.composeapp.generated.resources.icon
import simplewallet.composeapp.generated.resources.label_activate_your_virtual_card_now
import simplewallet.composeapp.generated.resources.profile

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinInject(),
    onHamburgerClick: () -> Unit,
) {
    val loanItems = listOf(
        LoanItem(
            "Finance",
            "Quick Loans",
            "Instant personal loans anytime, anywhere – no collateral required.",
            Res.drawable.profile
        ),
        LoanItem(
            "Finance",
            "Quick Loans",
            "Instant personal loans anytime, anywhere – no collateral required.",
            Res.drawable.profile
        ),
        LoanItem(
            "Finance",
            "Quick Loans",
            "Instant personal loans anytime, anywhere – no collateral required.",
            Res.drawable.profile
        ),
        LoanItem(
            "Finance",
            "Quick Loans",
            "Instant personal loans anytime, anywhere – no collateral required.",
            Res.drawable.profile
        ),
    )

    val itemList = arrayListOf(
        Item("Transfer", Res.drawable.action_1),
        Item("Reward", Res.drawable.action_2),
        Item("Bills", Res.drawable.action_3),
    )


    DefaultScreenUI(
        isHamburgerMenu = true,
        onHamburgerClick = onHamburgerClick,
        userName = viewModel.state.value.data?.userName,
        progressBarState = viewModel.state.value.progressBarState,
        networkState = viewModel.state.value.networkState,
        queue = viewModel.state.value.errorQueue,
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 14.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    border = BorderStroke(color = BaseColors.BorderColor, width = 1.dp),
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.icon),
                            contentDescription = null,
                        )
                        Text(
                            text = stringResource(Res.string.label_activate_your_virtual_card_now),
                            modifier = Modifier.padding(start = 12.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            painter = painterResource(Res.drawable.arrow_right),
                            contentDescription = null,
                        )
                    }
                }
            }


            item {
                CreditCardView(
                    totalBalance = (viewModel.state.value.wallet?.availableBalance ?: 0).toString()
                )
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(itemList.size) { index ->
                        ListItemWithImage(
                            modifier = Modifier.fillParentMaxWidth(fraction = (1f / 3.15).toFloat()),
                            item = itemList[index]
                        )
                    }
                }
            }

            items(loanItems) { loanItem ->
                Spacer(modifier = Modifier.height(8.dp))
                LoanCard(loanItem = loanItem)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun CreditCardView(
    modifier: Modifier = Modifier,
    totalBalance: String = "$5,432.15"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(top = 16.dp, bottom = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.tertiary, // Card background color
                shape = RoundedCornerShape(16.dp)
            )

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            // Bank Logo and Name
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Bank Logo (Use your image resource here)
                Image(
                    painter = painterResource(Res.drawable.app_logo),
                    contentDescription = "Bank Logo",
                    modifier = Modifier.align(Alignment.CenterVertically).height(50.dp)
                )
            }

            Column {
                Text(
                    text = "Total Balance",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Balance: $totalBalance",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ListItemWithImage(
    modifier: Modifier = Modifier,
    item: Item
) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .aspectRatio(1F)
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image (drawable)
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = item.name,
                modifier = Modifier.height(50.dp).width(50.dp)
            )
            // Text
            Text(
                text = item.name,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.padding(1.dp)
            )
        }
    }
}

@Composable
fun LoanCard(
    modifier: Modifier = Modifier,
    loanItem: LoanItem,
) {
    Card(
        modifier = modifier
            .fillMaxWidth().padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.weight(2f)) {
                Text(
                    text = loanItem.category,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.border(
                        BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                        shape = RoundedCornerShape(4.dp),
                    ).padding(paddingValues = PaddingValues(horizontal = 6.dp, vertical = 4.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = loanItem.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 2.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = loanItem.description,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }

            Image(
                painter = painterResource(loanItem.imageRes),
                contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
        }
    }
}

data class LoanItem(
    val category: String,
    val title: String,
    val description: String,
    val imageRes: DrawableResource
)

data class Item(val name: String, val imageRes: DrawableResource)
