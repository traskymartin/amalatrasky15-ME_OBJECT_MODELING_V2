package com.crio.codingame.commands;

import java.util.List;
import javax.swing.SortOrder;
import com.crio.codingame.entities.ScoreOrder;
import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class LeaderBoardCommand implements ICommand{

    private final IUserService userService;
    
    public LeaderBoardCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllUserScoreOrderWise method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LEADERBOARD","ASC"]
    // or
    // ["LEADERBOARD","DESC"]

    @Override
    public void execute(List<String> tokens) {
        if(tokens.size()>=2){
        String board=tokens.get(0);
        ScoreOrder scoreOrder=ScoreOrder.valueOf(tokens.get(1).toUpperCase());
        if(board.equals("LEADERBOARD")){
            ScoreOrder score=scoreOrder;
            List<User> userlist=userService.getAllUserScoreOrderWise(score);
          /*   for(User user:userlist){
                System.out.println(user);
            }*/
            System.out.println(userlist);
        }
    }
    }
   
}
