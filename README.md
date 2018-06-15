UserBooking Service
===================

Summary
-------
This microservice provides the functionality for retrieving statistics on the bookings of a particular user.
Requirements:
- At start up, the service loads user_features.txt into memory
- Answers queries like:
-- What is the number of bookings for a user
-- What is the total booking value for a user
-- What is the average length of stay for a user

Dependencies
------------

* Java 8 (Mandatory)

Maven Build
------------

```bash
    git clone git@gitlab.cwscloud.net:shadow/doc-gen-service.git
    mvn clean install
```

Maven Run
----------------

```bash
    mvn spring-boot:run
```

Command Line Run
----------------

```bash
	cd target/
    java -jar userbooking-service-1.0-SNAPSHOT.jar
```

Test
-------------------

```bash
    curl http://localhost:8585/userBooking/numberOfBookings/1
```

Endpoints 
-----------
* Total number of bookings for a user - <http://localhost:8585/userBooking/numberOfBookings/{userId}>
* Total booking value for a user - <http://localhost:8585/userBooking/totalBookingValue/{userId}>
* Average length of stay for a user - <http://localhost:8585/userBooking/averageLengthOfStay/{userId}>

Configuration
----------------------------------

|                Property                 |                 Description                 |                 Default Value                  |  
| --------------------------------------- | ------------------------------------------- | ---------------------------------------------- |  
| server.port | The port for the microservice to listen on | 8585 |  
| userbooking.data.file.location | Path to location of the user features data file. | file:///C:/userbooking/data/user-features.txt |
| logging.file | full file name, excluding rollingpattern.log  | {environment temp location}/userBooking and is suffixed with rolling pattern (%d{yyyy-MM-dd}.log) |
| logging.path | path where log file will be created. If set, it replaces path part of logging.file property defined above  | {environment temp location} or '/tmp' if not set |


Overriding Default Configuration
----------------------------------
The application should startup as is provided that all the mandatory dependencies are installed correctly
on the same host as the user booking service is being to deployed to.

In case any of the properties above need overriding, create a yaml config file somewhere in the file system i.e: `config.yml` with the relevant
overrides and make sure you start the service from the command line with the `--spring.config.location` parameter pointing to the config path i.e: file:///opt/userbooking/config.yml.

For more information please refer to: <https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html>
