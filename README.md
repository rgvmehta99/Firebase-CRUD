# Firebase-CRUD
Simple database CRUD operation usind Firebase

*-------------------*

**1.** First thing you need to do is go to https://firebase.google.com/ and make an account to gain access to their console. After you gain access to the console you can start by creating your first project.

**2.** Give the package name of your project (mine is `com.firebasecrud`) in which you are going to integrate the Firebase. Here the **_google-services.json_** file will be downloaded when you press add app button.

**3.** Create a new project in Android Studio from **File ⇒ New Project.** While filling the project details, use the same package name which you gave in firebase console. In my case I am using same `com.firebasecrud`.

**4.** Paste the **_google-services.json_** file to your project’s **app** folder. This step is very important as your project won’t build without this file.

**5.** Now open the **build.gradle** located in project’s home directory and add google playstore dependency.


*-------------------*

Project **build.gradle**


> dependencies {
> 
>         classpath 'com.google.gms:google-services:3.1.0'
>         classpath 'com.android.tools.build:gradle:3.0.0-beta2'
>
>         // NOTE: Do not place your application dependencies here; they belong
>         // in the individual module build.gradle files
>     }


*--------------------*

app **build.gradle**


> dependencies {
> 
>         implementation 'com.android.support:design:27.0.1'
>         implementation 'com.google.firebase:firebase-database:11.8.0'
>
>     }
> 
> apply plugin: 'com.google.gms.google-services'



**6.** References
###### Security rules:
https://firebase.google.com/docs/database/security/quickstart#sample-rules

######  Firebase Blog
https://firebase.googleblog.com/2016/07/have-you-met-realtime-database.html



**7.** Pricing
Unlike Analytics, Cloud Messaging, Crash Reporting and other services, firebase realtime database is not completely free. There are certain limitations in Free plan. You need to pay few bucks for the usage of number of connections, disk usage and network usage. For more information check out the firebase [Pricing plans](https://firebase.google.com/pricing/).

Here is the quick overview of realtime database pricing details (as of Oct 12th, 2016)

