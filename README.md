# GoogleMapsDistanceTracker
<h3>Android build
<ul>
<li> compileSdk 30</li>
<li> minSdk 21</li>
<li> targetSdk 30</li>
<ul>

#

    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'com.google.android.gms:play-services-maps:17.0.1'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    // Navigation Component
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'

    // Lifecycle
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"

    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0'

    // Fused Location
    implementation 'com.google.android.gms:play-services-location:18.0.0'

    // Util
    implementation 'com.google.maps.android:android-maps-utils:2.2.0'

    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.37"
    kapt "com.google.dagger:hilt-android-compiler:2.36"

    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt "androidx.hilt:hilt-compiler:1.0.0"

    // Easy Permissions
    implementation 'com.vmadalin:easypermissions-ktx:1.0.0'

