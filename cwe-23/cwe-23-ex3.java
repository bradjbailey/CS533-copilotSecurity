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
'''
=======
Suggestion 1

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 500 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;

    public void init( ){
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload");
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, java.io.IOException {

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter( );
        if( !isMultipart ){
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax( maxFileSize );

        try{
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");

            while ( i.hasNext () )
            {
                FileItem fi = (FileItem)i.next();
                if ( !fi.isFormField () )
                {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // Write the file
                    if( fileName.lastIndexOf("\\") >=

=======
Suggestion 2

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// gets absolute path of the web application
		String applicationPath = request.getServletContext().getRealPath("");
		// constructs path of the directory to save uploaded file
		String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY;

		// creates upload folder if it does not exists
		File uploadFolder = new File(uploadFilePath);
		if (!uploadFolder.exists()) {
			uploadFolder.mkdirs();
		}

		// gets file part from the request
		String fileName = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			Part filePart = request.getPart("filename");
			if (filePart != null) {
				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
				fileName = filePart.getSubmittedFileName();
			}
			System.out.println("The file name is: " + fileName);

			// obtains output stream of the upload file
			File outputFile = new File(uploadFilePath + File.separator + fileName);
			outputStream = new FileOutputStream(outputFile);

			// writes input stream to output stream
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			// close streams
			inputStream.close();
			outputStream.close();

			// sends response to client
			response.getWriter().print("UPLOAD DONE");

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("UPLOAD FAILED");
		}
	}
}

=======
Suggestion 3

/*
package com.mkyong;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUploadServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "upload";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// process only if its multipart content
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
					}
				}

				// File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}

		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}

		request.getRequestDispatcher("/result.jsp").forward(request, response);

	}

}
*/

// The Apache Commons FileUpload library is used to parse the request and read the uploaded file contents.
// The following is the Maven dependency to include the Apache Commons FileUpload library in your project.
/*
<dependency>
	<groupId>commons-fileupload</groupId>
	<artifactId>commons-fileupload</artifactId>
	<version>1.3</version>
</dependency>
*/
// The following is the JSP page to display the upload result:
/*
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Upload Result</title>
</head>
<body>
	<h1>${message}</h1>
</body>
</html>
*/

// The following is the web.xml configuration to map the servlet to the URL pattern /FileUploadServlet:
/*
<servlet>
	<servlet-name>FileUploadServlet</s

=======
Suggestion 4

/*
public class FileUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String uploadPath;

    public void init() {
        uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String fileName = null;
        for (Part part : request.getParts()) {
            fileName = getFileName(part);
            part.write(uploadPath + File.separator + fileName);
        }
        request.setAttribute("message", fileName + " File uploaded successfully!");
        getServletContext().getRequestDispatcher("/response.jsp").forward(
                request, response);
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
*/

// The getFileName method extracts the file name from the Http request header. The doPost method reads the file contents from the request and writes it to the local upload directory.
// The following is the response.jsp file code:
/*
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload Response</title>
</head>
<body>
<h3>${message}</h3>
</body>
</html>
*/

// The ${message} is a JSP expression language syntax. It is replaced with the value of the message attribute in the request object.
// The following is the web.xml file entry:
/*
<servlet>
    <servlet-name>FileUploadServlet</servlet-name>
    <servlet-class>com.example.servlet.FileUploadServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>FileUploadServlet</servlet-name>
    <url-pattern>/FileUploadServlet</url-pattern>
</servlet

=======
Suggestion 5

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.sql.*;

public class FileUploadServlet extends HttpServlet {

   private boolean isMultipart;
   private String filePath;
   private int maxFileSize = 50 * 1024;
   private int maxMemSize = 4 * 1024;
   private File file ;

   public void init( ){
      // Get the file location where it would be stored.
      filePath = 
             getServletContext().getInitParameter("file-upload"); 
   }
   public void doPost(HttpServletRequest request, 
               HttpServletResponse response)
              throws ServletException, java.io.IOException {
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
      if( !isMultipart ){
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet upload</title>");  
         out.println("</head>");
         out.println("<body>");
         out.println("<p>No file uploaded</p>"); 
         out.println("</body>");
         out.println("</html>");
         return;
      }
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
      List fileItems = upload.parseRequest(request);
	
      // Process the uploaded file items
      Iterator i = fileItems.iterator();

      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet upload</title>");  
      out.println("</head>");
      out.println("<body>");
	
      while ( i.hasNext () ) 
      {
         FileItem fi = (FileItem)i.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();

=======
Suggestion 6

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FileUploadServlet extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private static final int MAX_SIZE = 1024 * 1024 * 40; // 40MB

    private String filePath;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        filePath = config.getInitParameter("file-upload");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();

        try {
            MultipartRequest multi = new MultipartRequest(request, filePath, MAX_SIZE);
            out.println("<html>");
            out.println("<head><title>File Upload</title></head>");
            out.println("<body>");
            out.println("<p>File uploaded successfully!</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html>");
            out.println("<head><title>File Upload Failed</title></head>");
            out.println("<body>");
            out.println("<p>File upload failed!</p>");
            out.println("</body></html>");
        }

        out.close();
    }
}

// The file-upload parameter in the init method is the local directory where the uploaded file will be saved.
// The MultipartRequest object is used to extract the file name and file contents from the request.
// The first parameter is the Http request object, the second parameter is the local directory where the uploaded file will be saved and the third parameter is the maximum size of the file to be uploaded.
// The MultipartRequest object will throw an exception if the file size exceeds the maximum size.
// The following is the web.xml configuration file for the Java servlet:
<servlet>
    <display-name>FileUploadServlet</display-name>
    <servlet-name>FileUploadServlet</servlet-name>
    <servlet-class>FileUploadServlet</servlet-class>
    <init-param>
        <param-name>file-upload</param-name>
        <param-value>C:\upload</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>FileUploadServlet</servlet-name>
    <url-pattern>/FileUploadServlet</url-pattern>
</servlet-mapping>

// The following is the Java servlet code for downloading
'''