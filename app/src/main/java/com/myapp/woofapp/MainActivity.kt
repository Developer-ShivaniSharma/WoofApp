package com.myapp.woofapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.woofapp.data.DogInfo
import com.myapp.woofapp.ui.theme.WoofAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    WoofApp()
                }
            }
        }
    }
}

@Composable
fun WoofApp(modifier: Modifier = Modifier) {
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
    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)

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
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(dogInfo.name),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = stringResource(R.string.years_old, dogInfo.age),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp)

                )
            }
        }
    }
}

@Composable
fun DogHobbies(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(R.string.dog_description_1),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Composable
fun WoofClickButton(expended: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = if(expended)Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription =
        )


    }
}

@Composable
fun WoofAppList(dogInfoList: List<DogInfo>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(dogInfoList) { dogInfo ->
            DogDetail(dogInfo = dogInfo, modifier = modifier.padding(8.dp))

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