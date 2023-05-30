def makeNewUserDir(username):
    if invalidUsername(username):
        print('Usernames cannot contain invalid characters')
    return False

    #  temporarily raise the program's privileges to allow creation of a new user folder
    try:
        raisePrivileges()
        os.mkdir('/home/' + username)
        lowerPrivileges()

    Synthesizing 10/10 solutions

=======
Suggestion 1

        except:
        print('Error creating directory /home/' + username)

=======
Suggestion 2

        except OSError:
        print('Creation of user directory failed')
        return False

=======
Suggestion 3

        except OSError:
        print('Creation of user directory failed')
        lowerPrivileges()
        return False

=======
Suggestion 4

try:
        raisePrivileges()
        os.mkdir('/home/' + username)
        lowerPrivileges()

    #  if the user's home folder already exists, do nothing
    except FileExistsError:
        pass

=======
Suggestion 5

        except Exception as e:
        print('Error creating directory: ' + str(e))
        lowerPrivileges()
        return False

=======
Suggestion 6

        except Exception as e:
        print('Could not create user folder: ' + str(e))
        lowerPrivileges()
        return False

=======
Suggestion 7

try:
        raisePrivileges()
        os.mkdir('/home/' + username)
        lowerPrivileges()

    #  if the user folder already exists, restore privileges and inform the user
    except FileExistsError:
        print('Could not create folder for ' + username + ': folder already exists')
        lowerPrivileges()

=======
Suggestion 8

try:
        raisePrivileges()
        os.mkdir('/home/' + username)
        lowerPrivileges()

    #  handle the error if the user's folder cannot be created
    except Exception as e:
        print('Error: ' + str(e))
        print('Reverting privileges back to normal.')
        lowerPrivileges()
        return False

=======
Suggestion 9

        except PermissionError:
        print('Permission denied')

=======
Suggestion 10

        except Exception as e:
        logError(e)
        lowerPrivileges()
        return False
    else:
        return True
