package com.example.cameraandgallary

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform