# NeuroLearn - LLM-Enhanced Learning 

NeuroLearn is an Android application developed as part of SIT708. The app is designed to transform the educational experience by integrating Large Language Models (LLMs) to provide personalized, adaptive tutoring for students.

## 📱 Features

* **Personalized Learning Flow**: Uses initial account setup and student interests to tailor educational content.
* **AI-Powered Utilities**: Includes three core learning tools:
    * **Generate Hint**: Provides subtle nudges for complex questions.
    * **Explain Logic**: Detailed breakdowns of correct/incorrect answers.
    * **Lesson Summary**: Concise recaps of AI topics.
* **Asynchronous Processing**: Utilizes Kotlin Coroutines (`lifecycleScope`) to handle LLM interactions without blocking the UI thread.
* **State Management**: Explicitly handles loading (ProgressBar) and failure states (Try-Catch/Toasts).
* **Professional UI**: Follows a strict 6-screen architecture (Login, Setup, Interests, Dashboard, Task Detail, and Results) with a focus on readability and accessibility.

## 🛠️ Tech Stack

* **Language**: Kotlin
* **UI Framework**: XML Layouts & Material Components
* **Asynchronous Logic**: Kotlin Coroutines
* **Architecture**: Activity-based with Intent-driven data passing
* **Min SDK**: API 24+
* **Target SDK**: API 35 (Android 15)

## 🚀 Navigation Logic

The application is built to ensure a seamless user journey with no dead ends. 
* The InterestsActivity packages user data into Intent extras.
* The TaskDetailActivity performs the simulated LLM logic.
* The ResultsActivity uses finish() to return the user to the Dashboard, maintaining a clean activity backstack.


## 🎓 Assessment Context

* **Unit**: SIT708 - Applied Android Development
* **Task**: 6.1D LLM-Enhanced Learning Assistant
* **Student**: Sahan Chandimal Medagedara
* **Due Date**: Thursday, 23rd April
