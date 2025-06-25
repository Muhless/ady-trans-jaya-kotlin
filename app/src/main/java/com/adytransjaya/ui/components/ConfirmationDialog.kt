import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Suppress("ktlint:standard:function-naming")
@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String = "Konfirmasi",
    message: String = "Apakah Anda yakin?",
    confirmText: String = "Ya",
    dismissText: String = "Batal",
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(confirmText, color = Color.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(dismissText, color = Color.Black)
                }
            },
        )
    }
}
