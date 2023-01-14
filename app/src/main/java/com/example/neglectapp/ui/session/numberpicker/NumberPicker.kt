package com.example.neglectapp.ui.session.numberpicker

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.neglectapp.navigation.Screen
import com.example.neglectapp.viewmodel.HeftosViewModel
import com.example.neglectapp.viewmodel.NumberPickerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NumberPicker(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    numberPickerViewModel: NumberPickerViewModel = viewModel()
) {
    val viewModel: HeftosViewModel = viewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val min = (1..5).toList()
    val max = (5..10).toList()
    val minLocal = viewModel.minSession.collectAsState().value
    val maxLocal = viewModel.maxSession.collectAsState().value
    val stateMin = rememberPickerState(min.size, initiallySelectedOption = minLocal - 1)
    val stateMax = rememberPickerState(max.size, initiallySelectedOption = maxLocal + 1)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!numberPickerViewModel.showMax.collectAsState().value) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp),
                text = "Sessie"
            )
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 40.dp),
                text = "Minimum",
                color = MaterialTheme.colors.secondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Picker(
                modifier = Modifier.size(100.dp, 100.dp),
                state = stateMin,
                contentDescription = "NumberPicker Minimum",
            ) {
                Text(
                    "${min[it]}",
                    fontSize = 30.sp,
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { viewModel.saveMinSession(min[stateMin.selectedOption]); numberPickerViewModel.toggleMax() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Next",
                    modifier = Modifier
                        .size(24.dp)
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        } else {
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 10.dp),
                text = "Sessie"
            )
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 40.dp),
                text = "Maximum",
                color = MaterialTheme.colors.secondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Picker(
                modifier = Modifier.size(100.dp, 100.dp),
                state = stateMax,
                contentDescription = "NumberPicker Maximum",
            ) {
                Text(
                    "${max[it]}",
                    fontSize = 30.sp,
                    color = MaterialTheme.colors.secondary,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = {
                    viewModel.saveMaxSession(max[stateMax.selectedOption]); Toast.makeText(
                    context,
                    "Aantal gewijzigd",
                    Toast.LENGTH_LONG
                ).show();
                    scope.launch {
                        delay(5); navController.navigate(Screen.Settings.route) {
                        popUpTo(Screen.Settings.route) {
                            inclusive = true
                        }
                    }
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Next",
                    modifier = Modifier
                        .size(24.dp)
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
    }

}