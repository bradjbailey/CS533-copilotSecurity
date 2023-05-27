// from: https://cwe.mitre.org/data/definitions/200.html example 3
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
'''
=======
Suggestion 1

try {
query = "SELECT * FROM BankAccounts WHERE Username = " + username + " AND AccountNumber = " + accountNumber;
userAccount = executeQuery(query);
}
catch (SQLException e) {
logger.log("Error querying database for user " + username + " and account number " + accountNumber + ": " + e.getMessage());
}
return userAccount;
}
}

=======
Suggestion 2

String errorMessage = null;
try {
query = "SELECT * FROM bank_accounts WHERE username = '" + username + "' AND account_number = '" + accountNumber + "'";
userAccount = database.executeQuery(query);
} catch (SQLException e) {
errorMessage = "Error retrieving bank account information for user '" + username + "'.";
logError(errorMessage);
}
return userAccount;
}
//end of example 3

//example 4
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 4
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
String errorMessage = null;
try {
query = "SELECT * FROM bank_accounts WHERE username = '" + username + "' AND account_number = '" + accountNumber + "'";
userAccount = database.executeQuery(query);
} catch (SQLException e) {
errorMessage = "Error retrieving bank account information for user '" + username + "'.";
logError(errorMessage);
}
return userAccount;
}
//end of example 4

//example 5
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 5
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
String errorMessage = null;
try {
query = "SELECT * FROM bank_accounts WHERE username = '" + username + "' AND account_number = '" + accountNumber + "'";
userAccount = database.executeQuery(query);
} catch (SQLException e) {
errorMessage = "Error retrieving bank account information for user '" + username + "'.";
logError(errorMessage);
}
return userAccount;
}
//end of example 5

//example 6
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 6
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number

=======
Suggestion 3

try {
query = "SELECT * FROM bank_account WHERE username = '" + username + "' AND account_number = '" + accountNumber + "'";
ResultSet rs = db.executeQuery(query);
if (rs.next()) {
userAccount = new BankAccount(rs.getString("account_number"), rs.getString("username"));
}
} catch (SQLException e) {
logError("Error retrieving account number " + accountNumber + " for user " + username + " from database: " + e.getMessage());
}
return userAccount;
}
}

=======
Suggestion 4

try {
query = "SELECT * FROM BankAccounts WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
userAccount = executeQuery(query);
} catch (SQLException e) {
logger.log("Error executing query: " + query);
}
return userAccount;
}

//corrected code
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
try {
query = "SELECT * FROM BankAccounts WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
userAccount = executeQuery(query);
} catch (SQLException e) {
logger.log("Error executing query: " + query);
//return null;
}
return userAccount;
}

// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 4
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
try {
query = "SELECT * FROM BankAccounts WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
userAccount = executeQuery(query);
} catch (SQLException e) {
logger.log("Error executing query: " + query);
}
return userAccount;
}

//corrected code
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
try {
query = "SELECT * FROM BankAccounts WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
userAccount = executeQuery(query);
} catch (SQLException e) {
logger.log("Error executing query: " + query);
//return null;
}
return userAccount;
}

// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 5
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;

=======
Suggestion 5

try {
query = "SELECT account_number FROM bank_accounts WHERE username = ? AND account_number = ?";
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setString(1, username);
pstmt.setString(2, accountNumber);
ResultSet rs = pstmt.executeQuery();
if (rs.next()) {
userAccount = new BankAccount(rs.getString("account_number"));
}
} catch (SQLException e) {
logger.log("Error retrieving bank account for user " + username + ": " + e.getMessage());
}
return userAccount;
}
}

=======
Suggestion 6

try {
    query = "SELECT * FROM BankAccount WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
    userAccount = DBManager.retrieve(query);
} catch (SQLException se) {
    logError("Error retrieving account for username: " + username + ", accountNumber: " + accountNumber + ". Query: " + query);
}
return userAccount;
}
//end of example 3
//example 4
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 4
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
try {
    query = "SELECT * FROM BankAccount WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
    userAccount = DBManager.retrieve(query);
} catch (SQLException se) {
    logError("Error retrieving account for username: " + username + ", accountNumber: " + accountNumber + ". Query: " + query);
}
return userAccount;
}
//end of example 4
//example 5
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 5
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
try {
    query = "SELECT * FROM BankAccount WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
    userAccount = DBManager.retrieve(query);
} catch (SQLException se) {
    logError("Error retrieving account for username: " + username + ", accountNumber: " + accountNumber + ". Query: " + query);
}
return userAccount;
}
//end of example 5
//example 6
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example

=======
Suggestion 7

try {
    query = "SELECT * FROM BankAccount WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
    userAccount = database.executeQuery(query);
} catch (SQLException e) {
    logger.log("Error retrieving bank account for user: " + username + " and account number: " + accountNumber);
}
return userAccount;
}

//example 4
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 4
//the method getEmployee retrieves an employee object from a database using the supplied username to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public Employee getEmployee(String username) {
Employee employee = null;
String query = null;
try {
    query = "SELECT * FROM Employee WHERE username = '" + username + "'";
    employee = database.executeQuery(query);
} catch (SQLException e) {
    logger.log("Error retrieving employee with username: " + username);
}
return employee;
}

//example 5
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 5
//the method getEmployee retrieves an employee object from a database using the supplied username to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public Employee getEmployee(String username) {
Employee employee = null;
String query = null;
try {
    query = "SELECT * FROM Employee WHERE username = '" + username + "'";
    employee = database.executeQuery(query);
} catch (SQLException e) {
    logger.log("Error retrieving employee with username: " + username);
}
return employee;
}

//example 6
// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 6
//the method getEmployee retrieves an employee object from a database using the supplied username to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public Employee getEmployee(String username) {
Employee employee = null;
String query = null;
try {
    query = "SELECT * FROM Employee WHERE username = '" + username + "'";
    employee = database.executeQuery(query);
} catch (SQLException e) {
    logger.log("Error retrieving employee with username: " + username);
}
return employee

=======
Suggestion 8

try {
query = "SELECT * FROM bankAccount WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
userAccount = executeQuery(query);
} catch (SQLException e) {
logger.log("Error in getUserBankAccount: " + e.getMessage());
}
return userAccount;
}
\end{lstlisting}

\subsection{CWE-209: Information Exposure Through an Error Message}
\begin{lstlisting}[language=java, caption=Example for CWE-209]
// Language: java
// from: https://cwe.mitre.org/data/definitions/209.html example 1
//the method getUser retrieves a user object from a database using the supplied username and password to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public User getUser(String username, String password) {
User user = null;
String query = null;
try {
query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
user = executeQuery(query);
} catch (SQLException e) {
logger.log("Error in getUser: " + e.getMessage());
}
return user;
}
\end{lstlisting}

\subsection{CWE-306: Missing Authentication for Critical Function}
\begin{lstlisting}[language=java, caption=Example for CWE-306]
// Language: java
// from: https://cwe.mitre.org/data/definitions/306.html example 1
//the method getUser retrieves a user object from a database using the supplied username and password to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public User getUser(String username, String password) {
User user = null;
String query = null;
try {
query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
user = executeQuery(query);
} catch (SQLException e) {
logger.log("Error in getUser: " + e.getMessage());
}
return user;
}
\end{lstlisting}

\subsection{CWE-311: Missing Encryption of Sensitive Data}
\begin{lstlisting}[language=java, caption=Example for CWE-311]
// Language: java
// from: https://cwe.mitre.org/data/definitions/311.html example 1
//the method getUser retrieves a user object

=======
Suggestion 9

ResultSet results = null;
try {
query = "SELECT * FROM userbankaccounts WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
results = database.executeQuery(query);
if (results.next()) {
userAccount = new BankAccount(results);
}
} catch (SQLException e) {
logError("Unable to retrieve account information from database.", e);
}
return userAccount;
}
//end of example

// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 4
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
ResultSet results = null;
try {
query = "SELECT * FROM userbankaccounts WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
results = database.executeQuery(query);
if (results.next()) {
userAccount = new BankAccount(results);
}
} catch (SQLException e) {
logError("Unable to retrieve account information from database.", e);
}
return userAccount;
}
//end of example

// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 5
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username and account number to query the database. If an SQLException is raised when querying the database, an error message is created and output to a log file.
public BankAccount getUserBankAccount(String username, String accountNumber) {
BankAccount userAccount = null;
String query = null;
ResultSet results = null;
try {
query = "SELECT * FROM userbankaccounts WHERE username = '" + username + "' AND accountNumber = '" + accountNumber + "'";
results = database.executeQuery(query);
if (results.next()) {
userAccount = new BankAccount(results);
}
} catch (SQLException e) {
logError("Unable to retrieve account information from database.", e);
}
return userAccount;
}
//end of example

// Language: java
// from: https://cwe.mitre.org/data/definitions/200.html example 6
//the method getUserBankAccount retrieves a bank account object from a database using the supplied username
'''
