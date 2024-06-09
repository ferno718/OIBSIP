import pyttsx3
import speech_recognition as sr 
import datetime
import wikipedia
import webbrowser
import os
import random

engine=pyttsx3.init('sapi5')
voices=engine.getProperty('voices')
print(voices[1].id)
engine.setProperty('voice',voices[1].id)

def speak(audio):
    engine.say(audio)
    engine.runAndWait()

def wishme():
    hour=int(datetime.datetime.now().hour)
    if hour>=0 and hour<12:
        speak("\n\nHello! Good Morning!, How can i help you ?")
        print("\n\nHello! Good Morning!, How can i help you ?")
    elif hour>=12 and hour<18:
        speak("\n\nHello! Good Afternoon!, How can i help you ?")
        print("\n\nHello! Good Afternoon!, How can i help you ?")
    else:
        speak("\n\nHello! Good Evening!, How can i help you ?")
        print("\n\nHello! Good Evening!, How can i help you ?")

def takeCommand():
    r=sr.Recognizer()
    with sr.Microphone() as source:
        print("\n\nI'm listening...")
        r.pause_threshold=1
        audio = r.listen(source)

    try:
        print("\n\nRecognizing...")
        query = r.recognize_google(audio, language='en-in') 
        print(f"\n\nUser said : {query}\n")

    except Exception as e:
        print(e)
        print("\n\nSorry, I'm unable to recognize due to occurence of some exception, Please try again!")
        return "none"
    return query

if __name__ == "__main__" :
    wishme()
    while True:
        query = takeCommand().lower()
        if 'wikipedia' in query:
            speak('\n\nSearching Wikipedia...')
            query=query.replace("wikipedia","")
            results= wikipedia.summary(query,sentences=2)
            speak("\n\nAccording to wikipedia")
            print(results)
            speak(results)

        elif 'open youtube' in query:
            webbrowser.open("youtube.com")

        elif 'open google' in query:
            webbrowser.open("google.com")
        
        elif 'open source bin' in query:
            webbrowser.open("https://sourceb.in/")
        
        elif 'open github' in query:
            webbrowser.open("https://github.com/ferno718")
        
        elif 'open insta' in query or 'open instagram' in query:
            webbrowser.open("https://www.instagram.com/")
        
        elif 'open linkedin' in query:
            webbrowser.open("https://www.linkedin.com/in/yashg718/")

        # elif 'play music' or 'play spotify' or 'play some music'  or 'play some spotify'  or 'open spotify' in query:
        #     music_dir="C:\Users\ASUS\Desktop\Spotify.lnk"
        #     songs=os.listdir(music_dir)
        #     number_of_songs=len(songs)-1
        #     number=random.randint(0,number_of_songs)
        #     os.startfile(os.path.join(music_dir,songs[number]))
        
        elif 'open steam' in query:
            steam="C:\ProgramData\Microsoft\Windows\Start Menu\Programs\Steam\Steam.lnk"
            os.startfile(steam)

        elif 'whats the time' in query or 'whats the time now' in query or 'what is the time' in query or 'what is the time now' in query:
            strtime= datetime.datetime.now().strftime("%H:%m:%S")
            speak(f"Sir , the time right now is {strtime}")

        elif 'open vs code' in query:
            codepath="D:\Microsoft VS Code\Code.exe"
            os.startfile(codepath)
        
        elif 'open word' in query:
            mcodepath="C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE"
            os.startfile(mcodepath)