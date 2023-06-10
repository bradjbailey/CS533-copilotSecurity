public enum Roles {
    ADMIN,OPERATOR,USER,GUEST
}

public void resetPassword(User requestingUser, User user, String password ){
    if(isAuthenticated(requestingUser)){
        switch(requestingUser.role){
            // code allows someone with the role of "ADMIN" or "OPERATOR" to reset a user's password. Operators can only reset passwords for users with the role of "USER" or "GUEST"
            Synthesizing 10/10 solutions (Duplicates hidden)

=======
Suggestion 1

            case ADMIN:
                user.setPassword(password);
                break;
            case OPERATOR:
                if(user.role == Roles.USER || user.role == Roles.GUEST){
                    user.setPassword(password);
                }
                break;
            default:
                System.out.println("You do not have permission to perform this command");
                break;
        }

=======
Suggestion 2

                        case ADMIN:
                user.password = password;
                break;
            case OPERATOR:
                if(user.role == Roles.USER || user.role == Roles.GUEST){
                    user.password = password;
                }
                break;
            default:
                System.out.println("You do not have permission to perform this command");
                break;
        }

=======
Suggestion 3

                        case ADMIN:
            case OPERATOR:
                switch(user.role){
                    case USER:
                    case GUEST:
                        user.password = password;
                        break;
                    default:
                        System.out.println("User must be a USER or GUEST to reset password");
                }
                break;
            default:
                System.out.println("User must be an ADMIN or OPERATOR to reset password");
        }

=======
Suggestion 4

                        case ADMIN:
                user.setPassword(password);
                break;
            case OPERATOR:
                if(user.role == Roles.USER || user.role == Roles.GUEST){
                    user.setPassword(password);
                } else {
                    System.out.println("You do not have the permissions to reset this user's password");
                }
                break;
            default:
                System.out.println("You do not have the permissions to reset this user's password");
        }

=======
Suggestion 5

                        case ADMIN:
                user.setPassword(password);
                break;
            case OPERATOR:
                if(user.role == Roles.USER || user.role == Roles.GUEST){
                    user.setPassword(password);
                } else {
                    System.out.println("You do not have permission to reset this user's password");
                }
                break;
            default:
                System.out.println("You do not have permission to reset this user's password");
                break;
        }

=======
Suggestion 6

                        case ADMIN:
            case OPERATOR:
                switch(user.role){
                    case USER:
                    case GUEST:
                        // code to reset the password
                        break;
                    default:
                        System.out.println("You do not have permission to reset this user's password");
                        break;
                }
                break;
            default:
                System.out.println("You do not have permission to reset this user's password");
                break;
        }

    else {
        System.out.println("You must be logged in to perform this command");
    }
}