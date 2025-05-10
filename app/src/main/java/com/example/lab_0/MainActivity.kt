package com.example.lab_0

import android.R.attr.rotation
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.core.graphics.rotationMatrix
import com.example.lab_0.data.Course
import com.example.lab_0.data.CourseDataProvider
import com.example.lab_0.ui.theme.Lab0Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab0Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(innerPadding)
                }
            }
        }
    }
}

@Composable
fun MyApp(innerPaddingValues: PaddingValues, courses: List<Course> =CourseDataProvider.courses.take(8)
) {
    Surface {
        LazyColumn(Modifier
            .padding(innerPaddingValues)
            .padding(8.dp)) {
            itemsIndexed(courses){ index, it -> CourseCard(it)}
        }
    }

}

@Composable
fun CourseCard(course: Course) {
    var expanded = rememberSaveable {mutableStateOf(false)}
    var expandedPadding = animateDpAsState(
        if (expanded.value)32.dp else 0.dp,
                animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )
    )

    Card (
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 12.dp ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Row(modifier = Modifier
            .padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = expandedPadding.value.coerceAtLeast(0.dp))
                .animateContentSize()) {
                Text(text = course.title, style = MaterialTheme.typography.titleLarge)
                Row {
                    Text(text = course.code, modifier = Modifier.weight(1f))
                    Text(text = "Credit Hour: ${course.creditHours}")
                }
                Column {
                    if (expanded.value) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = course.description, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Prerequisites: ${course.prerequisites}", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
            IconButton(onClick = { expanded.value = !expanded.value }) {
                Icon(
                    imageVector = if (!expanded.value) Icons.Filled.ArrowDropDown else Icons.Filled.Close,
                    contentDescription = if (expanded.value) "Show less" else "Show more"
                )
            }
        }

    }
}


@Preview(showBackground = true, widthDp = 400)
@Composable
fun GreetingPreview1() {
    Lab0Theme {
        MyApp(PaddingValues(), courses = CourseDataProvider.courses.take(3))
    }
}

@Preview(
    showBackground = true,
    widthDp = 400,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode Preview"
)
@Composable
fun GreetingPreviewDark() {
    Lab0Theme {
        MyApp(PaddingValues(), courses = CourseDataProvider.courses.take(3))
    }
}

