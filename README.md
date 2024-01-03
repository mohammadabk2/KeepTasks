# Tasks

## Video Demo:  <https://youtu.be/CXGN3BxcGcw>

## App Description

I have choosen to build an android app as my final project for CS50.
the app will help me and other better orgnize out tasks to save time and
not to forget them.

it manages daily weekly and monthly tasks by setting alarms and sending notifications.
i built it becuase i never really liked the apps the offer similar services so why not make it my self.

## File Description

- TaskObj.java file contaisn the object of a task and its attributes like title urgency date remind the day before and note
and its one function toString.
- DataBaseHelper.java file interacts with the sql database and has a function that create the database if it doesnt exist and adds the table into it
and other functions that insert new tasks into the database and others that return all the tasks currently in the database as a list of TaskObj
and i am working on adding the ability to delete from the database and edit existing tasks in it.
- Setting.java file has the version number of the app and the functions that export and import the database file so the user can backup or use a backup
- CreateTask.java logs all the Task details like title urgency date and note and creates a TaskObj that gets sent to the database through a DataBaseHelper
- MainActivity.java contains all the buttons on the main page and the list view to show the user all the tasks
- gui and design are not speciality but i have tried my best and i am always adding to them
- activity_main.xml is the landing page of the app it holds buttons to add tasks which will take the user to the activity_createtask.xml and there fill out all the details
- activity_createtask.xml contails textview and edittext to get form the user title and note content and a finish and cancel buttons and toggle for urgency and daybefore.
- activity_settings.xml contians the app version and import and export buttons and go back button to the main page

### To do

1. ~~make the listView auto update when something is added or removed~~
1. ~~add the ability to complete tasks and delete them~~
1. ~~add a task histroy in the database to be viewed in settings or a button for it~~
1. ~~add clear history button~~
1. add functionality to the normal and urgent buttons
1. ~~add functionality to the clear history button~~
1. ~~add the valid date ability~~
1. change the filter buttons to be a list added by the user
1. add setting alarms
1. maybe sending a notification
1. add the ability to edit tasks by having the createtask show again with all the details there
1. add the ability to import/export the database
1. polish gui in every page make everyhting fit better all around and have the same size
1. add a personal touch were it welcomes th person by name
1. add search task
