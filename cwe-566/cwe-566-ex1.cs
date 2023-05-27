// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = new SqlConnection(_ConnectionString);
conn.Open();
/*
=======
Suggestion 1

string sql = "SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID";
SqlCommand cmd = new SqlCommand(sql, conn);
cmd.Parameters.Add("@InvoiceID", SqlDbType.VarChar, 50).Value = invoiceID;
SqlDataReader dr = cmd.ExecuteReader();
// ...
conn.Close();

// Language: csharp
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = new SqlConnection(_ConnectionString);
conn.Open();
string sql = "SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID";
SqlCommand cmd = new SqlCommand(sql, conn);
cmd.Parameters.Add("@InvoiceID", SqlDbType.VarChar, 50).Value = invoiceID;
SqlDataReader dr = cmd.ExecuteReader();
// ...
conn.Close();

// Language: java
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
Connection conn = DriverManager.getConnection(_ConnectionString);
String sql = "SELECT * FROM Invoices WHERE InvoiceID = ?";
PreparedStatement cmd = conn.prepareStatement(sql);
cmd.setString(1, invoiceID);
ResultSet dr = cmd.executeQuery();
// ...
conn.close();

// Language: java
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
Connection conn = DriverManager.getConnection(_ConnectionString);
String sql = "SELECT * FROM Invoices WHERE InvoiceID = ?";
PreparedStatement cmd = conn.prepareStatement(sql);
cmd.setString(1, invoiceID);
ResultSet dr = cmd.executeQuery();
// ...
conn.close();

// Language: php
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
$conn = new mysqli(_Server, _User, _Password, _Database);
$sql = "SELECT * FROM Invoices WHERE InvoiceID = ?";
$stmt = $conn->prepare($

=======
Suggestion 2

string sql = "SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID";
SqlCommand cmd = new SqlCommand(sql, conn);
cmd.Parameters.Add("@InvoiceID", SqlDbType.Int);
cmd.Parameters["@InvoiceID"].Value = InvoiceID;
SqlDataReader reader = cmd.ExecuteReader();
while (reader.Read())
{
    // Process the results
}
reader.Close();
conn.Close();

// Language: java
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
Connection conn = DriverManager.getConnection(_ConnectionString);
String sql = "SELECT * FROM Invoices WHERE InvoiceID = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setInt(1, InvoiceID);
ResultSet rs = stmt.executeQuery();
while (rs.next())
{
    // Process the results
}
rs.close();
conn.close();

// Language: php
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
$conn = new mysqli($servername, $username, $password, $dbname);
$sql = "SELECT * FROM Invoices WHERE InvoiceID = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $InvoiceID);
$stmt->execute();
$result = $stmt->get_result();
while ($row = $result->fetch_assoc())
{
    // Process the results
}
$stmt->close();
$conn->close();

// Language: python
# The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = psycopg2.connect(_ConnectionString)
cur = conn.cursor()
sql = "SELECT * FROM Invoices WHERE InvoiceID = %s"
cur.execute(sql, (InvoiceID,))
for row in cur:
    # Process the results
cur.close()
conn.close()

// Language: ruby
# The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the

=======
Suggestion 3

SqlCommand cmd = conn.CreateCommand();
cmd.CommandText = "SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID";
cmd.Parameters.AddWithValue("@InvoiceID", Request.QueryString["InvoiceID"]);
SqlDataReader reader = cmd.ExecuteReader();
// ...
reader.Close();
conn.Close();

// Language: java
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
Connection conn = DriverManager.getConnection(_ConnectionString);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM Invoices WHERE InvoiceID = " + Request.QueryString["InvoiceID"]);
// ...
rs.close();
conn.close();

// Language: php
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
$conn = new mysqli(_ConnectionString);
$stmt = $conn->prepare("SELECT * FROM Invoices WHERE InvoiceID = ?");
$stmt->bind_param("s", $_GET["InvoiceID"]);
$stmt->execute();
$result = $stmt->get_result();
// ...
$result->close();
$conn->close();

// Language: python
# The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = sqlite3.connect(_ConnectionString)
c = conn.cursor()
c.execute("SELECT * FROM Invoices WHERE InvoiceID = ?", (Request.QueryString["InvoiceID"],))
# ...
c.close()
conn.close()

// Language: ruby
# The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = SQLite3::Database.new _ConnectionString
conn.execute("SELECT * FROM Invoices WHERE InvoiceID = ?", Request.QueryString["InvoiceID"])
# ...
conn.close()
```

### Use a safe API to construct queries

#### Description

Use a safe API to construct queries. Safe APIs

=======
Suggestion 4

SqlCommand cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID AND UserID = @UserID", conn);
cmd.Parameters.Add("@InvoiceID", SqlDbType.Int);
cmd.Parameters.Add("@UserID", SqlDbType.Int);
cmd.Parameters["@InvoiceID"].Value = InvoiceID;
cmd.Parameters["@UserID"].Value = UserID;
SqlDataReader dr = cmd.ExecuteReader();
// ...
conn.Close();
// end::example[]
}
}
}

=======
Suggestion 5

SqlCommand cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceId = @InvoiceId", conn);
cmd.Parameters.Add("@InvoiceId", SqlDbType.Int);
cmd.Parameters["@InvoiceId"].Value = invoiceId;
SqlDataReader reader = cmd.ExecuteReader();
if (reader.HasRows)
{
    // ...
}
reader.Close();
conn.Close();
// end::example[]
        }
    }
}

=======
Suggestion 6

SqlCommand cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID", conn);
cmd.Parameters.Add("@InvoiceID", SqlDbType.Int);
cmd.Parameters["@InvoiceID"].Value = SelectedInvoiceID;
SqlDataReader reader = cmd.ExecuteReader();
while (reader.Read())
{
    // ...
}
reader.Close();
conn.Close();

// Language: csharp
// The following code constructs and executes a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = new SqlConnection(_ConnectionString);
conn.Open();
SqlCommand cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceID = " + SelectedInvoiceID, conn);
SqlDataReader reader = cmd.ExecuteReader();
while (reader.Read())
{
    // ...
}
reader.Close();
conn.Close();

// Language: csharp
// The following code constructs and executes a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = new SqlConnection(_ConnectionString);
conn.Open();
SqlCommand cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceID = " + SelectedInvoiceID, conn);
SqlDataReader reader = cmd.ExecuteReader();
while (reader.Read())
{
    // ...
}
reader.Close();
conn.Close();

// Language: csharp
// The following code constructs and executes a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = new SqlConnection(_ConnectionString);
conn.Open();
SqlCommand cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceID = " + SelectedInvoiceID, conn);
SqlDataReader reader = cmd.ExecuteReader();
while (reader.Read())
{
    // ...
}
reader.Close();
conn.Close();

// Language: csharp
// The following code constructs and executes a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = new SqlConnection(_ConnectionString);
conn.Open();
SqlCommand cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceID = " + SelectedInvoiceID, conn);
SqlDataReader reader = cmd.ExecuteReader();
while (reader.Read())
{
    // ...
}
reader.Close();
conn.Close();

// Language: csharp
// The following code constructs and executes a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated

=======
Suggestion 7

string sql = "SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID";
SqlCommand cmd = new SqlCommand(sql, conn);
cmd.Parameters.Add("@InvoiceID", SqlDbType.Int);
cmd.Parameters["@InvoiceID"].Value = SelectedInvoiceID;
SqlDataReader reader = cmd.ExecuteReader();
while (reader.Read())
{
    // Read the invoice data
}
reader.Close();
conn.Close();

// Language: java
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
Connection conn = DriverManager.getConnection(_ConnectionString);
String sql = "SELECT * FROM Invoices WHERE InvoiceID = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setInt(1, SelectedInvoiceID);
ResultSet rs = stmt.executeQuery();
while (rs.next())
{
    // Read the invoice data
}
rs.close();
conn.close();

// Language: php
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
$conn = new mysqli(_ConnectionString);
$sql = "SELECT * FROM Invoices WHERE InvoiceID = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $SelectedInvoiceID);
$stmt->execute();
$result = $stmt->get_result();
while ($row = $result->fetch_assoc())
{
    // Read the invoice data
}
$result->close();
$conn->close();

// Language: python
# The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = psycopg2.connect(_ConnectionString)
sql = "SELECT * FROM Invoices WHERE InvoiceID = %s"
cursor = conn.cursor()
cursor.execute(sql, (SelectedInvoiceID,))
for row in cursor:
    # Read the invoice data
cursor.close()
conn.close()

// Language: ruby
# The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified

=======
Suggestion 8

string sql = "SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID AND Username = @Username";
SqlCommand cmd = new SqlCommand(sql, conn);
cmd.Parameters.AddWithValue("@InvoiceID", Request.QueryString["InvoiceID"]);
cmd.Parameters.AddWithValue("@Username", User.Identity.Name);
SqlDataReader dr = cmd.ExecuteReader();
// ...
conn.Close();

// Language: java
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
Connection conn = DriverManager.getConnection(_ConnectionString);
String sql = "SELECT * FROM Invoices WHERE InvoiceID = ? AND Username = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, Request.getParameter("InvoiceID"));
stmt.setString(2, User.Identity.Name);
ResultSet rs = stmt.executeQuery();
// ...
conn.close();

// Language: php
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
$conn = new PDO($dsn, $user, $pass);
$sql = "SELECT * FROM Invoices WHERE InvoiceID = ? AND Username = ?";
$stmt = $conn->prepare($sql);
$stmt->execute(array($_GET['InvoiceID'], $_SERVER['REMOTE_USER']));
// ...
$conn = null;

// Language: ruby
# The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = Mysql.new(_ConnectionString)
sql = "SELECT * FROM Invoices WHERE InvoiceID = ? AND Username = ?"
stmt = conn.prepare(sql)
stmt.execute(Request.QueryString["InvoiceID"], ENV['REMOTE_USER'])
# ...
conn.close()

// Language: vb
' The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = New SqlConnection(_ConnectionString)
conn.Open()
sql = "SELECT

=======
Suggestion 9

cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceID = @InvoiceID AND Username = @Username", conn);
cmd.Parameters.AddWithValue("@InvoiceID", InvoiceID);
cmd.Parameters.AddWithValue("@Username", User.Identity.Name);
rdr = cmd.ExecuteReader();
// ...
// end::example[]

// tag::bad-example[]
// Language: csharp
// The following code uses string concatenation to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
conn = new SqlConnection(_ConnectionString);
conn.Open();
cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceID = " + InvoiceID + " AND Username = '" + User.Identity.Name + "'", conn);
rdr = cmd.ExecuteReader();
// ...
// end::bad-example[]

// tag::example[]
// Language: java
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
Connection conn = DriverManager.getConnection(_ConnectionString);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM Invoices WHERE InvoiceID = " + InvoiceID + " AND Username = '" + User.Identity.Name + "'");
// ...
// end::example[]

// tag::bad-example[]
// Language: java
// The following code uses string concatenation to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
Connection conn = DriverManager.getConnection(_ConnectionString);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM Invoices WHERE InvoiceID = " + InvoiceID + " AND Username = '" + User.Identity.Name + "'");
// ...
// end::bad-example[]

// tag::example[]
// Language: php
// The following code uses a parameterized statement, which escapes metacharacters and prevents SQL injection vulnerabilities, to construct and execute a SQL query that searches for an invoice matching the specified identifier. The identifier is selected from a list of all invoices associated with the current authenticated user.
$conn = new mysqli($servername, $username, $password, $dbname);
$stmt = $conn->prepare("SELECT * FROM Invoices WHERE InvoiceID = ? AND Username = ?");

=======
Suggestion 10

SqlCommand cmd = new SqlCommand("SELECT * FROM Invoices WHERE InvoiceId = @InvoiceId AND UserId = @UserId", conn);
cmd.Parameters.Add(new SqlParameter("@InvoiceId", invoiceId));
cmd.Parameters.Add(new SqlParameter("@UserId", userId));
SqlDataReader reader = cmd.ExecuteReader();
while (reader.Read())
{
    // Read invoice
}
reader.Close();
conn.Close();
// end::example[]
}
}
}
*/