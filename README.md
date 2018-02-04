# TomaTimeTrack (T3)
T3 aims to be a Pomodoro Techinque productivity app for privacy minded people that will track, store and visualise your productivity data locally without associating it with your name, ip, location or any kind of user account. If at any point this app sends data off your device, it is with your consent, anonymous, and only used in agregate data accessible to you the users.

## Technologies
We use Java with JavaFX for GUI and SQLite for local data storage.

## Status
Early development. Not yet implementing Data-, API- or Gamification features.

## Implemented Features
* Pomodoro Technique timer that can be set to Work (25 minutes), short break (5 minutes) or long break (15 minutes)
* Ability to adjust length of work, short break and long break
* Ability to set AlwaysOnTop parameter in settings menu
* Stores some productivity data in a database, no way of accessing it in the app yet

## Planned Features
### Timer
* Make a sound when the time is up
### Data
* Histogram of the week showing what times you usually work the most
* GitHub style tilechart showing which days you have completed work in the past year
* Day by day statistics on how many minutes, pomodoros (25 minute work intervals) 
and blocks (4 pomodoros with breaks in between) you complete.
### Agreagate Data API
* Collection of agregate anonymized data to a central database that can be explored and accessed by all users in the app or via an open API
### Gamification and Goalsetting
* Ability to set perosnal daily and weekly goals that can be achieved
* Achievements
