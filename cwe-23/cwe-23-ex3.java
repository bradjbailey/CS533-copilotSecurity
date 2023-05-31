// from: https://cwe.mitre.org/data/definitions/23.html example 3

// The action attribute of an HTML form is sending the upload file request to the Java servlet.
/*
<form action="FileUploadServlet" method="post" enctype="multipart/form-data">

Choose a file to upload:
<input type="file" name="filename"/>
<br/>
<input type="submit" name="submit" value="Submit"/>

</form>
*/

// When submitted the Java servlet's doPost method will receive the request, extract the name of the file from the Http request header, read the file contents from the request and output the file to the local upload directory.
// The following is the Java servlet code:
Synthesizing 8/10 solutions

=======
Suggestion 1

/*
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // Get the name of the file that the client uploaded.
    String fileName = request.getHeader("fileName");

    // Get the file contents.
    InputStream fileInputStream = request.getInputStream();

    // Save the file contents to a file on the server.
    FileOutputStream fileOutputStream = new FileOutputStream(fileName);

    int nextByte = fileInputStream.read();
    while (nextByte != -1) {
      fileOutputStream.write(nextByte);
      nextByte = fileInputStream.read();
    }

    fileOutputStream.close();
    fileInputStream.close();
  }
}
*/

// The Java servlet code lacks the necessary validation to prevent a malicious user from uploading a malicious file that could overwrite any file on the server.
// The following is a malicious POST request that will overwrite the /etc/passwd file on the server.
/*
POST /FileUploadServlet HTTP/1.1
Host:

=======
Suggestion 2


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the name of the file to upload
        String filename = request.getHeader("fileNameToUpload");

        // Get the file contents
        InputStream in = request.getInputStream();

        // Open the destination file
        OutputStream out = new FileOutputStream("C:\\uploads\\" + filename);

        // Copy the file contents to the destination file
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) >= 0) {
            out.write(buf, 0, len);
        }

        // Close the files
        in.close();
        out.close();
    }
}

=======
Suggestion 3

/*
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Get the name of the file from the request header
	String fileName = request.getHeader("fileName");
	// Get the file contents
	InputStream inputStream = request.getInputStream();
	// Write the file contents to the upload directory
	FileOutputStream outputStream = new FileOutputStream(uploadDir + fileName);
	int bytesRead = -1;
	byte[] buffer = new byte[BUFFER_SIZE];
	while ((bytesRead = inputStream.read(buffer)) != -1) {
		outputStream.write(buffer, 0, bytesRead);
	}
	outputStream.close();
	inputStream.close();
}
*/

// The following is the Java servlet code:
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Get the name of the file from the request header
	String fileName = request.getHeader("fileName");
	// Get the file contents
	InputStream inputStream = request.getInputStream();
	// Write the file contents to the upload directory
	FileOutputStream outputStream = new FileOutputStream(uploadDir + fileName);
	int bytesRead = -1;
	byte[] buffer = new byte[BUFFER_SIZE];
	while ((bytesRead = inputStream.read(buffer)) != -1) {
		outputStream.write(buffer, 0, bytesRead);
	}
	outputStream.close();
	inputStream.close();
}

=======
Suggestion 4

/*
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Get the filename from the request header
	String fileName = request.getHeader("fileName");
	// Get the file contents
	InputStream inputStream = request.getInputStream();
	// Write the file contents to the upload directory
	FileOutputStream outputStream = new FileOutputStream("c:\\uploads\\" + fileName);
	int bytesRead = -1;
	byte[] buffer = new byte[4096];
	while ((bytesRead = inputStream.read(buffer)) != -1) {
		outputStream.write(buffer, 0, bytesRead);
	}
	outputStream.close();
	inputStream.close();
}
*/

// This code is vulnerable to a path traversal attack. The attacker can manipulate the filename to cause the file to be written to an arbitrary location on the file system. For example, the following filename could be used to overwrite the system's password file:
// ../../../../../etc/passwd

// The following filename could be used to overwrite the system's startup script:
// ../../../../../etc/init.d/tomcat

// The following filename could be used to overwrite the system's boot script:
// ../../../../../etc/rc.local
// The following filename could be used to overwrite the system's boot.ini script:
// ../../../../../boot.ini

// The following filename could be used to overwrite the system's autoexec.bat script:
// ../../../../../autoexec.bat

// The following filename could be used to overwrite the system's hosts script:
// ../../../../../hosts

// The following filename could be used to overwrite the system's profile script:
// ../../../../../etc/profile

// The following filename could be used to overwrite the system's bashrc script:
// ../../../../../etc/bashrc

// The following filename could be used to overwrite the system's .bash_profile script:
// ../../../../../etc/.bash_profile

// The following filename could be used to overwrite the system's .bash_logout script:
// ../../../../../etc/.bash_logout

// The following filename could be used to overwrite the system's .profile script:
// ../../../../../etc/.profile

// The following filename could be used to overwrite the system's .login script:
// ../../../../../etc/.login

// The following filename could be used to overwrite the system's .cshrc script:
// ../../../../../etc/.cshrc

// The following filename could be used to overwrite the system's .logout script:
// ../../../../../etc/.logout

// The following filename could be used to overwrite the system's .tcsh

=======
Suggestion 5


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html";

    private static final String UPLOAD_DIRECTORY = "upload";

    private static final int BUFFER_SIZE = 4096;

    public void init() throws ServletException {
        // configure upload directory
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // gets absolute path of the web application
        String applicationPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY;

        // creates upload folder if it does not exists
        File uploadFolder = new File(uploadFilePath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        // write all files in upload folder
        for (Part part : request.getParts()) {
            String fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
        }

        request.setAttribute("message", "Upload has been done successfully!");
        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }

    // utility method to get file name from HTTP header content-disposition
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}

// The following is the JSP page that is used to display the upload status:

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Upload Example</title>
</head>
<body>
    <div style="margin: 100px 100px

=======
Suggestion 6

// FileUploadServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String filename = request.getHeader("filename");
        out.println("Received " + filename + "<br/>");

        InputStream in = request.getInputStream();
        OutputStream out = new FileOutputStream(new File("C:\\uploads\\" + filename));

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        out.flush();
        out.close();
        in.close();
    }
}

// The following is the code for the doGet method of the same Java servlet. This method will read the file from the local upload directory and output the file to the user's browser.
// FileUploadServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String filename = request.getParameter("filename");
        out.println("Received " + filename + "<br/>");

        File file = new File("C:\\uploads\\" + filename);

        FileInputStream in = new FileInputStream(file);

        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        out.flush();
        out.close();
        in.close();
    }
}

// The following is the code for the doGet method of the same Java servlet. This method will read the file from the local upload directory and output the file to the user's browser.
// FileUploadServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String filename = request.getParameter("filename");
        out.println("Received " + filename + "<br/>");

        File file = new File("C:\\uploads\\" + filename);

        FileInputStream in = new FileInputStream(file);

        int read = 0

=======
Suggestion 7

/*
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Get the name of the file that is uploaded.
        String fileName = request.getHeader("fileName");
        // Create a File object to represent the filename.
        File f = new File(fileName);

        // Create a byte array to hold the contents of the file.
        byte[] fileContents = new byte[(int)f.length()];

        // Create a DataInputStream to read the contents.
        DataInputStream dis = new DataInputStream(request.getInputStream());

        // Read the contents of the request into the byte array.
        dis.readFully(fileContents);

        // Create a FileOutputStream to write the contents to disk.
        FileOutputStream fos = new FileOutputStream(f);

        // Write the contents to disk.
        fos.write(fileContents);

        // Close the input stream and the file output stream.
        dis.close();
        fos.close();
    }
}
*/

// The code is vulnerable to a buffer overflow attack because the size of the file is not checked before it is read into the byte array. An attacker can exploit this vulnerability by sending a file that is larger than the byte array size. This will cause the data to overflow the buffer and corrupt the stack, allowing the attacker to execute arbitrary code.
// The following is an example of an attack that can be used to exploit this vulnerability:
/*
POST /FileUploadServlet HTTP/1.1
Host: www.example.com
Content-Type: multipart/form-data; boundary=---------------------------7d82751e2bc0858
Content-Length: 774
-----------------------------7d82751e2bc0858
Content-Disposition: form-data; name="filename"; filename="c:\test.txt"
Content-Type: text/plain
<@INCLUDE *C:\test.txt*@>
-----------------------------7d82751e2bc0858--
*/

// The attack is sending a file that is larger than the byte array size. The file is being sent as a multipart/form-data content type, which is the format that is used for uploading files. The Content-Length header is set to the size of the file, which is 774 bytes. However, the byte array is only 512 bytes, which will cause the data to overflow the buffer and corrupt the stack.

=======
Suggestion 8

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    // The directory where uploaded files will be saved, its relative to the web application directory.
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gets absolute path to root directory of web app.
        String applicationPath = request.getServletContext().getRealPath("");

        // Constructs path of the directory to save uploaded file.
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        // Creates the save directory if it does not exists.
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        // Part list (multi files).
        for (Part part : request.getParts()) {
            // Gets file name from HTTP header.
            String fileName = extractFileName(part);

            // Here we write the file to disk.
            part.write(uploadFilePath + File.separator + fileName);
        }

        // Notifies the user that the file is uploaded successfully.
        request.setAttribute("message", "Upload has been done successfully!");
        getServletContext().getRequestDispatcher("/response.jsp").forward(request, response);
    }

    // Extracts file name from HTTP header content-disposition.
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // Gets the file name from the header.
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}

// The following is the JSP code that will be executed when the file upload is complete.
/*
<html>
<body>
    <h1>File Upload Response</h1>
    <p>${message}</p>
</body>
</html>
*/

// This code is vulnerable to a directory traversal attack because it does not validate the file name.
// An attacker can upload a file with a malicious name such as: ../../../etc/passwd and the servlet will write the file to the root directory of the web application.
// This will overwrite the web application's web.xml file and allow the attacker to execute arbitrary code on the server.
