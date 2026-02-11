# 📱 دليل الرفع على GitHub من Termux

## 📥 الخطوة الأولى: تحضير الهاتف

### 1️⃣ تثبيت Termux
```
1. اذهب إلى Google Play Store
2. ابحث عن "Termux"
3. اضغط Install
4. افتح التطبيق
```

### 2️⃣ تثبيت البرامج المطلوبة
```bash
# تحديث Termux
pkg update && pkg upgrade

# تثبيت Git
pkg install git

# تثبيت Nano (محرر نصوص - اختياري)
pkg install nano
```

---

## 📥 الخطوة الثانية: استقبال الملفات

```bash
# أنشئ مجلد للمشروع
mkdir -p ~/marolyx
cd ~/marolyx

# انقل ملف Marolyx-Complete.zip وفك الضغط:
unzip Marolyx-Complete.zip
```

---

## 🔧 الخطوة الثالثة: تكوين Git

```bash
# تعيين اسمك
git config --global user.name "اسمك"

# تعيين بريدك
git config --global user.email "your-email@gmail.com"
```

---

## 📖 الخطوة الرابعة: إنشاء مستودع على GitHub

```
1. اذهب إلى: https://github.com/new
2. اكتب اسم: Games-Tools-Android
3. اختر Public
4. اضغط Create
```

---

## 🚀 الخطوة الخامسة: الرفع

### الطريقة السهلة (استخدام Script):

```bash
cd ~/marolyx
chmod +x upload-to-github.sh
./upload-to-github.sh
```

### الطريقة اليدوية:

```bash
cd ~/marolyx
git init
git add .
git commit -m "Initial commit: Marolyx Games Tools"
git remote add origin https://github.com/YOUR_USERNAME/Games-Tools-Android.git
git branch -M main
git push -u origin main
```

---

## 🔐 بيانات الاعتماد

عند الرفع سيطلب:
- **Username**: اسم مستخدمك على GitHub
- **Password**: Personal Access Token (ليس كلمة المرور)

### إنشاء Personal Access Token:
```
1. اذهب: https://github.com/settings/tokens
2. اضغط: Generate new token
3. اختر: repo (كل الخيارات تحته)
4. اضغط: Generate token
5. انسخ التوكن والصقه كـ Password
```

---

## ✅ أوامر مهمة

```bash
pwd                    # عرض المجلد الحالي
cd ~/marolyx          # الذهاب للمشروع
git status            # حالة المستودع
git log --oneline     # السجلات
git add .             # إضافة التغييرات
git commit -m "..."   # حفظ التغييرات
git push              # رفع على GitHub
git pull              # تحديث من GitHub
```

---

## 🎉 تم! المشروع على GitHub الآن!

