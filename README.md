# karpluswindchimes
This is an attempt at a virtual instrument using the Karplus Strong algoritm. This is by no means a finished product, but feel free to try it. 

# Getting Started

The project is more or less POJ - maven will import all dependencies for you. Just do a clean install beforehand. No installation necessary. 

# Running the project

Just run main. It will likely produce some sound for you - there is no guarantee, obviously: the project assumes the first audio device it can find is used to play sound - in most cases, that will be true, as in most cases, your system will make sure it is. However, java runs on lots of devices and I only tested on ubuntu and Windows 11. 
The main method creates an instrument using an array of frequencies to create the strings (as in reverbing ligaments) on the instrument - actually a virtual harpsichord, if you will. There is a method to create scales for you too - the project will use a chromatic scale by default. The program will play all the strings that are created in ascending order of frequency.

# Contributing
The main goal of this project is for me to learn, experiment, and have some fun, all in the pursuit of being a better programmer. I do not really expect any contributions, as this code is never going to be used for anything beyond those goals. 

However, if you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement". Your additions will be appreciated. 

Fork the Project
Create your Feature Branch (git checkout -b feature/AmazingFeature)
Commit your Changes (git commit -m 'Add some AmazingFeature')
Push to the Branch (git push origin feature/AmazingFeature)
Open a Pull Request
