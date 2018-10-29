A simple app to allow a basic Olympic Medals Table to be displayed in a browser or on a device;
The app should allow:
Countries to be added to a table, along with the TOTAL number of medals the country won during the last Olympic Games; and
Rows to be sorted in descending order of medals won, either automatically or manually by the user.

The architecture that I have used is MVVM.
It is using the Android Architecture Components, with the ViewModel having lifecycle awareness.
it is written in Kotlin
It includes Mockito tests
It currently displays the countries flag , as the American flag, but , I will update when I have time.
It is using ROOM database.
It uses Dagger 2, but, I have currently removed the implementation , since, there is a subtle bug
It sorts in descending order of the number of gold medals

