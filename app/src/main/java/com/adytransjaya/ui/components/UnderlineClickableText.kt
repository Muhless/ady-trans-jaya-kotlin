import android.content.Context
import android.content.Intent
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.net.toUri

@Suppress("ktlint:standard:function-naming")
@Composable
fun UnderlinedClickableText(
    label: String,
    pickupLat: Double? = null,
    pickupLng: Double? = null,
    deliveryLat: Double? = null,
    deliveryLng: Double? = null,
    context: Context = LocalContext.current,
) {
    ClickableText(
        text =
            AnnotatedString(
                label,
                spanStyle =
                    SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    ),
            ),
        onClick = {
            val lat = deliveryLat ?: pickupLat
            val lng = deliveryLng ?: pickupLng

            val uri =
                "https://www.google.com/maps/dir/?api=1&destination=$lat,$lng&travelmode=driving".toUri()

            val intent =
                Intent(Intent.ACTION_VIEW, uri).apply {
                    setPackage("com.google.android.apps.maps")
                }
            context.startActivity(intent)
        },
    )
}
