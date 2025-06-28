package com.adytransjaya.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adytransjaya.ui.screen.help.EmergencyContactCard
import com.adytransjaya.ui.screen.help.HelpNote
import com.adytransjaya.ui.theme.AppColors

data class EmergencyContact(
    val title: String,
    val number: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
)

@Composable
fun helpScreen(navController: NavController) {
    val emergencyContacts =
        listOf(
            EmergencyContact(
                title = "Jasa Marga",
                number = "14080",
                description = "Layanan Derek Tol Jasa Marga",
                icon = Icons.Default.DirectionsCar,
                color = Color(0xFF2E7D32),
            ),
            EmergencyContact(
                title = "Polisi",
                number = "110",
                description = "Kepolisian Negara Republik Indonesia",
                icon = Icons.Default.Security,
                color = Color(0xFF1976D2),
            ),
            EmergencyContact(
                title = "Pemadam Kebakaran",
                number = "113",
                description = "Dinas Pemadam Kebakaran",
                icon = Icons.Default.LocalFireDepartment,
                color = Color(0xFFD32F2F),
            ),
            EmergencyContact(
                title = "Ambulans/Medis",
                number = "118",
                description = "Layanan Ambulans dan Medis Darurat",
                icon = Icons.Default.LocalHospital,
                color = Color(0xFF388E3C),
            ),
            EmergencyContact(
                title = "Posko Bencana",
                number = "129",
                description = "Pusat Penanganan Bencana",
                icon = Icons.Default.Warning,
                color = Color(0xFFE65100),
            ),
        )

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(AppColors.background)
                .padding(16.dp),
    ) {
        Card(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            colors =
                CardDefaults.cardColors(
                    containerColor = AppColors.BrandBlue,
                ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    imageVector = Icons.Default.Help,
                    contentDescription = "Help Icon",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Bantuan Darurat",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Nomor-nomor penting yang perlu diketahui",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(emergencyContacts) { contact ->
                EmergencyContactCard(contact = contact)
            }

            item {
                HelpNote()
            }
        }
    }
}
