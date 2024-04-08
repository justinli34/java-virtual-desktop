# ArkNet

## Description

ArkNet is a mystery-adventure game set in a post-apocalyptic world where you must uncover the secrets of a lost system.
Players will work through an operating system with various apps, uncovering clues and solving puzzles to progress the story.
This game is for anyone who enjoys mysteries or working through computer systems. The storyline is based on decision-making.

## Story

In the year 2000, Arch Technologies stumbles upon an archaic system hidden among the remnants of the internet. 
ArkNet is a mysterious relic of civilization before its destruction.
As a hired explorer, you must delve into the depths of ArkNet and discover its secrets. 
But you are not alone. Rival factions are also after the system's secrets, and they will stop at nothing to get it. 
At the core of ArkNet is _The Ark_, which Arch Technologies believes holds the key to restoring order to the new world. 
Will you find out the truth behind humanity's downfall, or will you share its fate?

## Motivation

This project is of interest to me because I love computers and video games.
Mysteries have always captivated me and I want to try creating my own. 
Also, I enjoy the aesthetic of vintage operating systems and would like to emulate that feeling with ArkNet.
This game is inspired by games like _Hypnospace Outlaw_, _Citizen Sleeper_, and _Papers, Please_.

## User Stories

- As a user, I want to be able to enter and exit ArkNet.
- As a user, I want the game to have various working apps such as a browser, file explorer, music player, etc.
- As a user, I want to be able to view a list of my applications.
- As a user, I want to be able to download files from the browser app.
- As a user, I want to be able to view a list of my downloaded files.
- As a user, I want the game to have challenging but solvable puzzles.
- As a user, I want to be able to save my game when I close the application. 
- As a user, I want to be able to create a new game file or load a previous game file.
- As a user, I want the game to have multiple different endings.

## Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the Apps button
    in the menu and going to Fiber Search. Then, go to John's Music Blog and download some songs. 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by opening the Music Player
    from the menu and clicking on the song buttons to play them. Press the pause or resume buttons to pause or resume the song.
- You can locate my visual component by going to Fiber Search and going to Tony's Pizza.
- You can save the state of my application by closing the application. There will be a dialog popup asking if you want to save.
- You can reload the state of my application by opening the application. There will be a dialog popup asking if you want to load.

## Phase 4: Task 2
Tue Apr 02 14:46:14 PDT 2024  
Faraon was added to Music Player  
Tue Apr 02 14:46:14 PDT 2024  
BillieJean was added to Music Player  
Tue Apr 02 14:46:17 PDT 2024  
pizzarecipe.txt was downloaded  
Tue Apr 02 14:46:26 PDT 2024  
BillieJean started playing  
Tue Apr 02 14:46:28 PDT 2024  
Faraon started playing  
Tue Apr 02 14:46:30 PDT 2024  
Song was paused  
Tue Apr 02 14:46:31 PDT 2024  
Song was resumed  
Tue Apr 02 14:46:32 PDT 2024  
Song was paused  

## Phase 4: Task 3
In my current design, there is high coupling between the GUI and model because the GUI instantiates all the applications
to be able to work with them, but also instantiates a Home class which contains all the apps. To refactor this to reduce coupling,
I could remove the Home class entirely and work with the apps as unique classes. However, since I originally intended for there
to be apps that could be downloaded by the user, deleting the Home class, which is a container for the apps, may not be a good idea.
Instead, a better solution could be to change the appList field in Home to be a map with app names as keys and apps as values. 
Then, everytime an app is added in the model, a button for that app will be added in the ui. Whenever an app is opened through the ui,
I would get the app from the appList, and pass it as an argument to a method in the ui that handles interaction with that app specifically.
This way, only the methods that care about a specific application will have to know about it, instead of the entire ui.