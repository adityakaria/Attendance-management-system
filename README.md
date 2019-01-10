# POP_project
====
It is a project for attendance management, coded in java, consisting of the following parts:

- **##Android App**:
    - To read data from a student's QR code on their institution ID Card.
    - To save the data as a .csv file in the system after required data validation.
    - To export data via flask to a workstation.
    
- **##Java desktop code**:
    - This recieves data from the android app via flask
    - Then it processes the data, and arranges it as required into different lab dates.
    - It can be used to monitor attendance, and display batch data.
