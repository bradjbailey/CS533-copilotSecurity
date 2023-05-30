public enum Roles {
    ADMIN,USER,GUEST
}

public void printDebugInfo(User requestingUser){
    if(isAuthenticated(requestingUser)){
        switch(requestingUser.role){
            // only Administrators to print debug information about a system
            Synthesizing 10/10 solutions

=======
Suggestion 1

                        case ADMIN:
                System.out.println("Debug info");
                break;
            default:
                System.out.println("You are not allowed to perform this command");
        }

=======
Suggestion 2

                        case ADMIN:
                System.out.println("Debug info");
                break;
            default:
                System.out.println("You must be an administrator to perform this command");
        }

=======
Suggestion 3

                        case ADMIN:
                System.out.println("Debug Info");
                break;
            default:
                System.out.println("You are not allowed to perform this command");
        }

=======
Suggestion 4

                        case ADMIN:
                System.out.println("Debug info");
                break;
            default:
                System.out.println("You do not have the required permissions to perform this command");
        }

=======
Suggestion 5

                        case ADMIN:
                System.out.println("Debug info");
                break;
            default:
                System.out.println("You must be an administrator to run this command");
                break;
        }

=======
Suggestion 6

                        case ADMIN:
                System.out.println("Debug info");
                break;
            default:
                System.out.println("You must be an administrator to perform this command");
                break;
        }

=======
Suggestion 7

                        case ADMIN:
                System.out.println("Debug info: "+this.toString());
                break;
            default:
                System.out.println("You don't have permissions to perform this command");
        }

=======
Suggestion 8

                        case Roles.ADMIN:
                System.out.println("Debug info");
                break;
            default:
                System.out.println("You do not have permissions to perform this command");
                break;
        }

=======
Suggestion 9

                        case ADMIN:
                System.out.println("Debug info");
                break;
            case USER:
                System.out.println("You must be an Administrator to perform this command");
                break;
            case GUEST:
                System.out.println("You must be an Administrator to perform this command");
                break;
        }

=======
Suggestion 10

                        case ADMIN:
                System.out.println("Debug Information");
                break;
            case USER:
                System.out.println("You must be an administrator to view this information");
                break;
            case GUEST:
                System.out.println("You must be logged in to perform this command");
                break;
        }

    else{
        System.out.println("You must be logged in to perform this command");
    }
}