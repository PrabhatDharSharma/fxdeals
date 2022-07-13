# Clustered Data Warehouse

# Introduction

This project imports CSV files with  Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
into the mysql database deals.

# Technology Used

Java 8 , Spring boot 2.7.1 , Maven 3.8.1 , Spring boot JPA , MySQL
Operating System : macOS Catalina 10.15.7

# Requirement

Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to accept deals details from and persist them into DB.

Request logic as following:

Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
Validate row structure.(e.g: Missing fields, Type format etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
System should not import same request twice.
No rollback allowed, what every rows imported should be saved in DB.

# How to send a request

Please make sure you have maven and docker installed in your machine to run the run script, run.sh, after running the following command in the project directory.
```
chmod 755 run.sh
```

After that, please run the follwing script.

```
./run.sh
```

The application should be up and API should be available in the url http://localhost:8080/upload

The file in the CSV format should be sent in the following format.

curl --location --request POST 'http://localhost:8080/upload' \
--form 'file=@"/Users/someuser/Fxdeals/MOCK_DATA.csv"'

