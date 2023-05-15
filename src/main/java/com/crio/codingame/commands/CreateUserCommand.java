package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        if(tokens.size()>=2){
            String comman=tokens.get(0);
            String userName=tokens.get(1);
            if(comman.equals("CREATE-USER")){
                User user=userService.create(userName);
                System.out.print(user);
            }
        }
    }
    
}
