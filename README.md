Description:

A simple app to allow a basic Olympic Medals Table to be displayed in a browser or on a device;
The app should allow:
Countries to be added to a table, along with the TOTAL number of medals the country won during the last Olympic Games; and
Rows to be sorted in descending order of medals won, either automatically or manually by the user.

Notes:

1. The architecture that I have used is MVVM.
2. It is using the Android Architecture Components, with the ViewModel having lifecycle awareness.
3. it is written in Kotlin
4. It includes Mockito tests
5. It currently displays the countries flag , as the American flag, but , I will update when I have time.
6. It is using ROOM database.
7. It uses Dagger 2, but, I have currently removed the implementation , since, there is a subtle bug
8. It sorts in descending order of the number of gold medals

Todo:

1. add Dagger DI
2. add header to recyclerview
3. Fix broken test and add complete full range of tests

