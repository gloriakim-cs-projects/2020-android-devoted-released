# How to Install the Zip File

Step 1. Download the zip file

Step 2. Unzip the file

Step 3. Open Android Studio

Step 4. Open the file using Android Studio

A thorughout readme can be found here: https://github.com/gloriakim-cs-projects/android_app_daily_bible/blob/master/README.md

# Group Name

Devoted

# Member Name

Gloria Kim (sole contributor)

# As of August 1, 2020 1:16 AM

**Fully Completed**

- Main (except Setting)
- Note (except some features of Private)
- Alarm
- Notification
- Record
- 
- completed all SharedPreferences variables (readnig_plan and reading_day)
- showed full content of each item of ListView
- finished adding some brief messages for each reading plan. 
- finished making textview clickable in Alarm for eaiser selection of checkmarks
- finished changing font size
- added the app icon (shown in the screen) 
- modified squished icon size when font is large
- finished all instruction with fragments
- refreshed Bible fragment

**Need to Complete (Partially Completed)**

- Main's Setting - add [multiple langauges](https://github.com/gloriakim-cs-projects/android_app_daily_bible/blob/master/README.md)
- allow to change the bible verse only if today is different than yesterday
- setting: allow textview clickable for easier selection

**Need to add in the final report**

- Public note --> Now it shows. Next, (1) resets the public note every time (2) display in a weird way. need to remove [] --> Created a user id to fix this issue --> Now all works! :) 
- added information in alarm for each readnig method
- removed features to allow editing from private note to public note. (time limit)

**Removed Feature**

- Because NIV is not freely available and KJV is difficult to understand, I simply removed the entire Bible fragment.

**Need Help**
- I successfully retrieved data from Firebase, but I cannot show the data using listview.

**Before Publication**
- Delete all unused libraries

# Updates

June (2nd): finished the design using Adobe XD

June (3rd): Started to code the XML files using Android Studio 

June (4th): Continued to code the XML files using Android Studio

July (1st): Started to code the Java files using Android Studio

July (2nd): Started to code the internal storage using Android Studio

July (3rd): Started to code the external storage (Figrebase) using Android Studio

July (4th): Continued to code the Java files using Android Studio

August (1st): Warpped up the entire code and wrote the final report

# Tips

Because I spent hours to figure this out, I would like to show some detailed steps to use for later.

[Refreshing a Fragment - Android Geek](https://stackoverflow.com/questions/44622311/how-can-i-call-onactivityresult-inside-fragment-and-how-it-work)

- Step 1. Add the following code in the parent activity java file
```
public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    Fragment fragment = (Fragment) getSupportFragmentManager().findFragmentByTag("FragmentNotes");
    if (fragment != null) {
        fragment.onActivityResult(requestCode, resultCode, intent);
    }
}
```
- Step 2. Add the following code in the child fragment java file
```
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data)
{
    if ((requestCode == 10001) && (resultCode == Activity.RESULT_OK)) {
      // recreate your fragment here
      Fragment frg = null;
      FragmentTransaction ft = getParentFragmentManager().beginTransaction();
      if (Build.VERSION.SDK_INT >= 26) {
          ft.setReorderingAllowed(false);
      }
      ft.detach(this).attach(this).commit();
    }
}
```
- Step 3. Start `startActivityForResult` (not just `startActivity`) from the child fragment to a new activity
```
startActivityForResult(editPrivateData, 10001);
```
- Step 4. Add `setResult` in that new activity
```
setResult(Activity.RESULT_OK);
```

