import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun helpText() {
    val context = LocalContext.current
    val phoneNumber = "628871165551"
    val url = "https://api.whatsapp.com/send?phone=$phoneNumber"

    val annotatedText =
        buildAnnotatedString {
            append("Butuh bantuan ? hubungi admin ")
            pushStringAnnotation(tag = "WA_LINK", annotation = url)
            pushStyle(
                SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                ),
            )
            append("disini")
            pop() // style
            pop() // annotation
        }

    ClickableText(
        text = annotatedText,
        style = TextStyle(fontSize = 13.sp),
        onClick = { offset ->
            annotatedText
                .getStringAnnotations("WA_LINK", offset, offset)
                .firstOrNull()
                ?.let {
                    val intent =
                        Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(url)
                            setPackage("com.whatsapp")
                        }
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast
                            .makeText(context, "WhatsApp tidak ditemukan", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        },
    )
}
