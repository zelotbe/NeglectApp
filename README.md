# Neglect App - HeFTOS

This is an Android smartwatch app made with:
Jetpack Compose, Room Database, Preferences Datastore

## Installation

1. Clone the project to a folder of your choice.
2. Open the project in Android Studio.
3. Wait for Android Studio to run the gradle scripts.
4. Ready to build

## Extracting Local csv file from WearOS to PC

Make sure you have [adb installed](https://www.xda-developers.com/install-adb-windows-macos-linux/#how-to-set-up-adb) and setup correctly.

Before we can extract our .csv file we have to make sure that the smartwatch has developer options enabled. 
(This is required to connect over Wi-Fi - since most watches don't have an USB-port connection.)

### Developer options
To enable developer options (This may be different depending the OS), do the following:

1. Open the watch's **Settings**.
2. Tap **System > About**.
3. Scroll to **Build number** and tap the build number seven times.
4. A dialog will appear confirming that you are now a developer.

### Enable Wi-Fi debugging

1. Open the watch's **Settings**.
2. Tap **Developer options > Debug over Wi-Fi**.
3. After a moment the screen will display the watch's IP address (for example `192.168.1.100`). You'll need this for the next step, so make a note of it.

### Connect the debugger to the watch

1. Connect your watch and development machine to the same network.

2. Connect the debugger to the watch using the watch's IP address. For example, if the IP address is `192.168.1.100`, the adb connect command and its response will look like this (a port number, `5555` is added to the address):

   ```
   adb connect 192.168.1.100:5555
   connected to 192.168.1.100:5555
   ```

The watch is now connected to the debugger and you're ready to start debugging. 
Send adb commands to the watch using the `-s` flag and specify the watch's IP address, including the port number:

```
adb -s 192.168.1.100:5555 <command>
```

If you are not using the emulator and have only one device connected for debugging, you don't need to specify the address at all.

### Extraction command

This will download the NeglectAppData.csv file to your chosen folder.

```powershell
adb pull "/sdcard/Documents/NeglectApp/NeglectAppData.csv" "C:\Users\USER\FOLDER_LOCATION"
```

## Status
  Code is can be improved at some areas.

- [x] Choose & save operating hours

- [x] Choose & save minimum and maximum amount of sessions

- [x] Configurable stimula (Vibration, Light, Sound)

- [x] Configurable stimula intensity

- [x] Implement intensities 

- [x] Show a functioning progress bar on landing screen (represents operating hours)

- [x] Pause & resume ability

- [x] Show an alarm screen

- [x] Customize alarm screen with the chosen stimula

- [x] Trigger a random amount of stimula between operating hours

- [x] Implement ongoing activity

- [x] Ability to close the alarm

- [x] Auto close alarm after 15 seconds

- [x] Save time when alarm is triggered and if the user interacted with the alarm

- [x] Save data local

- [ ] Save data to Azure

- [ ] Unit testing

### EXTRAS
- [ ] Check if I'm able to start the service automatically on starting hour

- [ ] Heartrate

- [ ] Detect movement before stimula is triggered

- [ ] Optimization

- [ ] Animated icon for Ongoing Activity (wear homescreen icon)

- [x] MVVM - Pattern

- [x] Show data (Interactions) on a circle graph

- [ ] UI testing

### Good resources

https://www.youtube.com/watch?v=jpUVamtoKOs
