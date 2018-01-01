#!/bin/bash

sudo apt-get install unzip
wget -N http://chromedriver.storage.googleapis.com/2.34/chromedriver_linux64.zip -P ~/Downloads
unzip ~/Downloads/chromedriver_linux64.zip -d ~/Downloads
chmod +x ~/Downloads/chromedriver
sudo mkdir ~/selenium
sudo mv -f ~/Downloads/chromedriver ~/selenium/chromedriver
sudo ln -s ~/selenium/chromedriver /usr/local/bin/chromedriver
sudo ln -s ~/selenium/chromedriver /usr/bin/chromedriver