# Coursero
This is an android app using the ANDROID JETPACK components.
1. This app shows the list of video courses. 
2. Upon selecting one cource video will play.
3. Video will display NOTES if any and then pause the video.
4. Upon clicking the NOTES video will resume.
5. After completing the cource QUIZ will be presented.
6. After completing the quiz score will be shown.


//* 
Rules for the app.
You can take any video and any topic for a quiz. It's your choice. Minimum number of questions for each topic is 10. Use the latest code design and pattern for coding. You can improve code as much as possible and use the latest library for development.

## screen1 : list of topics

- On launch of App there will be a list of topics

## screen2 : video and notes

- the video will play on click of any topic
- user can add notes on video and will be stored on a state
- video will have cue points where notes are added
- on reach of a cue point the video will pause and note will be displayed on a overlay container on bottom
- once click on the notes it will disappear and the video will resume
- on next time the user comes the video state will be maintained via state
- on the end of the video the quiz will launch

## screen3 : quiz

### Reference: https://dribbble.com/shots/14012432-Quiz-App-Mobile-Design

- the quiz will have 3 layouts: vertical, horizontal and grid
- the grid layout reference is given in a sample video, other 2 can be derived from it only
- the effects and animations to be achieved as much as you can
- but the basic zoom in out of a option after click has to be there
- the correct and incorrect animation can be a placeholder animation
- on incorrect the user will be shown incorrect on the same manner the correct is showing, but the question will not go next, whereas in case of correct it will go next after the get ready animation is played
- the get ready animation can be a sample placeholder animation
- whether to show the get ready animation or not will be controlled from a settings.json externally
- there will the timer animation also(parameters like to show or not and duration are controlled from settings json)
- on next time the user comes the questions state will be maintained via state
- after the last question the summary screen will come

## screen4 : summary
- the last screen will be summary of quiz
- score and time taken for each question
*/



APP STATUS: screen 1 and screen 2 is completed. screen 3 and 4 still require some more development time.

SCREENSHOT:

![Screenshot_20210409-175225 1](https://user-images.githubusercontent.com/31123825/114184878-b0622980-9962-11eb-9a69-e55b46184840.png)
![Screenshot_20210409-175346 1](https://user-images.githubusercontent.com/31123825/114184927-bce68200-9962-11eb-9810-1d28f1d3af4d.png)
![Screenshot_20210409-175401 1](https://user-images.githubusercontent.com/31123825/114184959-c7088080-9962-11eb-88d8-8eb7bead089e.png)

