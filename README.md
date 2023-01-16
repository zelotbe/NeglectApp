# Neglect App - HeFTOS

This is an Android smartwatch app made with:
Jetpack Compose, Room Database, Preferences Datastore

## API Installation & Setup
<details>
  <summary>Be sure to read this when cloning the project! (Click to open)</summary>
  
 I recommend you to use an Azure MySQL database.
When you have cloned the project, move the API folder to a folder of your choice.

Open a terminal and execute the following:
```javascript
npm install
```

Now do the following:
1. Create a ".env" file in your root folder.
2. Copy these and change the placeholders with your database connection details.

```Shell
DB_DATABASE={DATABASE NAME}
DB_USERNAME={USERNAME}
DB_PASSWORD={PASSWORD}
DB_HOST={HOSTNAME}
```

3. If you want to use a SSL connection you have to download your certificate and place it in the root of the project folder.
   (Azure MySQL database: Click on settings > networks | Here you can download the SSL-certficate)
   If you don't want to use SSL, remove line 23 which should look like the following code:
```javascript
 ssl: { ca: fs.readFileSync("DigiCertGlobalRootCA.crt.pem") }
```
  
</details>


## MySQL database

Import the following into your MySQL database:
```sql
CREATE DATABASE  IF NOT EXISTS `neglectapp`;
USE `neglectapp`;

DROP TABLE IF EXISTS `sessions`;

CREATE TABLE `sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Datum` datetime DEFAULT NULL,
  `Interactie` tinyint DEFAULT NULL,
  `Hartslag` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;

``` 

## Project Installation

1. Clone the project to a folder of your choice.
2. Open the project in Android Studio.
3. Wait for Android Studio to run the gradle scripts.
4. Change the API_URL variable in Core > Constants.kt to the desired API URL

   If the API is running on localhost or your URL is not HTTPS, you have to also edit the IP adress in res > xml > network_security_config.xml
   (Android doesn't like connecting to HTTP)
   ```xml
   ...
      <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">PLACE LOCAL IP ADRESS HERE</domain>
      </domain-config>
    ...
   ``` 
5. Ready to build
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

- [x] Save data to Azure

- [x] Unit testing

### EXTRAS
- [ ] Check if I'm able to start the service automatically on starting hour

- [ ] Heartrate

- [ ] Detect movement before stimula is triggered

- [ ] Optimization

- [ ] Animated icon for Ongoing Activity (wear homescreen icon)

- [x] MVVM - Pattern

- [x] Show data (Interactions) on a circle graph

- [x] UI testing

### Good resources

https://www.youtube.com/watch?v=jpUVamtoKOs
