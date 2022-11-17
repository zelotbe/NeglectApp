import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.example.neglectapp.presentation.util.ButtonType


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
            .clickable { onClick() }
            .then(modifier)
    ) {
        when (type) {
            ButtonType.TEXT -> {
                CompactChip(onClick = { /*TODO*/ }, label = { Text(label) })
            }
            ButtonType.ICON -> {
                Button(onClick = { /*TODO*/ }) {
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