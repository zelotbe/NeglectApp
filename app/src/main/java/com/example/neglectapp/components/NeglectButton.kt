import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.example.neglectapp.util.ButtonType


@Composable
fun NeglectButton(
    modifier: Modifier,
    contentDescription: String = "Accept",
    icon: ImageVector = Icons.Default.Check,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
){

//        when (type) {
//            ButtonType.TEXT -> {
//                CompactChip(onClick = { onClick() }, label = { Text(label) }, modifier = Modifier.width(200.dp))
//            }
//            ButtonType.ICON -> {
                Button(onClick = { onClick() }, modifier = Modifier.fillMaxSize()) {
                    Icon(
                        icon,
                        contentDescription = "Accept",
                        modifier = Modifier.size(width = 35.dp, height = 35.dp)
                    )
                }
//            }
//        }
    }