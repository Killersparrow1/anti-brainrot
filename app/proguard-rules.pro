# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep generic methods for serialization
-keepclassmembers class * {
    @kotlin.Metadata *;
}

# Keep Room entities
-keep class ****RoomDatabase { *; }
-keep class ****Entity { *; }
-keep class ****Dao { *; }

# Keep Hilt generated classes
-keep class dagger.hilt.** { *; }
-keep class ****Hilt_* { *; }

# Keep Compose runtime
-keep class androidx.compose.runtime.** { *; }

# Keep Glance widget classes
-keep class androidx.glance.** { *; }
-keep class ****WidgetReceiver { *; }

# Keep Firebase classes
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Keep Facebook SDK
-keep class com.facebook.** { *; }

# Keep RevenueCat
-keep class com.revenuecat.purchases.** { *; }

# Keep Razorpay
-keep class com.razorpay.** { *; }

# Keep Kotlin coroutines
-keepclassmembers class * {
    @kotlinx.coroutines.** *;
}

# Keep Moshi
-keep class com.squareup.moshi.** { *; }

# Keep OkHttp
-keep class okhttp3.** { *; }

# Keep Coil
-keep class coil3.** { *; }

# Keep WorkManager
-keep class androidx.work.** { *; }

# Keep DataStore
-keep class androidx.datastore.** { *; }

# Keep Biometric
-keep class androidx.biometric.** { *; }

# Keep Navigation
-keep class androidx.navigation.** { *; }

# Keep Lifecycle
-keep class androidx.lifecycle.** { *; }

# Keep Kotlinx Serialization
-keep class kotlinx.serialization.** { *; }

# Keep Play Services Auth
-keep class com.google.android.gms.auth.** { *; }

# Suppress warnings
-dontwarn com.google.android.gms.**
-dontwarn com.google.firebase.**
-dontwarn com.facebook.**
-dontwarn com.revenuecat.purchases.**
-dontwarn com.razorpay.**
-dontwarn kotlinx.coroutines.**
-dontwarn okhttp3.**
-dontwarn coil3.**