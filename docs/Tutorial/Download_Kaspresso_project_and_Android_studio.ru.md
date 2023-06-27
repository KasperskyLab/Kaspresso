# Делаем первые шаги. Настраиваем необходимое окружение

В этом уроке мы скачаем проект Kaspresso, установим Android studio и настроим эмулятор.

## Скачиваем Android Studio
Android Studio используется для разработки программ. Нам она понадобится для написания и запуска автотестов.
<br>Если у вас уже установлена Android Studio, то пропустите этот шаг. Если нет, то переходим по [ссылке](https://developer.android.com/studio) и нажимаем Download Android Studio.

<br>Запускаем скачанный файл и проходим все шаги первичной настройки студии. Можно воспользоваться [официальной инструкцией](https://developer.android.com/studio/install) или [официальной инструкцией в формате codelabs](https://developer.android.com/codelabs/basic-android-kotlin-compose-install-android-studio#0) в случае возникновения проблем.
<br>После того как Android Studio будет скачана, запускаем ее.

## Скачиваем проект Kaspresso

Для загрузки проекта необходимо, чтобы на вашем компьютера была установлена система контроля версий GIT. Загрузить GIT и узнать о нем подробнее вы можете [здесь](https://git-scm.com/).

Когда GIT будет установлен, то вы сможете скачать проект. Для этого переходим по [ссылке](https://github.com/KasperskyLab/Kaspresso).

Нажимаем кнопку Code и копируем ссылку на репозиторий

<img src="../images/Download_Kaspresso_project_and_Android_studio/download_by_git.png" alt="Download Kaspresso button"/>

Открываем Android Studio.

Если у вас ранее не был открыт никакой проект в студии, то необходимо выбрать пункт Get From VCS

<img src="../images/Download_Kaspresso_project_and_Android_studio/get_from_vcs.png" alt="Get Project from VCS"/>

Если какой-то проект уже был запущен, то загрузить новый с GIT можно следующим образом: `File` -> `New` -> `Project From Version Control`

<img src="../images/Download_Kaspresso_project_and_Android_studio/new_project_from_vcs.png" alt="Get Project from VCS"/>

В открывшемся окне введите скопированный URL проекта, выберите папку, в которой будет размещен Kaspresso, и нажмите clone.

<img src="../images/Download_Kaspresso_project_and_Android_studio/clone_project.png" alt="Clone Project"/>

## Настройка эмулятора.

В верхнем меню Android Studio выбираем 'Tools' -> 'Device Manager'

<img src="../images/Download_Kaspresso_project_and_Android_studio/Tools_Device_Manager.png" alt="Tools Device Manager"/>

На экране появится вкладка управления эмуляторами и реальными устройствами. Нажимаем 'Create Device':

<img src="../images/Download_Kaspresso_project_and_Android_studio/Create_device.png" alt="Create Device"/>

Увидим следующий экран:

<img src="../images/Download_Kaspresso_project_and_Android_studio/Select_hardware.png" alt="Select hardware"/>

На этом экране можно задать характеристики "железа", эмуляцию которого хотим получить. В секции "1" можно выбрать телефон, планшет, телевизор и так далее. Нас интересует Телефон. В секции "2" - конкретную модель. В рамках туториала нет разницы, что выбрать. Выбираем 'Pixel 6'. Нажимаем 'Next' и попадаем на окно выбора образа операционной системы:

<img src="../images/Download_Kaspresso_project_and_Android_studio/System_Image.png" alt="System image"/>

Этот экран более важен в регулярной работе - здесь выбираем, какую версию Android установить на эмулятор. Давайте выберем 'R'. Нажимаем на иконку скачать справа от буквы 'R', проходим процесс установки и ожидаем.

<img src="../images/Download_Kaspresso_project_and_Android_studio/SDK_component_isntaller.png" alt="SDK_component_isntaller"/>

Когда процесс установки будет окончен, нажимаем кнопку 'Finish':

<img src="../images/Download_Kaspresso_project_and_Android_studio/SDK_component_installer_finish.png" alt="SDK_component_isntaller_finish"/>

Выбираем установленную версию 'R' и нажимаем 'Next':

<img src="../images/Download_Kaspresso_project_and_Android_studio/SDK_component_installer_next.png" alt="SDK_component_installer_next"/>

На экране ниже можно сменить название создаваемого эмулятора, чтоб их было легко отличать между собой. Дефолтное значение для наших целей подходит. Нажимаем 'Finish'.

<img src="../images/Download_Kaspresso_project_and_Android_studio/Device_name.png" alt="Device_name"/>

Устройство настроено и готово к работе. Запускаем его по иконке 'Play' справа от названия девайса:

<img src="../images/Download_Kaspresso_project_and_Android_studio/Launch_device.png" alt="Launch_device"/>

В некоторых случаях Android Studio может порекомендовать установить Hypervisor:

<img src="../images/Download_Kaspresso_project_and_Android_studio/Hyper_Visor.png" alt="Hyper_Visor"/>

<img src="../images/Download_Kaspresso_project_and_Android_studio/Hyper_Visor_next.png" alt="Hyper_Visor_next"/>

## Итог

Android Studio установлена, эмулятор настроен, проект Kaspresso загружен. В следующем уроке запустим первые тесты.
