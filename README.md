#ScienceView

ScienceView is a library to have MathJax support in Android. In ScienceView you can easily customize your HTML and CSS styles.
There is also onclick listener by which you can detect onClick event on HTML tag.\n
ScienceView is written in Kotlin. And this is kotlin only library.

### Getting Started

* First add the dependency in your build.gradle file.

```groovy
implementation 'io.github.zoha131.scienceview:scienceview:0.0.1'
```

* Then add ScienceView in your xml file

```xml
<io.github.zoha131.scienceview.ScienceView
        android:id="@+id/scienceView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

* From Activity of Fragment reference the ScienceView. Note: ScienceView is generic type. The type will be passed when you define HTML model for the view
```kotlin
 val scienceView:ScienceView<String> = findViewById(R.id.scienceView)
```

* (Optional) Add your custom one or more CSS file from res/raw folder of your Android project
```kotlin
scienceView.addCSS(R.raw.style)
```

* Add HTML model and placed your data from the passed parameter. here you can also placed onclick event in the HTML tag
```kotlin
 scienceView.setModel { model -> //Here model is String type from the generic
            """  
                <div onclick="onClick('div',1)"> 
                  <p>$model</p>
                </div>

            """.trimIndent() //Here in the onclick event your have to pass two String type parameter in onClick(...)
        }
```

* Add onClick listener to get the Click event from HTML
```kotlin
        scienceView.setScienceClickListener { tag, data ->
            Log.d("JS_CLICK_LISTENER", "$tag $data")
            //Here first one is for tag information and 
            //second one is for data
        }
```

* And Finally set the data to get the text rendered in MathJax
```kotlin
scienceView.setData("${'$'}${'$'}\\\\ce{x Na(NH4)HPO4 ->[\\\\Delta] (NaPO3)_x + x NH3 ^ + x H2O}${'$'}${'$'}")
```

