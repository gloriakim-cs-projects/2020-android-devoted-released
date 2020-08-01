# As of August 1, 2020 3:00 AM

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
- completed setting - allow textview clickable for easier selection

**Need Help**

- I successfully retrieved data from Firebase, but I cannot show the data using listview nicely (I separated each string with comma, but that limits users to use the comma in their notes).

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

# How to Remotely Upload Android Studio Files in Windows
*Note: Remeber to remove any git connection in Android Studio to use this.*

- Step 1. Open the PowerShell 
- Step 2. Create a folder for Git stuffs
- Step 3. Inside of the folder, type the following code

1. `git init`
2. `git remote add origin http://github.com/gloriakim-cs-projects......`
3. `git add .`
4. `git commit -m "Initial commit"`
5. `git pull origin master`
6. Modify the folder as needed (ex) add a new folder
7. `git push origin master`
