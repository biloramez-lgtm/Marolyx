# 🚀 دليل رفع المشروع على GitHub

## المرحلة الأولى: إعداد GitHub

### 1️⃣ إنشاء مستودع جديد
```
اذهب إلى: https://github.com/new
```

**الإعدادات:**
- **Repository name**: `Games-Tools-Android` أو `Marolyx`
- **Description**: Games Tools Android App - أداة ألعاب مبنية بـ Kotlin و Jetpack Compose
- **Public/Private**: اختر حسب رغبتك
- **Initialize this repository with**: 
  - [ ] Add a README file (سنضيفه نحن)
  - [ ] Add .gitignore (سنضيفه نحن)
  - [ ] Choose a license (اختياري)
- اضغط **Create repository**

---

## المرحلة الثانية: إضافة الملفات على GitHub مباشرة

### الطريقة الأولى: من واجهة GitHub (الأسهل)

#### 1. أضف ملف README
```
1. انقر "Add file" → "Create new file"
2. اسم الملف: README.md
3. انسخ محتوى README.md من الملفات المرفقة
4. اضغط "Commit new file"
```

#### 2. أضف ملف .gitignore
```
1. انقر "Add file" → "Create new file"
2. اسم الملف: .gitignore
3. انسخ محتوى .gitignore من الملفات المرفقة
4. اضغط "Commit new file"
```

#### 3. أضف ملف settings.gradle.kts
```
1. انقر "Add file" → "Create new file"
2. اسم الملف: settings.gradle.kts
3. انسخ محتوى الملف
4. اضغط "Commit new file"
```

#### 4. أضف ملف build.gradle.kts (الجذر)
```
1. انقر "Add file" → "Create new file"
2. اسم الملف: build.gradle.kts
3. انسخ محتوى الملف
4. اضغط "Commit new file"
```

#### 5. أضف ملف gradle.properties
```
1. انقر "Add file" → "Create new file"
2. اسم الملف: gradle.properties
3. انسخ محتوى الملف
4. اضغط "Commit new file"
```

---

## المرحلة الثالثة: إنشاء مجلدات التطبيق

### إنشاء مجلد app
```
1. انقر "Add file" → "Create new file"
2. اسم الملف: app/build.gradle.kts
3. انسخ محتوى app/build.gradle.kts
4. اضغط "Commit new file"
```

### إضافة ملفات UI

#### Screens
```
1. انقر "Add file" → "Create new file"
2. اسم الملف: app/src/main/kotlin/com/example/gamestools/ui/screens/HomeScreen.kt
3. انسخ محتوى HomeScreen.kt
4. اضغط "Commit new file"
```

**كرر العملية لـ:**
- `DiceScreen.kt`
- `TimerScreen.kt`
- `ScreensPlaceholder.kt`

#### Theme
```
أضف الملفات التالية تحت app/src/main/kotlin/com/example/gamestools/ui/theme/:
- Color.kt
- Theme.kt
- Type.kt
```

### ملفات أساسية
```
أضف الملفات التالية:

app/src/main/kotlin/com/example/gamestools/
- MainActivity.kt

app/src/main/
- AndroidManifest.xml
```

---

## الطريقة الثانية: استخدام Git من سطر الأوامر (الأفضل)

إذا كنت تريد رفع المشروع بسرعة من جهازك:

### 1. فتح Terminal/Command Prompt

### 2. انتقل إلى مجلد المشروع
```bash
cd /path/to/your/project
```

### 3. هيّئ Git
```bash
git init
git config user.name "اسمك"
git config user.email "بريدك الإلكتروني"
```

### 4. أضف جميع الملفات
```bash
git add .
```

### 5. أول commit
```bash
git commit -m "Initial commit: Create Marolyx Games Tools Android App"
```

### 6. أضف remote repository
```bash
git remote add origin https://github.com/username/Games-Tools-Android.git
```

### 7. رفع الملفات
```bash
git branch -M main
git push -u origin main
```

---

## 🎯 البنية النهائية على GitHub

بعد إكمال الخطوات، يجب أن تحصل على هذه البنية:

```
Games-Tools-Android/
│
├── README.md
├── .gitignore
├── settings.gradle.kts
├── build.gradle.kts
├── gradle.properties
│
└── app/
    ├── build.gradle.kts
    │
    └── src/
        └── main/
            ├── AndroidManifest.xml
            │
            └── kotlin/com/example/gamestools/
                ├── MainActivity.kt
                │
                └── ui/
                    ├── screens/
                    │   ├── HomeScreen.kt
                    │   ├── DiceScreen.kt
                    │   ├── TimerScreen.kt
                    │   └── ScreensPlaceholder.kt
                    │
                    └── theme/
                        ├── Color.kt
                        ├── Theme.kt
                        └── Type.kt
```

---

## ✅ التحقق من نجاح الرفع

1. اذهب إلى صفحة مستودعك على GitHub
2. تأكد من رؤية جميع الملفات
3. تحقق من وجود ملف README.md في الصفحة الرئيسية
4. انقر على الملفات للتحقق من محتوياتها

---

## 📱 تشغيل المشروع محلياً

بعد رفع المشروع:

```bash
# استنساخ المستودع
git clone https://github.com/username/Games-Tools-Android.git

# فتح في Android Studio
# اختر File > Open وحدد مجلد المشروع

# انتظر حتى ينتهي Gradle من التحميل

# اضغط Run أو استخدم
./gradlew run
```

---

## 🔄 العمل المستقبلي

بعد الرفع الأولي، عند إضافة تغييرات جديدة:

```bash
# أضف التغييرات
git add .

# قم بـ commit
git commit -m "وصف التغييرات"

# رفع التغييرات
git push origin main
```

---

## 💡 نصائح مهمة

1. **قسم الملفات الكبيرة**: إذا كان لديك ملفات كبيرة، استخدم Git LFS
2. **استخدم .gitignore**: تأكد من تجاهل الملفات غير المهمة
3. **رسائل commit واضحة**: اكتب رسائل توضح ما فعلته
4. **update README**: عدّل README.md مع تقدم المشروع
5. **استخدم Branches**: للميزات الجديدة استخدم فرع منفصل

---

## 🆘 حل المشاكل الشائعة

### المشكلة: "fatal: Could not read from remote repository"
```bash
# تحقق من الاتصال
git remote -v

# إعد تعيين الـ remote
git remote remove origin
git remote add origin https://github.com/username/repo.git
```

### المشكلة: ملفات كبيرة جداً
```bash
# تحقق من حجم الملفات
git ls-files -lhS | head -10

# استخدم .gitignore لتجاهلها
```

---

**مبروك! 🎉 لقد رفعت مشروعك بنجاح على GitHub!**

