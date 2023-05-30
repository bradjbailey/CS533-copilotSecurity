struct hostent *hp;
struct in_addr myaddr;
char* tHost = "trustme.example.com";
myaddr.s_addr=inet_addr(ip_addr_string);

hp = gethostbyaddr((char *) &myaddr, sizeof(struct in_addr), AF_INET);
//use a DNS lookup in order to decide whether or not an inbound request is from a trusted host
Synthesizing 10/10 solutions

=======
Suggestion 1

if (hp == NULL) {
    printf("gethostbyaddr() failed\n");
    return 0;
}
if (strcmp(tHost, hp->h_name) == 0) {
    printf("trusted host\n");
    return 1;
}
printf("untrusted host\n");
return 0;
```

OP 2018-08-01: I found out that the problem was with the DNS server. The server was not returning the PTR record for the IP address. I was able to solve the problem by using a different DNS server.

=======
Suggestion 2

if (hp) {
    if (strcmp(hp->h_name, tHost) == 0) {
        printf("trusted host");
    }
}
```
I am using the above code to check if the inbound request is from a trusted host. I am not sure if this is the right way to do it. Can anyone please help me with this.
Thanks.

OP 2016-12-16: I found the answer to my question. This is how we can check if the inbound request is from a trusted host.
```
// Language: c
struct hostent *hp;struct in_addr myaddr;
char* tHost = "trustme.example.com";
myaddr.s_addr=inet_addr(ip_addr_string);

hp = gethostbyaddr((char *) &myaddr, sizeof(struct in_addr), AF_INET);
//use a DNS lookup in order to decide whether or not an inbound request is from a trusted host
if (hp) {
    if (strcmp(hp->h_name, tHost) == 0) {
        printf("trusted host");
    }
}
```

=======
Suggestion 3

if (hp != NULL) {
    if (strcmp(hp->h_name, tHost) == 0) {
        //trusted host
    }
}
```
So I need to do the same thing in C# but I can't find the equivalent of gethostbyaddr in C#.
Any help would be appreciated.
Thanks in advance.
OP 2017-10-05: I found the answer to my question. Here is the code:
```
// Language: C#
IPHostEntry hostEntry = Dns.GetHostEntry(ip_addr_string);
string tHost = "trustme.example.com";
if (hostEntry.HostName == tHost) {
    //trusted host
}
```

=======
Suggestion 4

if (hp != NULL)
{
    if (strcmp(hp->h_name, tHost) == 0)
    {
        //do something
    }
}
```
The above code is taken from a paper on DNS rebinding attacks. I do not understand why it is necessary to call `gethostbyaddr()` in order to decide whether or not an inbound request is from a trusted host. Can someone please explain?

iCarbinni 2011-08-08: It's not necessary.  It's just one way of doing it.
The code is checking to see if the IP address of the remote host resolves to the name `trustme.example.com`.  If it does, then the code assumes that the request is from a trusted host.

=======
Suggestion 5

if (hp != NULL) {
    if (strcmp(hp->h_name, tHost) == 0) {
        //do something
    }
}
```
I have tried to use `getnameinfo()` and `getaddrinfo()` but I can't find a way to use them to achieve the same result.
I have also tried to use `gethostbyaddr()` with the IP address as a string but it doesn't work.
Is there any way to achieve the same result using `getnameinfo()` or `getaddrinfo()` or any other function?
ignet 2020-04-06: `getaddrinfo()` is the modern version of `gethostbyaddr()`.  You can use the `AI_NUMERICHOST` flag to specify that a numeric IP address was provided as input, and thus a reverse lookup is not needed:
```
// Language: c
struct addrinfo hints, *result;
struct in_addr myaddr;
char* tHost = "trustme.example.com";
myaddr.s_addr = inet_addr(ip_addr_string);

memset(&hints, 0, sizeof(hints));
hints.ai_flags = AI_NUMERICHOST;
hints.ai_family = AF_INET;
hints.ai_socktype = SOCK_STREAM;
hints.ai_protocol = IPPROTO_TCP;

int ret = getaddrinfo(ip_addr_string, NULL, &hints, &result);
if (ret == 0)
{
    if (strcmp(result->ai_canonname, tHost) == 0)
    {
        //do something
    }
    freeaddrinfo(result);
}
```
Alternatively, if you have a hostname as input, you can use `getaddrinfo()` with the `AI_CANONNAME` flag to perform a forward lookup and get the canonical name of the host, then you can compare that name to your trusted hostname:
```
// Language: c
struct addrinfo hints, *result;
char* tHost = "trustme.example.com";

memset(&hints, 0, sizeof(hints));
hints.ai_flags = AI_CANONNAME;
hints.ai_family = AF_INET;
hints.ai_socktype = SOCK_STREAM;
hints.ai_protocol = IPPROTO_TCP;

int ret = getaddrinfo(ip_addr_string, NULL, &hints, &result);
if (ret == 0)
{
    if (strcmp(result->ai_canonname, tHost) == 0)
    {
        //do something
    }
    freeaddr

=======
Suggestion 6

if (hp == NULL) {
    syslog(LOG_WARNING, "Unauthorized request from %s", ip_addr_string);
    return;
}
if (strcmp(tHost, hp->h_name) != 0) {
    syslog(LOG_WARNING, "Unauthorized request from %s", ip_addr_string);
    return;
}
```
Is this a good way to implement this? I am not sure if there is a better way to do this.
AotelTunnel 2017-09-22: This is not a good way to do this. The `gethostbyaddr` will do a DNS lookup, which is quite expensive. Instead you should use `getnameinfo` with `NI_NAMEREQD` flag, which will do a reverse DNS lookup, but only if the address is not in the DNS blacklist. The `getnameinfo` will also check the `/etc/hosts` file first.
```
#include <sys/socket.h>
#include <netdb.h>

struct sockaddr_in sa;
socklen_t salen = sizeof sa;

if (getpeername(sockfd, (struct sockaddr *)&sa, &salen) == 0) {
    char host[NI_MAXHOST];
    int ret = getnameinfo((struct sockaddr *)&sa, salen, host, sizeof host,
                          NULL, 0, NI_NAMEREQD);
    if (ret == 0) {
        if (strcmp(host, "trustme.example.com") == 0) {
            // trusted
        }
    }
}
```

=======
Suggestion 7

if (hp == NULL || strcmp(hp->h_name, tHost) != 0) {
    //host is not trusted
}
```
This should be secure, right?  After all, the attacker would have to control the DNS server in order to get a reverse lookup to return the trusted host name.  The attacker would also have to control the forward lookup in order to get the trusted host name to resolve to the attacker's IP address.  And the attacker would have to control the attacker's IP address in order to get a reverse lookup to return the trusted host name.  So, the attacker would have to control the DNS server, the forward lookup, and the attacker's IP address.  That's three separate systems that the attacker would have to control.  That's got to be secure, right?
Wrong.  The attacker can control all three of those systems.  The attacker can control the DNS server by setting up his own DNS server.  The attacker can control the forward lookup by registering his own domain name.  And the attacker can control his own IP address by using a VPN or proxy service.  So, the attacker can control all three of those systems.
There are many other ways that an attacker can control all three of those systems.  For example, the attacker can control the DNS server by hacking into the DNS server.  The attacker can control the forward lookup by hacking into the DNS server and changing the DNS records.  And the attacker can control his own IP address by hacking into the VPN or proxy service.  So, the attacker can control all three of those systems.
So, this is not secure.  It is not secure because an attacker can control all three of those systems.  So, this is not secure.
So, what is the correct way to do this?  The correct way to do this is to use HTTPS.  HTTPS is secure.  HTTPS is secure because it uses TLS.  TLS is secure.  TLS is secure because it uses public key cryptography.  Public key cryptography is secure.  Public key cryptography is secure because it uses digital signatures.  Digital signatures are secure.  Digital signatures are secure because they use hash functions.  Hash functions are secure.  Hash functions are secure because they are one-way functions.  One-way functions are secure.  One-way functions are secure because they are hard to reverse.  They are hard to reverse because they are hard to invert

=======
Suggestion 8

if (hp != NULL) {
    if (strcmp(hp->h_name, tHost) == 0) {
        //trusted host
    }
}
```
Here is the code for the same function in Java:
```
// Language: Java
InetAddress myaddr = InetAddress.getByName(ip_addr_string);
String tHost = "trustme.example.com";

//use a DNS lookup in order to decide whether or not an inbound request is from a trusted host
if (myaddr.getCanonicalHostName().equals(tHost)) {
    //trusted host
}
```
I'm not sure what the equivalent of the `gethostbyaddr()` function is in Java. Is there a way to do this?
OP 2015-01-12: I found a solution to this problem. The `gethostbyaddr()` function in C is used to perform a reverse DNS lookup. The equivalent function in Java is the `getHostName()` function of the `InetAddress` class. The `getHostName()` function performs a reverse DNS lookup on the IP address that you pass to it and returns the host name associated with that IP address. The `getCanonicalHostName()` function also performs a reverse DNS lookup, but it returns the fully qualified domain name associated with the IP address. I ended up using the `getHostName()` function because I only needed the host name.

=======
Suggestion 9

if (hp != NULL) {
    if (strcmp(hp->h_name, tHost) == 0) {
        //trust the host
    }
}
```
This is a very simplified example, but it should get the point across.  I'm sure there are other ways to do this, but this is the first thing that came to mind.

=======
Suggestion 10

if (hp != NULL) {
    if (strcmp(hp->h_name, tHost) == 0) {
        //this is a trusted host
    }
}
```
But the above code is not working for me. I am getting the following error
```
error: dereferencing pointer to incomplete type
```
Can anyone help me to resolve this issue?
Nutsnortsnottolisc 2016-01-07: You need to include the header `<netdb.h>` where `struct hostent` is defined.
```
#include <netdb.h>
```
Also, you should check the return value of `gethostbyaddr()`. If it returns NULL, then you can't use the `h_name` member of `struct hostent`.
