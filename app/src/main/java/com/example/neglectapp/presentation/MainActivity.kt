/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.neglectapp.presentation

import NeglectButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.neglectapp.R
import androidx.wear.compose.material.*
import com.example.neglectapp.presentation.ui.components.display.DisplayStatus
import com.example.neglectapp.presentation.ui.components.settings.SettingsIcon
import com.example.neglectapp.presentation.util.ButtonType


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    MaterialTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
//            verticalArrangement = Arrangement.Center
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SettingsIcon(onClick = { /*TODO*/ })
            Spacer(modifier = Modifier.height(45.dp))
            DisplayStatus(modifier = Modifier)
            Spacer(modifier = Modifier.height(25.dp))
            Column(modifier = Modifier.size(75.dp)) {
                NeglectButton(type = ButtonType.TEXT, modifier = Modifier, label = "Starten"){}

            }

        }
    }
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

@Preview(name= "Light Mode", device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode",
//    device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true
//)
@Composable
fun DefaultPreview() {
    WearApp()
}