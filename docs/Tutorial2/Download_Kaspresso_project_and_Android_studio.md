# Делаем первые шаги. Настраиваем необходимое окружение

В этом уроке мы скачаем проект Kaspresso, установим Android studio и настроим эмулятор.

## Скачиваем проект Kaspresso

Код Kaspresso публичен и доступен по [ссылке](https://github.com/KasperskyLab/Kaspresso). Переходим по ссылке и нажимаем кнопку Code:
<img src="../images/Github_download_button.png" alt="Download Kaspresso button"/>
В открывшемся диалоговом окне выбираем Download ZIP:
<img src="../images/Github_download_zip.png" alt="Download Kaspresso ZIP"/>
После того как проект будет скачен, распаковываем архив. Появится папка Kaspresso-master. Рекомендуем на диске создать рабочую папку и переместить в нее из папки "Загрузки" распакованный Kaspresso-master. Переходим к следующему шагу.

!!! Info

        Вы можете скачать проект при помощи Git. Если вы еще не знакомы с этой системой контроля версий, можете узнать подробнее [здесь](https://git-scm.com/)

## Скачиваем Android Studio
Android Studio используется для разработки программ. Нам она понадобится для написания и запуска автотестов.
<br>Если у вас уже установлена Android Studio, то пропустите этот шаг. Если нет, то переходим по ссылке [https://developer.android.com/studio](https://developer.android.com/studio) и нажимаем Download Android Studio:
<br>Запускаем скаченный файл и проходим все шаги первичной настройки студии. Можно воспользоваться [официальной инструкцией](https://developer.android.com/codelabs/basic-android-kotlin-compose-install-android-studio#0) как пошаговой инструкцией.
<br>После того как Android Studio будет скачена, запускаем ее. Выбираем "Open", находим в списке папку Kaspresso-master. После того как откроете проект, нужно подождать некоторое время, чтобы Android Studio проиндексировала файлы проекта.

## Настройка эмулятора
В верхнем меню Android Studio выбираем Tools -> Device Manager
<img src="../images/Tools_Device_Manager.png" alt="Tools Device Manager"/>
<br>На экране появися вкладка управления эмуляторами и реальными устройствами. Нажимаем Create Device
<img src="../images/Create_device.png" alt="Create Device"/>
<br> Увидим следующий экран:
<img src="../images/Select_hardware.png" alt="Select hardware"/>
<br> На этом экране можно задать характеристики "железа", эмуляцию которого хотим получить. В секции "1" можно выбрать телефон, планшет, телевизор и так далее. Нас интересует Телефон. В секции "2" - конкретную модель. В рамках туториала нет разницы, что выбрать. Выбираем Pixel 6. Нажимаем Next и попадаем на окно выбора образа операционной системы:
<img src="../images/System_Image.png" alt="System image"/>

<br> Этот экран более важен в регулярной работе - здесь выбираем, какую версию Android установить на эмулятор. Давайте выберем R. Нажимаем на иконку скачать справа от буквы R, проходим процесс установки и ожидаем.

<img src="../images/SDK_component_isntaller.png" alt="SDK_component_isntaller"/>

<br> Когда процесс установки будет окончен нажимаем кнопку Finish:

<img src="../images/SDK_component_installer_finish.png" alt="SDK_component_isntaller_finish"/>

<br> Выбираем установленную версию R и нажимает Next:

<img src="../images/SDK_component_installer_next.png" alt="SDK_component_installer_next"/>

<br> На экране ниже можно сменить название создаваеого эмулятора, чтоб их было легко отличать между собой. Дефолтное значение для наших целей подходит. Нажимаем Finish.

<img src="../images/Device_name.png" alt="Device_name"/>

<br> Устройство настроено и готово к работе. Запускаем его по иконке Play правее названия девайса:

<img src="../images/Launch_device.png" alt="Launch_device"/>

<br> В некоторых случаях Android Studio может порекомендовать установить Hypervisor:

<img src="../images/Hyper_Visor.png" alt="Hyper_Visor"/>

<img src="../images/Hyper_Visor_next.png" alt="Hyper_Visor_next"/>

<br> Итог: Android Studio скачена, эмулятор настроен, проект Kaspresso скачен. В следующем уроке запустим первые тесты.
