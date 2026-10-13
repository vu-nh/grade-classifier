
package com.example.grade_classifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = false

        setContent {
            GradeClassifier()
        }
    }
}

val score = 8.5

fun classifyGrade(score: Double): String {
    if (score < 0 || score > 10) {
        return "Không hợp lệ"
    } else if (score >= 8) {
        return "Giỏi"
    } else if (score >= 6.5) {
        return "Khá"
    } else if (score >= 5) {
        return "Trung bình"
    } else {
        return "Yếu"
    }
}

fun gradeColor(score: Double): Color {
    if (score < 0 || score > 10) {
        return Color.Gray
    } else if (score >= 8) {
        return Color(0xFF38794A)
    } else if (score >= 6.5) {
        return Color(0xFF3465A4)
    } else if (score >= 5) {
        return Color(0xFFFF9800)
    } else {
        return Color.Red
    }
}

@Composable
fun GradeClassifier() {
    val testScores = listOf(9.0, 7.0, 5.5, 3.0, 11.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F3F8))
    ) {
        // Tiêu đề
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF304878))
                .padding(
                    start = 20.dp,
                    top = 58.dp,
                    bottom = 18.dp
                )
        ) {
            Text(
                text = "Xếp loại học lực",
                fontSize = 24.sp,
                color = Color.White
            )
        }

        // Card hiển thị điểm chính
        Card(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp,
                    vertical = 12.dp
                )
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Điểm trung bình",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Text(
                    text = score.toString(),
                    fontSize = 58.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF252A32)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFC8E6C9)),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = classifyGrade(score),
                        color = gradeColor(score),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Tiêu đề kiểm tra thêm
        Text(
            text = "KIỂM TRA THÊM",
            modifier = Modifier.padding(
                start = 20.dp,
                top = 4.dp,
                bottom = 8.dp
            ),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        // Card chứa các điểm kiểm tra
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            ) {
                for (testScore in testScores) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Điểm ${testScore.toString().removeSuffix(".0")}",
                            fontSize = 14.sp,
                            color = Color(0xFF252A32)
                        )

                        Text(
                            text = classifyGrade(testScore),
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .background(
                                    when (testScore) {
                                        9.0 -> Color(0xFFE4F2E7)
                                        7.0 -> Color(0xFFE5EFFB)
                                        5.5 -> Color(0xFFFFF1D7)
                                        3.0 -> Color(0xFFFBE8E6)
                                        else -> Color(0xFFECEEF1)
                                    }
                                )
                                .padding(
                                    horizontal = 8.dp,
                                    vertical = 4.dp
                                ),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = gradeColor(testScore)
                        )
                    }

                    if (testScore != testScores.last()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color(0xFFEEEEEE))
                        ) {}
                    }
                }
            }
        }
    }
}
