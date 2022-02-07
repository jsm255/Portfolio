package controllers;

import controllers.action.Action;
import controllers.action.DeleteBlogAction;
import controllers.action.ViewCountryAction;
import controllers.action.WriteBlogAction;
import controllers.action.DeleteReReviewAction;
import controllers.action.DeleteReviewAction;
import controllers.action.DeleteUserAction;
import controllers.action.DoModifyBlogAction;
import controllers.action.JoinAction;
import controllers.action.LikeAction;
import controllers.action.LoginAction;
import controllers.action.LogoutAction;
import controllers.action.ModifyBlogAction;
import controllers.action.ModifyReReviewAction;
import controllers.action.ModifyReviewAction;
import controllers.action.ModifyReviewSubmitAction;
import controllers.action.ModifyUserAction;
import controllers.action.MyBoardListAction;
import controllers.action.WriteReReviewAction;
import controllers.action.WriteReviewAction;


public class ActionFactory {
	
	private static ActionFactory instance = new ActionFactory();
	private ActionFactory () {}
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public static Action createAction(String command) {
		
		if(command.equals("join")) 
			return new JoinAction();
		
		else if(command.equals("writeReview"))
			return new WriteReviewAction();
		
		else if(command.equals("login"))
			return new LoginAction();
		
		else if(command.equals("modifyReview"))
			return new ModifyReviewAction();
		
		else if(command.equals("modifyReviewSubmit"))
			return new ModifyReviewSubmitAction();
		
		else if(command.equals("mypage"))
			return new ModifyUserAction();
		
		else if(command.equals("deleteUser"))
			return new DeleteUserAction();
		
		else if(command.equals("deleteReview"))
			return new DeleteReviewAction();
		
		else if(command.equals("deleteReReview"))
			return new DeleteReReviewAction();
		
		else if(command.equals("logout"))
			return new LogoutAction();
		
		else if(command.equals("writeReReview"))
			return new WriteReReviewAction();
		
		else if(command.equals("modifyReReview"))
			return new ModifyReReviewAction();
		else if(command.equals("viewCountry"))
			return new ViewCountryAction();
		else if(command.equals("myBoardList"))
			return new MyBoardListAction();
		
		else if(command.equals("writeBlog"))
			return new WriteBlogAction();
		else if (command.equals("modifyblog")) 
			return new ModifyBlogAction();
		else if (command.equals("DoModifyBlog"))
			return new DoModifyBlogAction();
		else if (command.equals("deleteBlog"))
			return new DeleteBlogAction();
		else if(command.equals("like") || command.equals("hate"))
			return new LikeAction();
		return null;
	}
	
}
