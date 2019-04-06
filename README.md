[![](https://jitpack.io/v/tobiasschuerg/android-prefix-suffix-edit-text.svg)](https://jitpack.io/#tobiasschuerg/android-prefix-suffix-edit-text)

# android-prefix-suffix-edit-text
EditText with support for non editable prefix and suffix.

```kotlin
// just a prefix:
edit_text_dollar.prefix = "$"


// just a suffix
edit_text_euro.suffix = "€"


// both, prefix and suffix
edit_text_age.prefix = "Age: "
edit_text_age.suffix = " years"
```
See  [sample code](https://github.com/tobiasschuerg/android-prefix-suffix-edit-text/blob/master/app/src/main/java/com/tobiasschuerg/sample/MainActivity.kt).

![Sample Screenshot](https://github.com/tobiasschuerg/android-prefix-suffix-edit-text/raw/master/screenshots/sample.png)

# Add as Library
Step 1. Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
	dependencies {
		implementation 'com.github.tobiasschuerg:android-prefix-suffix-edit-text:version'
	}
```
Replace `version` by the [latest release](https://github.com/tobiasschuerg/android-prefix-suffix-edit-text/releases).

