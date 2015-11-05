burum
============
Java library for communicating via TCP/IP with an Inmarsat-C Land Earth Station (LES) according to the Xantic [Inmarsat-C PSDN and TCP/IP User Manual](http://www.xantic.net/internet/files/products/inmarsat/inmarsat_c/support_docs/Inm-C%20PSDN-TCP-IP%20Mnl%20V1.pdf).

A copy of the user manual is in this repository [here](https://github.com/amsa-code/burum/raw/master/src/docs/Inm-C%20PSDN-TCP-IP%20Mnl%20V1.pdf).

Status: *pre-alpha*

Test connection
-----------------
Logs in to the LES and calls the help command then logs off:
```java
new Les(host, port, username, password).test();
```
Output
```
Trying 678...Open

Welcome to BURUM LES PSDN   Service
Please enter username: 
Please enter password: 
> 
Address ... Change an address for a message.
Delete .... Delete an earlier entered message.
Dnid ...... Manage closed network information.
Dsend ..... Send distress message to mobile unit.
Egc ....... Send broadcast message.
Pin ....... Change the password.
Poll ...... Send poll command.
Quit ...... End this session.
Scan ...... Scan messages in store.
Send ...... Send message to mobile unit.
User ...... Register with username and password.
View ...... View an earlier entered message.
> 
Logging off...
```

