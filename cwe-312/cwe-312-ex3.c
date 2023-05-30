#include <stdio.h>
#include <sys/socket.h>

int main() {
    // establish connection, read in a password, and store it in a buffer
    server.sin_family = AF_INET; hp = gethostbyname(argv[1]);
    if (hp==NULL) error("Unknown host");
    memcpy( (char *)&server.sin_addr,(char *)hp->h_addr,hp->h_length);
    if (argc < 3) port = 80;
    else port = (unsigned short)atoi(argv[3]);
    server.sin_port = htons(port);
    if (connect(sock, (struct sockaddr *)&server, sizeof server) < 0) error("Connecting");
    Synthesizing 10/10 solutions (Duplicates hidden)

=======
Suggestion 1

        printf("Connected socket\n");
    printf("Enter password: ");
    fgets(buffer, sizeof buffer, stdin);
    // send the password to the server
    if (write(sock, buffer, strlen(buffer)) < 0) error("Writing");
    // read the server's response and print it out
    memset(buffer, 0, sizeof buffer);
    if (read(sock, buffer, sizeof buffer) < 0) error("Reading");
    printf("Server response: %s\n", buffer);
    return 0;

=======
Suggestion 2

        printf("Please enter the message: ");
    bzero(buffer, 256);
    fgets(buffer, 255, stdin);
    n = write(sock, buffer, strlen(buffer));
    if (n < 0) error("Writing to socket");
    bzero(buffer, 256);
    n = read(sock, buffer, 255);
    if (n < 0) error("Reading from socket");
    printf("%s\n", buffer);
    return 0;

=======
Suggestion 3

        printf("Please enter the message: ");
    bzero(buffer,256); fgets(buffer,255,stdin);
    // send the password to the server
    n = write(sock,buffer,strlen(buffer));
    if (n < 0) error("Writing to socket");
    // read the server's response, and print it out
    bzero(buffer,256); n = read(sock,buffer,255);
    if (n < 0) error("Reading from socket");
    printf("%s\n",buffer);
    return 0;

=======
Suggestion 4

    
    // send the password to the server
    write(sock, buf, strlen(buf));

    // read the response from the server
    while (read(sock, buf, 1) > 0) printf("%s", buf);
    close(sock);

=======
Suggestion 5

        printf("Connected socket: %d\n", sock);
    printf("Enter password: "); scanf("%s", buffer);
    printf("Sending password: %s\n", buffer);
    write(sock, buffer, strlen(buffer));
    // read in the response, and print it out
    memset(buffer, 0, sizeof buffer);
    read(sock, buffer, sizeof buffer - 1);
    printf("Received: %s\n", buffer);
    close(sock);
    return 0;

=======
Suggestion 6

        printf("Connected socket = %d\n", sock);
    printf("Enter password: ");
    fgets(buffer, sizeof buffer, stdin);
    buffer[strlen(buffer)-1] = 0;
    printf("Sending %s\n", buffer);
    if (send(sock, buffer, strlen(buffer), 0) < 0) error("Sending");
    // receive the response from the server
    if (recv(sock, buffer, sizeof buffer, 0) < 0) error("Receiving");
    printf("Received %s\n", buffer);
    close(sock);
    return 0;

=======
Suggestion 7

        printf("Connected socket=%d to %s:%d\n",sock,inet_ntoa(server.sin_addr),ntohs(server.sin_port));
    strcpy(buffer,"GET ");
    strcat(buffer,argv[2]);
    strcat(buffer," HTTP/1.0\n\n");
    printf("Request: %s\n",buffer);
    n = write(sock,buffer,strlen(buffer));
    if (n < 0) error("Writing");
    printf("Wrote %d bytes\n",n);
    // read the server's response and print it out
    while ((n = read(sock,buffer,BUFSIZE-1)) > 0) {
        buffer[n] = '\0';
        printf("Read %d bytes\n%s\n",n,buffer);
    }
    if (n < 0) error("Reading");
    close(sock);
    return 0;

=======
Suggestion 8

        write(sock,buffer,strlen(buffer));
    bzero(buffer,256);
    read(sock,buffer,255);
    printf("%s\n",buffer);
    return 0;

=======
Suggestion 9

    
    printf("Password: ");
    fgets(buffer, sizeof(buffer), stdin);
    buffer[strlen(buffer)-1] = 0;

    // send the password to the server
    write(sock, buffer, strlen(buffer));

    // read the server's response and print it out
    read(sock, buffer, sizeof(buffer));
    printf("%s\n", buffer);
    close(sock);

}