#  MoneyTransfer App


Basically this application provides ability to :

    1> Create Account /Lists accounts

    2> Transfer money from Account A to Account B

    3> Carry out Batch Transfers 


  #1> Endpoints available
  
	1> POST localhost:4567/accounts -- This is for creating account

	2> GET localhost:4567/accounts - This is for getting all accounts

	3> POST localhost:4567/transfer - This is for money transfer from account A to B

	4> GET localhost:4567/transfer/status/{transactionId} -- This is for checking current status 
	of Transfer

	5> POST localhost:4567/batchTransfer - for Batch Transfer


#2> Few words about Design/Architecture:

When a user submits request for money transfer(endpoint#3), the request is uploaded to a queue and a transaction id is sent to back to user as a response . User can use this transactionId to check status of transfer using another endpoint(endpoint#4).

System picks up each transfer from the queue and sends it processing to AcccountProcessor, this is to ensure that debit transaction for a given fromaccount happens in the order of submission but at the same time, accounts transfers are independent and concurrent.

For example, if you send following transfers( fromAccount ->toAccount) :

	1 -> 2

	1->3

	1->4

	2->3

	3->4

	4->6


System will rearrange these transaction as :

	AccountProcessor1 : Debit from 1 (for 2 ) -> Debit from 1 for 3 -> Debit from 1 for 4 

	AccountProcessor1 will also spawn threads for credit transaction as soon as debit is complete however next debit transaction for Account will not wait for credit to complete ( only wait is on Debit Transaction ,accountwise)

					
	AccountProcesor 2 : Debit from 2 & spawn a thread for credit to 3

	AccountProcesor 3 : Debit from 3 & spawn a thread for credit to 4

	AccountProcesor 4 : Debit from 4 & spawn a thread for credit to 6


Here AccountPrcoessor1, AccountProcessor2 and so on are executing concurrently.

Also note that debit transaction are ordered and happen single threaded way. 
As soon as debit transaction is completed inside a AccountWiseProcessor for a given transfer, it spawns a new thread for credit transaction and immediately goes to process next debit transaction for the account.


It means the only dependency or ordering of transactions is debit for a given account,rest all is multi-threaded.

Obvious reason for making debit single threaded ( for a given account) is to make sure we honour the order of submission.

	#Of course, if we want to turn this into multi-threaded, it can be easily done as 
	SOLID principles are followed .

The dependencies is through interfaces so one class really doesnt know about other one. We have also made sure, each class has single responsibility and is open for extension. 

	#For example, most implementations in this exercise are memory based however if we want to turn
	to some persistent based implementations , we can easily do so by implementing the given interface.

# If you are running low on time, you can skip "How to Run"(#3) and go to Quick Demo(#4)/Detailed Demo(#5) Sections.

====================
# 3> How to run the App:


	Prerequisites : 
	1> Install JDK 1.8 

 Without any additional thing:
 
	Option 1 : execute run.sh (or run.bat) 


	Option 2 : navigate to moneytransfer root directory and execute java -jar moneytransfer-0.0.1-SNAPSHOT.jar

If you want to verify if application is up and running , execute following GET request:
GET http://localhost:4567/accounts . ( It will return success)

Alternative ways of running Application with Maven( or if you want to Build & run):


Option 1: 
	run following command 

	mvn exec:java

Option 2: 

	1> run following command 

	mvn clean install

	2> Now run following command

	java -jar target/moneytransfer-0.0.1-SNAPSHOT.jar


# 4> Quick Demo 
        You can look at MoneyTrasferAppDemo.txt available at root directory.

	This file has a recording of input and output for a end to end test, simulating account creation and transfer.


If you like, you can even execute com.technopreneur.moneytransfer.EndToEndMoneyTransferTest.java to see live output.


# 5> Detailed Demo 
==============================

	# Step 1. Create two accounts : POST  http://localhost:4567/accounts
Step 1a. Input for first Account
{
 accountId:1,
 name:Scott,
 balance:1000
}

Output for first Account
{"status":"SUCCESS","data":{"accountId":1,"name":"Scott","balance":1000}}

Step 1b. Input for second Account
{
 accountId:2,
 name:Tiger,
 balance:1000
}


{"status":"SUCCESS","data":{"accountId":2,"name":"Tiger","balance":1000}}

==============================

	# Step 2. Get  accounts : GET  http://localhost:4567/accounts
Output showing created Accounts


{"status":"SUCCESS","data":[{"accountId":1,"name":"Scott","balance":1000},{"accountId":2,"name":"Tiger","balance":1000}]}

	Please note that balances =1000 for both Scott and Tiger!

==============================

	#Step 3. Execute the Transfer of 100 : 

	# POST  http://localhost:4567/transfer 

	# Input : {"fromAccount" : "1","toAccount" :"2","amount":"100" }


#Output 
Confirming transfer queued

	{"status":"SUCCESS",
	#"message":"Your transfers are succesfully queued !} 

==============================


#Step 4. Get  Transfer status : 

	#GET  http://localhost:4567/transfer/status/{3d730dca-1ee1-4a1e-aef2-6ce643224f44}

Output showing Transfer Status
	 "Transaction is Successful"

==============================

#Step 5. Check Account Balance to verify the transfer : GET  http://localhost:4567/accounts

Output showing updated Accounts Balances

		{"status":"SUCCESS",
		"data":[
		#{"accountId":1,"name":"Scott","balance":900},
		#{"accountId":2,"name":"Tiger","balance":1100}

		]}

Y
==============================


# Issues
For any issues , you can reach out to milind.technopreneur@gmail.com
