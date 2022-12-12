import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.example.neglectapp.util.ButtonType


@Composable
fun NeglectButton(
    type: ButtonType,
    modifier: Modifier,
    label: String = "",
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
    ) {
        when (type) {
            ButtonType.TEXT -> {
                CompactChip(onClick = { onClick() }, label = { Text(label) }, modifier = Modifier.width(500.dp))
            }
            ButtonType.ICON -> {
                Button(onClick = { onClick() }) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Accept",
                        modifier = Modifier.size(width = 35.dp, height = 35.dp)
                    )
                }
            }
        }
    }
}