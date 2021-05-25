# ChatBotExperiment
Chatbot Java web experiment

These are eclipse projects in Java

Eclipse project Name : ChatBotExperiment
PC Folder : ChatBotExperiment
This is an all in one conversation with the Chatbot service.
Sends a POST request to MZ Azure chatbot. Interprets the response
and displays it to the user. Console driven only.
Feel free to use this as a guide.
The Service at the moment is free, however that will
not last much longer. If your are trying to use this you may
have to investigate getting another QnA AI Service free account through
Microsoft. The account will only last a little over a month. This was setup
sometime in April 2021.

Eclipse project Name : ChatBotCovid19Service
PC Folder : testProjectWebService
This a Web Service. I have only ran it on local PC. Spring boot will launch tomcat
for you once you have everything installed.
has not been deployed fully.
Receives POST requests through GET method.
See project ChatbotTestService to test this.
This uses maven and Spring. You will need to download Spring and maven to use.

Eclipse project Name : ChatbotTestService
PC Folder : ChatbotTestService
This sends a POST request to CHatBotCovid19Service in order to test it. Console driven api 
within eclipse only.
