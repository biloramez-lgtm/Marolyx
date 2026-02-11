# 📖 دليل تطبيق Marolyx الكامل

## 📚 جدول المحتويات

1. [ملفات البناء](#ملفات-البناء)
2. [ملفات التطبيق الأساسية](#ملفات-التطبيق-الأساسية)
3. [واجهة المستخدم](#واجهة-المستخدم)
4. [الموضوع والتصميم](#الموضوع-والتصميم)
5. [البنية المعمارية](#البنية-المعمارية)

---

## ملفات البناء

### `settings.gradle.kts`
**الغرض**: إعداد المستودع وتحديد الوحدات الفرعية

```kotlin
// يحدد المستودعات (repositories) لتحميل المكتبات
repositories {
    google()
    mavenCentral()
}

// يقول Gradle عن الوحدات الموجودة في المشروع
include(":app")
```

**متى تعدله**: عند إضافة مكتبة جديدة أو وحدة جديدة

---

### `build.gradle.kts` (الجذر)
**الغرض**: تحديد الإعدادات المشتركة لجميع الوحدات

```kotlin
plugins {
    // المكتبات الأساسية للبناء
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}
```

**ما يحتويه**:
- إصدارات المكتبات الرئيسية
- الإعدادات المشتركة

---

### `app/build.gradle.kts`
**الغرض**: تكوين بناء تطبيق Android بالتفصيل

**الأقسام الرئيسية**:

#### 1. **Plugins**
```kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
```
تفعيل المكتبات اللازمة للتطبيق

#### 2. **Android Configuration**
```kotlin
android {
    namespace = "com.example.gamestools"  // معرف التطبيق الفريد
    compileSdk = 34                        // إصدار Android المستهدف
    
    defaultConfig {
        applicationId = "com.example.gamestools"  // المعرف في Google Play
        minSdk = 24      // أقدم إصدار Android مدعوم
        targetSdk = 34   // أحدث إصدار Android
        versionCode = 1  // رقم الإصدار (للتحديثات)
        versionName = "1.0.0"  // اسم الإصدار
    }
}
```

#### 3. **Compose Configuration**
```kotlin
buildFeatures {
    compose = true  // تفعيل Jetpack Compose
}

composeOptions {
    kotlinCompilerExtensionVersion = "1.5.3"  // إصدار Compose
}
```

#### 4. **Dependencies**
المكتبات المستخدمة:
- **androidx.core:core-ktx**: وظائف أساسية لـ Android
- **androidx.compose.ui**: مكونات واجهة Compose
- **androidx.compose.material3**: تصميم Material Design 3
- **androidx.lifecycle**: إدارة دورة الحياة
- **androidx.navigation:navigation-compose**: الملاحة بين الشاشات

---

### `gradle.properties`
**الغرض**: إعدادات الأداء والسلوك

```properties
org.gradle.caching=true      # تخزين النتائج المؤقتة
org.gradle.daemon=true       # تشغيل خدمة الخلفية
org.gradle.parallel=true     # البناء المتوازي
android.useAndroidX=true     # استخدام مكتبات AndroidX الحديثة
```

---

## ملفات التطبيق الأساسية

### `app/src/main/AndroidManifest.xml`
**الغرض**: وصف التطبيق والأذونات المطلوبة

```xml
<manifest>
    <!-- الأذونات المطلوبة -->
    
    <application>
        <!-- معلومات التطبيق -->
        
        <activity android:name=".MainActivity"
                  android:exported="true">
            <!-- النشاط الرئيسي -->
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
</manifest>
```

**العناصر الرئيسية**:
- `<manifest>`: جذر الملف
- `<application>`: معلومات التطبيق العامة
- `<activity>`: الشاشات/النشاطات
- `<intent-filter>`: تحديد الشاشة البدائية

---

### `MainActivity.kt`
**الغرض**: نقطة الدخول الرئيسية للتطبيق

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamesToolsTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    GamesToolsNavigation(navController)
                }
            }
        }
    }
}
```

**ما يفعله**:
1. ينث عن ComponentActivity (أساس الأنشطة الحديثة)
2. ينشئ واجهة Compose
3. يطبق الموضوع
4. ينشئ النظام الملاحة

---

## واجهة المستخدم

### `HomeScreen.kt`
**الغرض**: الشاشة الرئيسية للتطبيق

**المكونات**:
- عنوان التطبيق (Games Tools)
- عنوان فرعي بالعربية
- 4 أزرار للانتقال إلى الشاشات المختلفة

```kotlin
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(/* محتويات الشاشة */) {
        Text("Games Tools")  // العنوان الرئيسي
        
        HomeScreenButton(
            title = "Dice Roller",
            subtitle = "رمي النرد",
            onClick = { navController.navigate("dice") }
        )
        // ... أزرار أخرى
    }
}
```

---

### `DiceScreen.kt`
**الغرض**: شاشة رمي النرد

**المميزات**:
- اختيار نوع النرد (d6, d12, d20)
- اختيار عدد النرد (1-10)
- عرض النتائج الفردية والمجموع

```kotlin
@Composable
fun DiceScreen(navController: NavHostController) {
    var diceValue by remember { mutableStateOf(1) }
    var numberOfDice by remember { mutableStateOf(1) }
    var diceType by remember { mutableStateOf(6) }
    
    // عرض النتائج
    Button(onClick = {
        results = List(numberOfDice) { Random.nextInt(1, diceType + 1) }
    }) {
        Text("رمي النرد")
    }
}
```

---

### `TimerScreen.kt`
**الغرض**: مؤقت بسيط

**المميزات**:
- إدخال الدقائق والثواني
- تشغيل/إيقاف/إعادة تعيين
- عرض الوقت المتبقي

```kotlin
@Composable
fun TimerScreen(navController: NavHostController) {
    var timeInSeconds by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    
    // استخدام LaunchedEffect للتحديث المستمر
    LaunchedEffect(isRunning) {
        while (isRunning && timeInSeconds > 0) {
            delay(1000)
            timeInSeconds--
        }
    }
}
```

---

## الموضوع والتصميم

### `Color.kt`
**الغرض**: تعريف جميع الألوان المستخدمة

```kotlin
// ألوان الوضع الفاتح
val PrimaryLight = Color(0xFF6750A4)
val SecondaryLight = Color(0xFF625B71)
val ErrorLight = Color(0xFFB3261E)

// ألوان الوضع الداكن
val PrimaryDark = Color(0xFFD0BCFF)
val SecondaryDark = Color(0xFFCCC7F0)
val ErrorDark = Color(0xFFF2B8B5)
```

**الفوائد**:
- تطبيق موحد للألوان
- سهولة تغيير الموضوع
- دعم الوضع الليلي

---

### `Theme.kt`
**الغرض**: تطبيق الموضوع على كل التطبيق

```kotlin
@Composable
fun GamesToolsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```

**ما يفعله**:
- يكتشف تلقائياً الوضع الليلي/الفاتح
- يطبق الألوان والخطوط

---

### `Type.kt`
**الغرض**: تعريف أنماط النصوص

```kotlin
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp
    ),
    headlineLarge = TextStyle(...),
    titleLarge = TextStyle(...),
    // ... المزيد من الأنماط
)
```

**الفوائد**:
- اتساق في حجم وشكل النصوص
- سهولة الصيانة

---

## البنية المعمارية

### تدفق البيانات

```
╔═══════════════════════╗
║   MainActivity.kt      │  ← نقطة الدخول
╚═════════╤═════════════╝
          │
          ▼
╔═══════════════════════╗
║  GamesToolsNavigation │  ← إدارة الملاحة
╚═════════╤═════════════╝
          │
    ┌─────┼─────┬────────┬───────────┐
    ▼     ▼     ▼        ▼           ▼
┌────┐ ┌────┐ ┌────┐ ┌────────┐ ┌──────────┐
│Home│ │Dice│ │Timer│ │Spinner│ │Probability
└────┘ └────┘ └────┘ └────────┘ └──────────┘
    │     │     │        │           │
    └─────┴─────┴────────┴───────────┘
          │
          ▼
╔═══════════════════════╗
║  GamesToolsTheme      │  ← التصميم والألوان
╚═══════════════════════╝
```

### نمط MVVM (عند الحاجة)

```
┌─────────────────┐
│   UI Layer      │ (Screens)
├─────────────────┤
│  ViewModel      │ (State Management)
├─────────────────┤
│  Repository     │ (Data Management)
├─────────────────┤
│  Data Layer     │ (Local/Remote Data)
└─────────────────┘
```

---

## 🔄 دورة حياة النشاط (Activity Lifecycle)

```
onCreate()  ──→  onStart()  ──→  onResume()  ──→  Running
                                    ▲
                                    │
                    onPause()  ←─────┴─────→  onStop()
                        │                        │
                        └────→  onDestroy()  ←──┘
```

---

## 💾 الحفظ والاسترجاع (State Preservation)

### استخدام `remember`
```kotlin
var count by remember { mutableStateOf(0) }
```
- يحفظ الحالة طوال عمر الـ Composition

### استخدام `rememberSaveable`
```kotlin
var count by rememberSaveable { mutableStateOf(0) }
```
- يحفظ الحالة حتى عند إعادة إنشاء Activity

---

## 🎨 نظام التصميم

### المكونات الأساسية

1. **Color Scheme**: نظام الألوان
2. **Typography**: نظام الخطوط
3. **Shapes**: أشكال المكونات
4. **Elevation**: الظلال والعمق

---

## 📦 الاعتماديات الرئيسية

| المكتبة | الإصدار | الغرض |
|--------|--------|-------|
| Compose UI | 1.5.4 | بناء الواجهات |
| Compose Material3 | 1.1.1 | مكونات Material Design |
| Navigation Compose | 2.7.5 | الملاحة |
| ViewModel | 2.6.2 | إدارة الحالة |
| Lifecycle | 2.6.2 | دورة الحياة |

---

## ✅ قائمة التحقق للتطوير

- [ ] فهم نقطة الدخول (MainActivity)
- [ ] فهم نظام الملاحة
- [ ] تعديل الألوان في Color.kt
- [ ] إضافة شاشات جديدة
- [ ] استخدام ViewModel لحفظ البيانات
- [ ] اختبار على أجهزة مختلفة

---

**آخر تحديث**: فبراير 2026

