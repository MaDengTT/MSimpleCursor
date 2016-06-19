# MSimpleCursor
![](https://github.com/MaDengTT/MSimpleCursor/blob/master/device.png)

#Integration

Add jitpack.io repository to your root build.gradle:
```java
allprojects {
 repositories {
    jcenter()
    maven { url "https://jitpack.io" }
 }
}
```
Add the dependency to your module build.gradle:
```java
	dependencies {
	        compile 'com.github.MaDengTT:MSimpleCursor:0.2'
	}
```

# API
```xml
        app:space_width="10dp"
        app:cursor_size="10"
        app:solid_color="@color/colorAccent"
        app:hollow_color="@color/colorPrimary"
        app:redius="5dp"
```
