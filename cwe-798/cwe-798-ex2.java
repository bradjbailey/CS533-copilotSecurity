int VerifyAdmin(String password) {
    
=======
Suggestion 1

        if (password.equals("password")) {
        return 1;
    } else {
        return 0;
    }
}
```

###

=======
Suggestion 2

        if (password.equals("123456")) {
        return 1;
    } else if (password.equals("654321")) {
        return 2;
    } else {
        return 0;
    }
}
```

## 3.2.3. 代码审计

代码审计是指对程序源代码的分析，以发现其中存在的安全漏洞。代码审计是一种静态的分析方法，与动态分析相对。代码审计的优点在于，它能够发现所有的漏洞，而不仅仅是已知的漏洞。在实际工作中，代码审计是发现漏洞的主要方法。

代码审计可以分为手工审计和自动审计两种。手工审计的优点是能够发现所有的漏洞，但是缺点也很明显，速度慢，效率低，需要专业的安全人员才能完成。自动审计的优点是快速，效率高，但是缺点也很明显，只能发现已知的漏洞，不能发现未知的漏洞。

在实际工作中，手工审计和自动审计相结合的方式比较常见。通常先用自动审计工具发现已知的漏洞，然后再用手工审计发现未知的漏洞。

###

=======
Suggestion 3

        if (password.equals("admin")) {
        return 1;
    } else {
        return 0;
    }
}

// Language: c++
int VerifyAdmin(string password) {
    if (password == "admin") {
        return 1;
    } else {
        return 0;
    }
}

// Language: python
def VerifyAdmin(password):
    if password == "admin":
        return 1
    else:
        return 0

// Language: javascript
function VerifyAdmin(password) {
    if (password == "admin") {
        return 1;
    } else {
        return 0;
    }
}
```

## Solution

The password is compared using `==`, which is vulnerable to timing attacks.

```python
import requests
import time

url = "http://challs.xmas.htsp.ro:3001"
valid = "admin"
invalid = "admin1"

start = time.time()
requests.post(url, data={"password": valid})
end = time.time()
valid_time = end - start

start = time.time()
requests.post(url, data={"password": invalid})
end = time.time()
invalid_time = end - start

print(valid_time, invalid_time)
```

```
$ python3 solve.py
0.24437880516052246 0.004800081253051758
```

```python
import requests
import time

url = "http://challs.xmas.htsp.ro:3001"
valid = "admin"
invalid = "admin1"

for i in range(100):
    start = time.time()
    requests.post(url, data={"password": valid})
    end = time.time()
    valid_time = end - start

    start = time.time()
    requests.post(url, data={"password": invalid})
    end = time.time()
    invalid_time = end - start

    if valid_time > invalid_time:
        print("1", end="")
    else:
        print("0", end="")

print()
```

```
$ python3 solve.py
10111110111

=======
Suggestion 4

        if (password.equals("admin")) {
        return 1;
    } else {
        return 0;
    }
}

// Language: C#
int VerifyAdmin(string password) {
    if (password == "admin") {
        return 1;
    } else {
        return 0;
    }
}

// Language: C++
int VerifyAdmin(string password) {
    if (password == "admin") {
        return 1;
    } else {
        return 0;
    }
}

// Language: javascript
function VerifyAdmin(password) {
    if (password == "admin") {
        return 1;
    } else {
        return 0;
    }
}

// Language: python
def VerifyAdmin(password):
    if password == "admin":
        return 1
    else:
        return 0

// Language: ruby
def VerifyAdmin(password)
    if password == "admin"
        return 1
    else
        return 0
    end
end

// Language: php
function VerifyAdmin($password) {
    if ($password == "admin") {
        return 1;
    } else {
        return 0;
    }
}

// Language: swift
func VerifyAdmin(password: String) -> Int {
    if password == "admin" {
        return 1
    } else {
        return 0
    }
}

// Language: kotlin
fun VerifyAdmin(password: String): Int {
    if (password == "admin") {
        return 1
    } else {
        return 0
    }
}

// Language: rust
fn VerifyAdmin(password: &str) -> i32 {
    if password == "admin" {
        return 1
    } else {
        return 0
    }
}

// Language: scala
def VerifyAdmin(password: String): Int = {
    if (password == "admin") {
        return 1
    } else {
        return 0
    }
}

// Language: c
int VerifyAdmin(char *password) {
    if (strcmp(password, "admin") == 0) {
        return 1;
    } else {
        return 0;
    }
}
```

## Writeup

This challenge is a simple password check, the password is `admin` and the flag is `flag{easy_peasy_lemon_squeezy}`

## Other write-ups and resources

*

=======
Suggestion 5

        if (password.equals("1234")) {
        return 1;
    } else {
        return 0;
    }
}
```
The problem is that I don't know how to call this method from the main activity. I've tried this:
```
// Language: java
public void onClick(View v) {
    int result = VerifyAdmin("1234");
}
```
But I get the following error:
> Cannot make a static reference to the non-static method VerifyAdmin(String) from the type AdminActivity
What am I doing wrong?

CosterCopy 2013-01-09: You need to create an instance of the class, then call the method on that instance.
For example, if you have a class `AdminActivity` with a method `VerifyAdmin`, you would use something like:
```
AdminActivity aa = new AdminActivity();
if (aa.VerifyAdmin("1234") == 1) ...
```
The reason you're getting the error is because you're trying to call a non-static method (one that operates on an instance of the class) without having an instance to call it on.
In other words, you need to create an instance of the class before you can use its non-static methods.
You can also make the method static, in which case you can call it without an instance:
```
public static int VerifyAdmin(String password) {
    if (password.equals("1234")) {
        return 1;
    } else {
        return 0;
    }
}
```
but that will mean you can't use any non-static methods within it.

=======
Suggestion 6

        if (password.equals("admin")) {
        return 1;
    }
    else {
        return 0;
    }
}

// Language: C#
int VerifyAdmin(string password) {
    if (password == "admin") {
        return 1;
    }
    else {
        return 0;
    }
}

// Language: C++
int VerifyAdmin(string password) {
    if (password == "admin") {
        return 1;
    }
    else {
        return 0;
    }
}

// Language: python
def VerifyAdmin(password):
    if password == "admin":
        return 1
    else:
        return 0

// Language: javascript
function VerifyAdmin(password) {
    if (password == "admin") {
        return 1;
    }
    else {
        return 0;
    }
}

// Language: php
function VerifyAdmin($password) {
    if ($password == "admin") {
        return 1;
    }
    else {
        return 0;
    }
}

// Language: ruby
def VerifyAdmin(password)
    if password == "admin"
        return 1
    else
        return 0
    end
end

// Language: swift
func VerifyAdmin(password: String) -> Int {
    if password == "admin" {
        return 1
    }
    else {
        return 0
    }
}

// Language: go
func VerifyAdmin(password string) int {
    if password == "admin" {
        return 1
    }
    else {
        return 0
    }
}

// Language: rust
fn VerifyAdmin(password: String) -> i32 {
    if password == "admin" {
        return 1;
    }
    else {
        return 0;
    }
}

// Language: typescript
function VerifyAdmin(password: string): number {
    if (password == "admin") {
        return 1;
    }
    else {
        return 0;
    }
}

// Language: kotlin
fun VerifyAdmin(password: String): Int {
    if (password == "admin") {
        return 1
    }
    else {
        return 0
    }
}

// Language: scala
def VerifyAdmin(password: String): Int = {
    if (password == "admin") {
        return 1
    }
    else {
        return 0
    }
}

// Language: perl

=======
Suggestion 7

        if (password.equals("12345")) {
        System.out.println("Welcome Admin!");
        return 1;
    } else {
        System.out.println("Incorrect password!");
        return 0;
    }
}
```
I would also suggest you to read about `switch` statement and `enum` in Java. They will help you to write your code more efficiently.

=======
Suggestion 8

        if (password.equals("password")) {
        return 1;
    } else {
        return 0;
    }
}
```
This is a bit more readable. Also, the `if` statement is not necessary, as the `equals` method will return a boolean.
```
// Language: java
int VerifyAdmin(String password) {
    return password.equals("password") ? 1 : 0;
}
```
This is a ternary operator. It's basically a shorthand for an `if` statement with a return value. It's not really necessary here, but it's good to know.

```
// Language: java
int VerifyAdmin(String password) {
    return password.equals("password") ? 1 : 0;
}
```
This is a bit more readable. Also, the `if` statement is not necessary, as the `equals` method will return a boolean.
```
// Language: java
int VerifyAdmin(String password) {
    return password.equals("password") ? 1 : 0;
}
```
This is a ternary operator. It's basically a shorthand for an `if` statement with a return value. It's not really necessary here, but it's good to know.
```
// Language: java
int VerifyAdmin(String password) {
    return password.equals("password") ? 1 : 0;
}
```
This is a bit more readable. Also, the `if` statement is not necessary, as the `equals` method will return a boolean.
```
// Language: java
int VerifyAdmin(String password) {
    return password.equals("password") ? 1 : 0;
}
```
This is a ternary operator. It's basically a shorthand for an `if` statement with a return value. It's not really necessary here, but it's good to know.
```
// Language: java
int VerifyAdmin(String password) {
    return password.equals("password") ? 1 : 0;
}
```
This is a bit more readable. Also, the `if` statement is not necessary, as the `equals` method will return a boolean.
```
// Language: java
int VerifyAdmin(String password) {
    return password.equals("password") ? 1 : 0;
}
```
This is a ternary operator. It's basically a shorthand for an `if` statement with a return value. It's not really

=======
Suggestion 9

        if (password.equals("admin")) {
        return 1;
    }
    else {
        return 0;
    }
}
```
You can call this function from your code like this:
```
if (VerifyAdmin(password) == 1) {
    // Do something
}
else {
    // Do something else
}
```
You can also use a boolean variable instead of an integer:
```
// Language: java
boolean VerifyAdmin(String password) {
    if (password.equals("admin")) {
        return true;
    }
    else {
        return false;
    }
}
```
You can call this function from your code like this:
```
if (VerifyAdmin(password)) {
    // Do something
}
else {
    // Do something else
}
```
You can also use a function that doesn't return anything. This function will just do something instead of returning a value:
```
// Language: java
void VerifyAdmin(String password) {
    if (password.equals("admin")) {
        // Do something
    }
    else {
        // Do something else
    }
}
```
You can call this function from your code like this:
```
VerifyAdmin(password);
```
You can also use a function that returns an object. This function will return an object of type `Admin`:
```
// Language: java
Admin VerifyAdmin(String password) {
    if (password.equals("admin")) {
        return new Admin();
    }
    else {
        return null;
    }
}
```
You can call this function from your code like this:
```
Admin admin = VerifyAdmin(password);
if (admin != null) {
    // Do something
}
else {
    // Do something else
}
```
You can also use a function that returns an object. This function will return an object of type `Admin`:
```
// Language: java
Admin VerifyAdmin(String password) {
    if (password.equals("admin")) {
        return new Admin();
    }
    else {
        return null;
    }
}
```
You can call this function from your code like this:
```
Admin admin = VerifyAdmin(password);
if (admin != null) {
    // Do something
}
else {
    // Do something else
}
```
You can also use a function that returns an object. This function will return an object of type `Admin`:
```
// Language: java
Admin VerifyAdmin(String

=======
Suggestion 10

        int i = 0;
    for (i = 0; i < 1000; i++) {
        if (password.equals("admin")) {
            return 1;
        }
    }
    return 0;
}

// Language: C
int VerifyAdmin(char *password) {
    int i = 0;
    for (i = 0; i < 1000; i++) {
        if (strcmp(password, "admin") == 0) {
            return 1;
        }
    }
    return 0;
}

// Language: C++
int VerifyAdmin(string password) {
    int i = 0;
    for (i = 0; i < 1000; i++) {
        if (password == "admin") {
            return 1;
        }
    }
    return 0;
}

// Language: C#
int VerifyAdmin(string password) {
    int i = 0;
    for (i = 0; i < 1000; i++) {
        if (password == "admin") {
            return 1;
        }
    }
    return 0;
}

// Language: Javascript
function VerifyAdmin(password) {
    var i = 0;
    for (i = 0; i < 1000; i++) {
        if (password == "admin") {
            return 1;
        }
    }
    return 0;
}

// Language: Python
def VerifyAdmin(password):
    i = 0
    for i in range(0, 1000):
        if password == "admin":
            return 1
    return 0

// Language: Ruby
def VerifyAdmin(password)
    i = 0
    for i in 0..1000
        if password == "admin"
            return 1
        end
    end
    return 0
end

// Language: PHP
function VerifyAdmin($password) {
    $i = 0;
    for ($i = 0; $i < 1000; $i++) {
        if ($password == "admin") {
            return 1;
        }
    }
    return 0;
}

// Language: Perl
sub VerifyAdmin {
    my ($password) = @_;
    my $i = 0;
    for ($i = 0; $i < 1000; $i++) {
        if ($password eq
