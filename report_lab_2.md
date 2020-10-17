# Лабораторная работа №2. Activity Lifecycle. Alternative resources.
### Цели
* Ознакомиться с жизненным циклом Activity
* Изучить основные возможности и свойства alternative resources

### Задачи

#### Задача 1. Activity

**Задание:** продемонстрируйте жизненный цикл Activity на любом нетривиальном примере.

**Решение:** 
Пример №1. Использование ADB для экстренного выключения приложения.
___
ADB (Android Debug Bridge)  - инструмент командной строки, который позволяет взаимодействовать с работающим эмулятором. Благодаря ADB была смоделирована ситуация, при которой ОС выключает приложение в фоновом режиме (например, если происходит нехватка RAM). Была выполнена команда kill, которая остановила процесс, как если бы он был остановлен ОС

*Рис. 1.1 Команда ADB*

![image_2_1](https://github.com/Julia0028/android_lab_2/blob/master/report_png/2_1.png)

Как можно увидеть ниже, после выполнения команды ОС "тихо" уничтожило процесс в фоновом режиме. Если пользователь переключится на другое приложение, а затем вернется к данному, оно перезапустится.

*Рис. 1.2 Жизненный цикл приложения до использования команды*

![image_1](https://github.com/Julia0028/android_lab_2/blob/master/report_png/1.png)

*Рис. 1.3 и Рис. 1.4 Перезапуск приложения после воздействия ADB*

![image_2](https://github.com/Julia0028/android_lab_2/blob/master/report_png/2.png)

![image_3](https://github.com/Julia0028/android_lab_2/blob/master/report_png/3.png)


Пример №2. Принятие входящего звонка
___

*Рис. 1.5 и Рис. 1.6 Жизненный цикл приложения во время принятия входящего звонка*

![image_4](https://github.com/Julia0028/android_lab_2/blob/master/report_png/4.png)

![image_5](https://github.com/Julia0028/android_lab_2/blob/master/report_png/5.png)

Как видно из скриншотов, при принятии входящего звонка приложение переходит в состояние, при котором мы не можем с ним взаимодействовать, однако оно остается видимым  (вызывается метод onPaused()).


Пример №3. Взаимодействие с верхним меню
___

*Рис. 1.7 Жизненный цикл приложения при изменении настроек верхнего меню*

![image_6](https://github.com/Julia0028/android_lab_2/blob/master/report_png/6.png)

Как видно из скриншота, при взаимодействие с верхним меню не изменяет своего состояния  (вызывается метод onResume()).


#### Задача 2. Alternative Resources

**Задание:** продемонстрируйте работу альтернативного ресурса (18 вариант) на каком-либо примере.

**Решение:** в данном варианте рассматривается квалификатор Avaliable width. 
Он задает минимальную доступную ширину экрана в единицах dp, при которой должен использоваться ресурс - определяется значением <N>. Это значение конфигурации изменяется при изменении ориентации с альбомной на книжную в соответствии с текущей фактической шириной. Таким образом, можно использовать только один квалификатор, чтобы указать минимальную ширину, необходимую для макета, вместо использования одновременно квалификаторов размера экрана и ориентации.
Система будет использовать тот макет, ширина которого ближе всего (не превышает) заданную ширину. 

В примерах ниже данный квалификатор позволяет грамотно распределить пространство экрана: 
При смене ориентации с книжной на альбомую, т.е. увеличении ширины можно изменить расположение и размер кнопок, размер текста.

*Рис. 2.1 Пример 1*

![image_7](https://github.com/Julia0028/android_lab_2/blob/master/report_png/7.png)

![image_8](https://github.com/Julia0028/android_lab_2/blob/master/report_png/8.png)

*Рис. 2.2 Пример 2*

![image_9](https://github.com/Julia0028/android_lab_2/blob/master/report_png/9.png)


#### Задача 3. Best-matching resource

**Задание:** для заданного набора альтернативных ресурсов, предоставляемых приложением, и заданной конфигурации устройства (18 вариант) объясните, какой ресурс будет выбран в конечном итоге. Ответ докажите.

Конфигурация устройства:
```
LOCALE_LANG: fr
LOCALE_REGION: rUS
SCREEN_SIZE: small
SCREEN_ASPECT: long
ROUND_SCREEN: round
ORIENTATION: port
UI_MODE: appliance
NIGHT_MODE: night
PIXEL_DENSITY: tvdpi
TOUCH: finger
PRIMARY_INPUT: qwerty
NAV_KEYS: dpad
PLATFORM_VER: v26
```

Конфигурация ресурсов:
```
(default)
large-12key-v25
en-large-tvdpi
car-notouch
notnight-v25
round-finger
fr-rCA-notlong-v27
notlong-xxhdpi-qwerty-wheel-v25
large-notlong-v25
land-watch-night-nonav
round-dpad-v27
```

**Решение:** 
1. Уберем те ресурсы, которые противоречат заданной конфигурации (fr):

```
(default)
large-12key-v25
* en-large-tvdpi
car-notouch
notnight-v25
round-finger
fr-rCA-notlong-v27
notlong-xxhdpi-qwerty-wheel-v25
large-notlong-v25
land-watch-night-nonav
round-dpad-v27
```

2. Уберем те ресурсы, в которых не указано заданное требование (fr):

```
(default)
* large-12key-v25
* car-notouch
* notnight-v25
* round-finger
fr-rCA-notlong-v27
* notlong-xxhdpi-qwerty-wheel-v25
* large-notlong-v25
* land-watch-night-nonav
* round-dpad-v27
```

3. Уберем те ресурсы, которые противоречат заданном конфигурации (rUS):

```
(default)
* fr-rCA-notlong-v27
```

**Ответ:** в конечном итоге будет выбран default


#### Задача 4. Сохранение состояние Activity.

**Задание:** Студент написал приложение: continuewatch. Это приложение должно считать, сколько секунд пользователь провел в этом приложении (когда оно отображается на экране; приложение не считает секунды, когда оно не отображается на экране; значение счетчика сохраняется при перезапуске приложения). Найдите ошибки в этом приложении и исправьте их.

**Решение:**

Ошибки:
* При запуске приложения отображается слово Hello World
* Таймер продолжает работать в фоновом режиме
* При изменении ориентации устройства значение счетчика сбрасывается

Для исправления 1 ошибки необходимо удалить атрибут text из макета;

Для исправления 2 ошибки был создан класс Seconds, в котором были реализованы функции подсчета секунд. Разрешение на подсчет осуществляется в методе onResume(), т.е. когда приложение переходит в состояние взаимодействия с пользователем. Запрет на подсчет находится в зеркальном методе - onPause(). Таким образом, счетчик не работает, когда находится в фоновом режиме;

При изменении ориентации значительно меняется конфигурация устройства, поэтому системе проще всего перезапустить Activity. Таким образом, необходимо переопределить метод onSaveInstanceState() для сохранения данных и добавить пару ключ-значение (счетчика) к объекту Bundle, который сохраняется на случай, если действие будет неожиданно уничтожено.


### Вывод

В данной лабораторной работе подробно изучен жизненный цикл Activity, рассмотрены методы обратного вызова, которые определяют поведение Activity при смене состояния. На практике было доказано, что их грамотное использование позволяет избежать: сбоев (когда пользователь переключается на другое приложение во время использования данного), потребления ценных системных ресурсов (когда приложение находится в фоновом режиме), потери данных при изменении ориентации устройства. Также были изучены возможности альтернативных ресурсов, которые позволяют подстраивать приложение под разные конфигурации устройства.


### Приложение

**MainAktivity.kt**
```
package com.example.lab_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var timer: Seconds

    var backgroundThread = Thread {
        while (true) {
            Thread.sleep(1000)
            if (timer.isStartTimer())
                textSecondsElapsed.post {
                    textSecondsElapsed.setText("Seconds elapsed: " + timer.addSeconds())
                }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer = Seconds()
        if (savedInstanceState != null)
            timer.putSeconds(savedInstanceState.getInt("seconds_key"))
        Log.i("MainActivity", "onCreate called")
        setContentView(R.layout.activity_main)
        backgroundThread.start()
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onReStart called")
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called")
    }


    override fun onResume() {
        super.onResume()
        timer.startSeconds()
        Log.i("MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        timer.stopSeconds()
        Log.i("MainActivity", "onPause called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("seconds_key", timer.getSeconds())
        Log.i("MainActivity", "onSaveInstanceState called")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy called")
    }
}
```

**Seconds.kt**
```
package com.example.lab_2

class Seconds {
    var secondsElapsed: Int = 0
    var startTimer: Boolean = true


    fun addSeconds(): Int {
        return secondsElapsed++
    }

    fun startSeconds() {
        startTimer = true
    }


    fun stopSeconds() {
        startTimer = false
    }

    fun getSeconds(): Int {
        return secondsElapsed
    }

    fun putSeconds(newTimer: Int) {
        secondsElapsed = newTimer
    }

    fun isStartTimer(): Boolean {
        return startTimer
    }

}
```

**res/layout/main_activity.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textSecondsElapsed"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="14sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button4"
        android:layout_width="180dp"
        android:layout_height="83dp"
        android:text="Button"
        app:layout_constraintBottom_toTopOf="@+id/textSecondsElapsed"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.501"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.114" />

    <Button
        android:id="@+id/button5"
        android:layout_width="172dp"
        android:layout_height="96dp"
        android:text="Button"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textSecondsElapsed" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
**res/layout-w480dp/main_activity.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textSecondsElapsed"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="10sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/textSecondsElapsed"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Button"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/textSecondsElapsed"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```
**res/layout-w720dp/main_activity.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textSecondsElapsed"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:textSize="36sp"
        app:layout_constraintBottom_toTopOf="@+id/button3"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button3"
        android:layout_width="208dp"
        android:layout_height="116dp"
        android:text="Button"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```