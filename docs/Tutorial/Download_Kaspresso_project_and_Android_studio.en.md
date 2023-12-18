# Setting up the required environment.
In this lesson, we will download the Kaspresso project, install Android studio and set up the emulator.

## Download Android Studio
Android Studio is used for software development. We will need it to write and run autotests.
<br>If you already have Android Studio installed, skip this step. If not, then follow [the link](https://developer.android.com/studio) and click Download Android Studio.

<br>Run the downloaded file and go through all the steps of the initial setup of the studio. You can use [the official manual](https://developer.android.com/studio/install) or [the official codelabs manual](https://developer.android.com/codelabs/basic-android-kotlin-compose-install-android-studio#0) in case of problems.
<br>After Android Studio is downloaded, run it.

## Downloading the Kaspresso project
To download a project, you must have the GIT version control system installed on your computer. You can download GIT and learn more about it [here](https://git-scm.com/).

Once GIT is installed, you will be able to download the project. To do this, follow the [link](https://github.com/KasperskyLab/Kaspresso).

Click the Code button and copy the link to the repository

<img src="../images/Download_Kaspresso_project_and_Android_studio/download_by_git.png" alt="Download Kaspresso button"/>

Open Android Studio.

If you have not previously opened any project in the studio, then you must select the Get From VCS item

<img src="../images/Download_Kaspresso_project_and_Android_studio/get_from_vcs.png" alt="Get Project from VCS"/>

If a project has already been launched, then you can load a new one from GIT as follows: `File` -> `New` -> `Project From Version Control`

<img src="../images/Download_Kaspresso_project_and_Android_studio/new_project_from_vcs.png" alt="Get Project from VCS"/>

In the window that opens, enter the copied project URL, select the folder where Kaspresso will be placed and click clone.

<img src="../images/Download_Kaspresso_project_and_Android_studio/clone_project.png" alt="Clone Project"/>

## Setting up the emulator.
In the top menu of Android Studio, select 'Tools' -> 'Device Manager'

<img src="../images/Download_Kaspresso_project_and_Android_studio/Tools_Device_Manager.png" alt="Tools Device Manager"/>

The tab for managing emulators and real devices will appear on the screen. Click on 'Create Device':

<img src="../images/Download_Kaspresso_project_and_Android_studio/Create_device.png" alt="Create Device"/>

We will see the following screen:

<img src="../images/Download_Kaspresso_project_and_Android_studio/Select_hardware.png" alt="Select hardware"/>

On this screen, you can set the characteristics of the hardware you want to emulate. In section "1" you can select phone, tablet, TV and so on. For the purposes of this tutorial we will be working with the "phone" type. In section "2" you can select a specific model. Within the scope of this guide, it makes no difference which one to choose. Choose 'Pixel 6'. Click 'Next' and get to the operating system image selection window:

<img src="../images/Download_Kaspresso_project_and_Android_studio/System_Image.png" alt="System image"/>

This screen is more important for regular work and lets you choose which version of Android to install on the emulator. Let's choose 'R'. Click on the download icon to the right of the letter 'R', go through the installation process and wait.

<img src="../images/Download_Kaspresso_project_and_Android_studio/SDK_component_isntaller.png" alt="SDK_component_isntaller"/>

When the installation process is completed, click the Finish button:

<img src="../images/Download_Kaspresso_project_and_Android_studio/SDK_component_installer_finish.png" alt="SDK_component_isntaller_finish"/>

Select the installed version ('R') and click 'Next':

<img src="../images/Download_Kaspresso_project_and_Android_studio/SDK_component_installer_next.png" alt="SDK_component_installer_next"/>

On the screen below, you can change the name of the created emulator so that it is easy to distinguish between them. The default value is fine for our purposes. Click 'Finish'.

<img src="../images/Download_Kaspresso_project_and_Android_studio/Device_name.png" alt="Device_name"/>

The device is set up and ready for work. We launch it by the 'Play' icon to the right of the device name:

<img src="../images/Download_Kaspresso_project_and_Android_studio/Launch_device.png" alt="Launch_device"/>

In some cases, Android Studio may recommend installing Hypervisor:

<img src="../images/Download_Kaspresso_project_and_Android_studio/Hyper_Visor.png" alt="Hyper_Visor"/>

<img src="../images/Download_Kaspresso_project_and_Android_studio/Hyper_Visor_next.png" alt="Hyper_Visor_next"/>

## Summary
Android Studio is installed, emulator is configured, Kaspresso project is loaded. In the next lesson, we will run the first tests.
