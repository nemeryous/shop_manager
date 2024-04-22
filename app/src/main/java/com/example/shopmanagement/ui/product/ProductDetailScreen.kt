package com.example.shopmanagement.ui.product

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.example.shopmanagement.R
import com.example.shopmanagement.ui.navigation.NavigationDestination
import com.example.shopmanagement.ui.theme.ShopManagementTheme
import kotlin.math.absoluteValue
import kotlin.random.Random

val listImgs = listOf(
    R.drawable.h2,
    R.drawable.h3,
    R.drawable.h4,
    R.drawable.h6,
    R.drawable.h7,
    R.drawable.h5,
    R.drawable.h3,
)

object ProductDetailDestination : NavigationDestination {
    override val route: String = "product_details"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun ProductDetailScreen() {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                ProductImagePager(
                    images = listImgs, modifier = Modifier.fillMaxSize()
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(32.dp)) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = "New Balance 996V2",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.size(28.dp)) {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "8,374 sold",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(Color.Gray.copy(0.3f), RoundedCornerShape(8.dp))
                                .padding(vertical = 2.dp, horizontal = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.Black
                        )
                        Text(text = "4.9 (6.573 reviews)")
                    }

                    HorizontalDivider()
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                ) {
                    DetailContainerVertical(name = "Description") {
                        ExpandedText(
                            text = stringResource(id = R.string.not_full),
                            expandedText = stringResource(id = R.string.full),
                            expandedTextButton = " view more..",
                            shrinkTextButton = " less",
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        DetailContainerVertical(name = "Size") {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                items(4) {
                                    TextCircleButton(text = "4$it", onClick = {}, size = 40.dp)
                                }
                            }
                        }

                        DetailContainerVertical(name = "Color") {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                items(4) {
                                    ColorCircleButton(
                                        color = Color(
                                            red = Random.nextInt(256),
                                            blue = Random.nextInt(256),
                                            green = Random.nextInt(256),
                                        ), onClick = {}, size = 40.dp
                                    )
                                }
                            }
                        }
                    }

                    DetailContainerHorizontal(name = "Quantity") {
                        QuantityButton(size = 44.dp)
                    }
                }

            }
        }

        PriceBar(price = "$5.555", border = false) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.AddShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Add to Cart", fontSize = 17.sp)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ProductImagePager(
    images: List<Int>,
    modifier: Modifier = Modifier
        .height(320.dp)
        .fillMaxWidth()
) {
    val pagerState = rememberPagerState(pageCount = {
        images.size
    })

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = modifier
            .background(Color.White.copy(0.7f))
    ) {
        HorizontalPager(state = pagerState) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val pageOffset = (
                                (pagerState.currentPage - it) + pagerState
                                    .currentPageOffsetFraction
                                )

                        alpha = lerp(
                            start = 0.4f,
                            stop = 1f,
                            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
                        )

                        cameraDistance = 8 * density

                        rotationY = lerp(
                            start = 0f,
                            stop = 80f,
                            fraction = pageOffset.coerceIn(-1f, 1f),
                        )

                        lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f),
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
            ) {
                Image(
                    painter = painterResource(images[it]),
                    contentDescription = null,
                    modifier = Modifier
                        .background(Color.Gray.copy(0.3f))
                        .size(280.dp)

                )
            }
        }


        Row(Modifier.padding(vertical = 16.dp)) {
            if (images.size > 1) {
                PagerIndicator(
                    pagerState = pagerState,
                    indicatorCount = if (images.size > 4) 4 else images.size,
                    animationDurationInMillis = 500
                )
            }
        }
    }
}


@Composable
private fun DetailContainerVertical(
    name: String,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )
        content()
    }
}

@Composable
private fun DetailContainerHorizontal(
    name: String,
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )
        content()
    }
}

@Composable
fun TextCircleButton(
    text: String,
    active: Boolean = false,
    onClick: (isActivated: Boolean) -> Unit,
    size: Dp = 32.dp,
    modifier: Modifier = Modifier
) {
    var isActivated by remember { mutableStateOf(active) }
    val backgroundColor = if (isActivated) Color.Black else Color.White
    val textColor = if (isActivated) Color.White else Color.Black

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(2.dp, Color.Black, CircleShape)
            .size(size)
            .background(backgroundColor, CircleShape)
            .clip(CircleShape)
            .clickable {
                isActivated = !isActivated
                onClick(isActivated)
            }
    ) {

        Text(text = text, fontSize = (size.value / 2).sp, color = textColor)
    }
}


@Composable
fun ColorCircleButton(
    color: Color,
    checkedColor: Color = Color.White,
    active: Boolean = false,
    onClick: (isActivated: Boolean) -> Unit,
    size: Dp = 32.dp,
    modifier: Modifier = Modifier
) {
    var isActivated by remember { mutableStateOf(active) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
            .background(color, CircleShape)
            .clip(CircleShape)
            .clickable {
                isActivated = !isActivated
                onClick(isActivated)
            }
    ) {
        if (isActivated) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = checkedColor
            )
        }
    }
}

@Composable
fun QuantityButton(size: Dp) {
    var quantity by remember { mutableIntStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(Color.Gray.copy(0.3f), RoundedCornerShape(30.dp))
    ) {
        IconButton(
            onClick = { if (quantity > 0) quantity-- },
            modifier = Modifier.size(size),
            enabled = quantity > 0
        ) {
            Icon(Icons.Filled.Remove, contentDescription = "Decrease Quantity")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$quantity", fontSize = 20.sp)
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { quantity++ },
            modifier = Modifier.size(size),
            enabled = true
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Increase Quantity")
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    indicatorCount: Int = 4,
    selectedColor: Color = Color.White,
    defaultColor: Color = Color.LightGray.copy(0.7f),
    containerColor: Color = Color.Black.copy(0.7f),
    defaultRadius: Dp = 8.dp,
    selectedLength: Dp = 20.dp,
    space: Dp = 6.dp,
    animationDurationInMillis: Int = 300,
) {
    val listState = rememberLazyListState()

    val totalWidth: Dp = defaultRadius * indicatorCount + space * (indicatorCount - 1)
    val widthInPx = LocalDensity.current.run { defaultRadius.toPx() }

    val currentItem by remember {
        derivedStateOf {
            pagerState.currentPage
        }
    }

    val itemCount = pagerState.pageCount

    LaunchedEffect(key1 = currentItem) {
        val viewportSize = listState.layoutInfo.viewportSize
        listState.animateScrollToItem(
            currentItem,
            (widthInPx / 2 - viewportSize.width / 2).toInt()
        )
    }


    LazyRow(
        state = listState,
        horizontalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(vertical = space / 2),
        verticalAlignment = Alignment.CenterVertically,
        userScrollEnabled = false,
        modifier = modifier
            .width(totalWidth + defaultRadius * 4)
            .background(containerColor, RoundedCornerShape(defaultRadius * (1.5f)))
    ) {
        item { Spacer(modifier = Modifier.width(space)) }

        items(itemCount) { index ->

            val isSelected = (index == currentItem)

            // Index of item in center when odd number of indicators are set
            // for 5 indicators this is 2nd indicator place
            val centerItemIndex = indicatorCount / 2

            val right1 =
                (currentItem < centerItemIndex &&
                        index >= indicatorCount - 1)

            val right2 =
                (currentItem >= centerItemIndex &&
                        index >= currentItem + centerItemIndex &&
                        index <= itemCount - centerItemIndex + 1)
            val isRightEdgeItem = right1 || right2

            // Check if this item's distance to center item is smaller than half size of
            // the indicator count when current indicator at the center or
            // when we reach the end of list. End of the list only one item is on edge
            // with 10 items and 7 indicators
            // 7-3= 4th item can be the first valid left edge item and
            val isLeftEdgeItem =
                index <= currentItem - centerItemIndex &&
                        currentItem > centerItemIndex &&
                        index < itemCount - indicatorCount + 1

            PageIndicatorView(
                isSelected = isSelected,
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                defaultRadius = defaultRadius,
                selectedLength = selectedLength,
                animationDurationInMillis = animationDurationInMillis,
            )
            Spacer(modifier = Modifier.width(space))
        }
    }
}

@Composable
fun PageIndicatorView(
    isSelected: Boolean,
    selectedColor: Color,
    defaultColor: Color,
    defaultRadius: Dp,
    selectedLength: Dp,
    animationDurationInMillis: Int,
    modifier: Modifier = Modifier,
) {

    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            defaultColor
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        ), label = ""
    )
    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            selectedLength
        } else {
            defaultRadius
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
        ), label = ""
    )

    Canvas(
        modifier = modifier
            .size(
                width = width,
                height = defaultRadius,
            ),
    ) {
        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(
                width = width.toPx(),
                height = defaultRadius.toPx(),
            ),
            cornerRadius = CornerRadius(
                x = defaultRadius.toPx(),
                y = defaultRadius.toPx(),
            ),
        )
    }
}


@Composable
fun PriceBar(
    price: String,
    border: Boolean = true,
    modifier: Modifier = Modifier,
    actionButtonContent: @Composable () -> Unit,
) {

    Column(
        verticalArrangement = if (border) Arrangement.Center else Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
            .let {
                if (border)
                    it
                        .background(
                            Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .border(
                            border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .height(100.dp)
                else it
                    .background(Color.White)
                    .height(80.dp)
            }
    ) {
        if (!border) HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(horizontal = 16.dp)
        ) {
            Column {
                Text(text = "Total Price", fontSize = 12.sp, fontWeight = FontWeight.Light)
                Text(text = price, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(height = 52.dp, width = 200.dp)
                ) {
                    actionButtonContent()
                }
            }
        }
    }
}

@Composable
fun ExpandedText(
    text: String,
    expandedText: String,
    expandedTextButton: String,
    shrinkTextButton: String
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = if (expanded) expandedText else text,
            maxLines = if (expanded) Int.MAX_VALUE else 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        if (!expanded) {
            TextButton(
                onClick = { expanded = true },
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(text = expandedTextButton)
            }
        } else {
            TextButton(
                onClick = { expanded = false },
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(text = shrinkTextButton)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProductDetailScreen() {
    ShopManagementTheme {
        Surface {
            ProductDetailScreen()
        }
    }
}