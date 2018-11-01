Description:

A simple app to allow a basic Olympic Medals Table to be displayed in a browser or on a device;
The app should allow:
Countries to be added to a table, along with the TOTAL number of medals the country won during the last Olympic Games; and
Rows to be sorted in descending order of medals won, either automatically or manually by the user.

Notes:

1. The architecture that I have used is MVVM.
2. It is using the Android Architecture Components, with the ViewModel having lifecycle awareness.
3. it is written in Kotlin
4. It includes Mockito tests , test coverage 100%
5. It is using ROOM database.
6. It uses Dagger 2
7. It sorts in descending order of the number of gold medals
8. Uses corountines for ROOM operations running off the main thread.

Todo:

1. add header to recyclerview and general improvement to the UI ( increase font size, use percent-guideline for liquid dimensions)
2. It currently displays the countries flag , as the American flag, by default, so, it needs to change so that 
user can select a flag

