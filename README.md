[![](https://jitpack.io/v/tobiasschuerg/android-prefix-suffix-edit-text.svg)](https://jitpack.io/#tobiasschuerg/android-prefix-suffix-edit-text)

# android-prefix-suffix-edit-text
EditText with support for non editable prefix and suffix.


![Sample Screenshot](https://github.com/tobiasschuerg/android-prefix-suffix-edit-text/raw/master/screenshots/sample.png)


## Howto?
Either directly via xml:
```xml
<com.tobiasschuerg.prefixsuffix.PrefixSuffixEditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:inputType="numberDecimal"
    android:text="24"
    app:prefix="Age: "
    app:suffix=" years"
    android:textColorHint="@color/colorPrimary"/>
```

or programmatically in code:

```kotlin
// just a prefix:
edit_text_dollar.prefix = "$"


// suffix with color
edit_text_euro.suffix = "€"
edit_text_euro.setHintTextColor(Color.GREEN)


// both, prefix and suffix
edit_text_age.prefix = "Age: "
edit_text_age.suffix = " years"
```
See  [sample code](https://github.com/tobiasschuerg/android-prefix-suffix-edit-text/blob/master/app/src/main/java/com/tobiasschuerg/sample/MainActivity.kt).

### Setting the color

The color can be set via xml:
```xml
android:textColorHint="@color/colorPrimary"
```
and programmatically
```
prefixSuffixView.setHintTextColor(Color.GREEN)
```

## Add as Library
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

