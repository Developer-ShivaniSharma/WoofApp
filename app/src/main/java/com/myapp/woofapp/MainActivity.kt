package com.myapp.woofapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.woofapp.data.DogInfo
import com.myapp.woofapp.model.WoofClass
import com.myapp.woofapp.ui.theme.WoofAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    WoofApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofApp(modifier: Modifier = Modifier) {
    Scaffold(topBar = { WoofAppBar() }) {
        WoofAppList(dogInfoList = WoofClass().dogInfo, modifier = Modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_woof_logo),
                modifier = Modifier.padding(16.dp),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayMedium
            )
        }

    })
}


@Composable
fun DogDetail(dogInfo: DogInfo, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp),
    ) {
        Row(
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessHigh
                    )
                )
                .background(color = color)
                .fillMaxWidth()
                .padding(8.dp),


        ) {

            Image(
                painter = painterResource(dogInfo.imageResourceId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)

            )

            Column {
                Text(
                    text = stringResource(dogInfo.name),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = stringResource(R.string.years_old, dogInfo.age),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 10.dp)

                )
            }
            Spacer(modifier = Modifier.weight(1f))
            WoofClickButton(expanded = expanded, onClick = { expanded = !expanded })

        }
        if (expanded) {
            DogHobbies()
        }
    }
}

@Composable
fun DogHobbies(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall,
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = stringResource(R.string.dog_description_1),
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
fun WoofClickButton(expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )


    }
}

@Composable
fun WoofAppList(dogInfoList: List<DogInfo>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(dogInfoList) { dogInfo ->
            DogDetail(dogInfo = dogInfo, modifier = Modifier.padding(8.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun WoofAppBarPreview() {
    WoofAppBar()
}

@Preview(showBackground = true)
@Composable
fun DogDetailPreview() {
    DogDetail(DogInfo(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1))
}

@Preview(showBackground = true)
@Composable
fun DogHobbiesPreview() {
    DogHobbies()
}

@Preview(showBackground = true)
@Composable
fun WoofAppListPreview() {
    WoofAppList(
        dogInfoList = listOf(
            DogInfo(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
            DogInfo(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
            DogInfo(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
            DogInfo(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4),
            DogInfo(R.drawable.faye, R.string.dog_name_5, 8, R.string.dog_description_5),
            DogInfo(R.drawable.bella, R.string.dog_name_6, 14, R.string.dog_description_6),
            DogInfo(R.drawable.moana, R.string.dog_name_7, 2, R.string.dog_description_7),
            DogInfo(R.drawable.tzeitel, R.string.dog_name_8, 7, R.string.dog_description_8),
            DogInfo(R.drawable.leroy, R.string.dog_name_9, 4, R.string.dog_description_9)
        )
    )

}

@Preview(showBackground = true)
@Composable
fun WoofAppPreview() {
    WoofAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            WoofApp()
        }
    }
}

