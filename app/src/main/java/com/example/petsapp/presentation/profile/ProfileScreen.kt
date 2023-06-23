package com.example.petsapp.presentation.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.presentation.view.common.Toolbar
import com.example.petsapp.MainActivity
import com.example.petsapp.R
import com.example.petsapp.utils.Response


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    toolbarDestinations: List<() -> Unit>
) {
    var username: String? = null
    var login: String? = null
    var email: String? = null
    if (viewModel.profileState.value is Response.Success) {
    username =
        (viewModel.profileState.value as Response.Success<ProfileData>).data?.username.toString()
    login =
            (viewModel.profileState.value as Response.Success<ProfileData>).data?.login.toString()
    email =
            (viewModel.profileState.value as Response.Success<ProfileData>).data?.email.toString()
    }
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.White)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
                .background(color = Color.White)
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ProfileHeader()
            CircleImg()
            ProfileFields(username, login, email)
        }
        Toolbar(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedItem = 3,
            toolbarDestinations = toolbarDestinations
        )

    }
}

@Composable
fun ProfileHeader() {
    Column(
        Modifier
            .padding(40.dp)
    ) {
        Text(
            text = "Профиль",
            fontSize = 31.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
    }
}

@Composable
fun ProfileFields(username: String?, login: String?, email: String?) {
    Column() {
        Box(
            Modifier
                .border(2.dp, color = Color.Black, CircleShape)
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Username",
                Modifier
                    .fillMaxHeight()
                    .padding(),
                tint = Color.Black
            )
            Text(
                text = "$username",
                Modifier.padding(30.dp,0.dp ,0.dp ,0.dp ),
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            Modifier
                .border(2.dp, color = Color.Black,CircleShape)
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Username",
                Modifier
                    .fillMaxHeight()
                    .padding(),
                tint = Color.Black
            )
            Text(
                text = "$login",
                Modifier.padding(30.dp,0.dp ,0.dp ,0.dp ),
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Box(
            Modifier
                .border(2.dp, color = Color.Black, CircleShape)
                .padding(5.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Username",
                Modifier
                    .fillMaxHeight()
                    .padding(),
                tint = Color.Black
            )
            Text(
                text = "$email",
                Modifier.padding(30.dp,0.dp ,0.dp ,0.dp ),
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ProfileFooter() {

}


//@Preview
//@Composable
//fun ProfileScreenPreview() {
//    ProfileScreen(toolbarDestinations = listOf({}, {}, {}, {}))
//
//}

@Composable
fun CircleImg() {

    Column(

        // we are using column to align our imageview
        // to center of the screen.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(40.dp),

        // below line is used for specifying
        // vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for specifying
        // horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        // creating a card for creating a circle image view.
        Card(
            // below line is use to add size to our image view and
            // test tag is use to add tag to our image.
//            modifier = Modifier.preferredSize(100.dp).testTag(tag = "circle"),

            // below line is use to
            // add shape to our image view.
            shape = CircleShape,

            // below line is use to add
            // elevation to our image view.
            elevation = 12.dp
        ) {
            // below line we are creating a new image.
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)                       // clip to the circle shape
                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
            )
        }
    }
}


data class ProfileData(
    val username: String? = null,
    val login: String? = null,
    val email: String? = null
)