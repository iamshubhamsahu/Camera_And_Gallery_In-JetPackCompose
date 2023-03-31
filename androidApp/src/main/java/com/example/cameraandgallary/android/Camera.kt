package com.example.cameraandgallary.android

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Camera() {
    val cameraPermissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)
    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) {
            bitmap = it
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            if (cameraPermissionState.status.isGranted) {
                launcher.launch()
            }
            else {
                cameraPermissionState.launchPermissionRequest()
            }
        }) {
            Text(text = "Pick Image from Camera")
        }
        Spacer(modifier = Modifier.height(20.dp))
        bitmap?.let {
            Image(
                bitmap = bitmap?.asImageBitmap()!!,
                contentDescription = "",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}