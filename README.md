[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

# NewsGyan
NewsGyan is a desktop application that helps one stay connected to the world by keeping clients up-to-date with the daily news, blogs and brief video reports.

Users can create account, read news, read/write blogs, view vlogs, get recommendations and enjoy many other cool desktop app features for a seamless news reading experience.
A central database server to store all user’s information, content and stream vlogs.

## Basic Features:
1. Login/Register for the new user.
2. Fetch NEWS articles from multiple trusted websites.
3. Ask the users about their preferences at the time of signup (may be updated later as well in profile section)
4. News articles must be displayed based on the preferences of the user (sports, politics, world.)
5. Each article content should be concise and limited (show first few lines or so (be creative))
6. Articles can be searched based on their category.
7. User can click on these articles to read them in further detail
8. Weather report, currency exchange rates and clock can be shown as well.
9. News displayed using creative UI (different tabs, views and cards to categorize articles).
10. Users and journalists can write their blogs on any particular topic and other users can read it.

## Advanced Features:
1. Users or journalists can post videos shot by them(vlogs) on platform so that other users can watch their content.
2. Users can upvote or downvote any vlog.
3. Some articles can have images as well (be creative and add your custom image if there is none provided).
4. User should be able to save their favourite articles locally and view them any time.
5. Create a ‘recommended for you’ section where you can show articles of interest to users on basis of preferences and reading habits.
6. Widget for the application that always stays visible on desktop and shows Top HeadLines (redirects to the application when clicked).
7. Might add expiring time of blogs, or use any general validity for blogs so that old and irrelevant blogs can be deleted from server.

## Technology, libraries and API's used:
1. Java, Java Swing(for UI), SQL for database
2. JDBC Api
3. [News Api](https://newsapi.org/)
4. JSON/GSON
5. [vlcj](https://github.com/caprica/vlcj) 

Video streaming is done through RTP(Real-time Transport Protocol) with help of vlcj library. 
The server node(serving the video and storing other information) and the client node establish socket connection to transfer any data(user info, blogs, video/audio).

### Project Maintainer:
* [Aashish Tandon](https://github.com/aashishat718)

![Java](https://img.shields.io/badge/java-%230095D5.svg?&style=for-the-badge&logo=java&logoColor=white)
![Git](https://img.shields.io/badge/git%20-%23F05033.svg?&style=for-the-badge&logo=git&logoColor=white)
![Github](https://img.shields.io/badge/github%20-%23121011.svg?&style=for-the-badge&logo=github&logoColor=white)
