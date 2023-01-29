package com.example.composefirst.pages

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composefirst.R
import com.example.composefirst.data.BottomMenuContent
import com.example.composefirst.data.Feature
import com.example.composefirst.standardQuadFrom
import com.example.composefirst.ui.theme.ComposeFirstTheme
import com.example.composefirst.ui.theme.Purple200
import com.example.composefirst.ui.theme.Purple700
import com.example.composefirst.ui.theme.Teal200


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Column {
            GreetingSection(name = "Gor")
            ChipSection(chips = listOf("first", "second", "third"))
            CurrentMediation()
            FeatureSection(features = listOf(
                Feature(
                    "first", R.drawable.ic_search,
                    Color.White, Color.Blue, Purple700
                ),
                Feature(
                    "second",R.drawable.ic_headphones,
                    Color.White, Color.Blue, Purple700
                ),
                Feature(
                    "third",R.drawable.ic_beach,
                    Color.White, Color.Blue, Purple700
                ),
                Feature(
                    "four",R.drawable.ic_moon,
                    Color.White, Color.Blue, Purple700
                )
            ))
        }

        BottomMenu(items = listOf(
            BottomMenuContent("One",R.drawable.ic_search),
            BottomMenuContent("Two",R.drawable.ic_moon),
            BottomMenuContent("Three",R.drawable.ic_headphones),
            BottomMenuContent("Beach",R.drawable.ic_beach)
        ),
            modifier = Modifier.align(Alignment.BottomCenter))

    }
}

@Composable
fun GreetingSection(name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Good morning $name",
                color = Color.White,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "How are you?",
                color = Color.White,
                style = MaterialTheme.typography.body1
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_search), contentDescription = "",
            tint = Color.White, modifier = Modifier.size(24.dp)
        )


    }
}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(chips.size) {
            Box(modifier = Modifier
                .padding(15.dp, 15.dp, 15.dp)
                .clickable {
                    selectedIndex = it
                }
                .clip(RoundedCornerShape(10.dp))
                .background(if (selectedIndex == it) Purple200 else Purple700)
                .padding(15.dp)
            ) {
                Text(text = chips[it], color = Color.White)
            }
        }
    }

}

@Composable
fun CurrentMediation(
    color: Color = Teal200
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Text(
                text = "Good morning",
                color = Color.White,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = "How are you?",
                color = Color.White,
                style = MaterialTheme.typography.body1
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Cyan)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search), contentDescription = "",
                tint = Color.White, modifier = Modifier.size(24.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureSection(features: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Featured",
            color = Color.White,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(15.dp)
        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }

    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight


        val mediumColorPoint1 = Offset(0f, height * 0.3f)
        val mediumColorPoint2 = Offset(width * 0.1f, height * 0.36f)
        val mediumColorPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColorPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColorPath = Path().apply {
            moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
            standardQuadFrom(mediumColorPoint1, mediumColorPoint2)
            standardQuadFrom(mediumColorPoint2, mediumColorPoint3)
            standardQuadFrom(mediumColorPoint3, mediumColorPoint4)
            standardQuadFrom(mediumColorPoint4, mediumColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(mediumColorPath, color = feature.mediumColor)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        )
        {

            Text(
                text = feature.title,
                color = Color.White,
                style = MaterialTheme.typography.h6,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )


            Text(
                text = "start",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Purple700)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )

        }


    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = Color.Gray,
    activeTextColor: Color = Color.White,
    inActiveTextColor: Color = Color.Gray,
    initialSelectedItemIndex:Int  = 0
){
    var selectedIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(15.dp)) {
        items.forEachIndexed { index, bottomMenuContent ->
            BottomMenuItem(
                item = bottomMenuContent,
                isSelected = index == selectedIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inActiveTextColor = inActiveTextColor
            ) {
                selectedIndex = index
            }
        }

    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected:Boolean = false,
    activeHighlightColor: Color,
    activeTextColor: Color,
    inActiveTextColor: Color,
    onItemClick:()->Unit
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }){
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)){
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inActiveTextColor)
        }

        Text(text = item.title,
            color = if (isSelected) activeTextColor else inActiveTextColor
        )


    }
}