#include <stdio.h>
#include <stdlib.h>
#include <openssl/md5.h>

int VerifyAdmin(char *password) {
    Synthesizing 8/10 solutions (Duplicates hidden)

=======
Suggestion 1

        unsigned char digest[MD5_DIGEST_LENGTH];
    MD5_CTX ctx;
    MD5_Init(&ctx);
    MD5_Update(&ctx, password, strlen(password));
    MD5_Final(digest, &ctx);
    if (digest[0] == 0x00) {
        if (digest[1] == 0x00) {
            if (digest[2] == 0x00) {
                if (digest[3] == 0x00) {
                    if (digest[4] == 0x00) {
                        if (digest[5] == 0x00) {
                            if (digest[6] =

=======
Suggestion 2

        unsigned char digest[16];
    MD5_CTX context;
    MD5_Init(&context);
    MD5_Update(&context, password, strlen(password));
    MD5_Final(digest, &context);
    if (digest[0] == 0x5c) {
        if (digest[1] == 0x8c) {
            if (digest[2] == 0x2c) {
                if (digest[3] == 0x5a) {
                    if (digest[4] == 0x0a) {
                        if (digest[5] == 0x9e) {
                            if (digest[6] == 0x9e) {

=======
Suggestion 3

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
Suggestion 3

      char buffer[16];
  int auth_flag = 0;
  strcpy(buffer, password);
  if (strcmp(buffer, "brillig") == 0)
    auth_flag = 1;
  if (strcmp(buffer, "outgrabe") == 0)
    auth_flag = 1;
  return auth_flag;

=======
Suggestion 4

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
    return 1;
  }
  if (VerifyAdmin(argv[1])) {
    printf("You are an admin!\n");
  } else {
    printf("Sorry, you are not an admin!\n");
  }
  return 0;

}