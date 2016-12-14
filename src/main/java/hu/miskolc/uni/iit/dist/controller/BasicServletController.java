package hu.miskolc.uni.iit.dist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.miskolc.uni.iit.dist.dao.UserDao;
import hu.miskolc.uni.iit.dist.dao.UserDaoImpl;
import hu.miskolc.uni.iit.dist.domain.Gender;
import hu.miskolc.uni.iit.dist.domain.Qualification;
import hu.miskolc.uni.iit.dist.domain.User;

/**
 * Servlet implementation class BasicServletController
 */
@WebServlet({"", BasicServletController.USER_ORIGINATION, BasicServletController.SEARCH})
public class BasicServletController extends HttpServlet 
{
	
	private static final long serialVersionUID = -6478661208300839785L;
	protected static final String SEARCH = "/search";
	protected static final String USER_ORIGINATION = "/userorigination";
	
	
	private static final List<String> AVAILABLECOLORS = new ArrayList<>(3);
	private static final Map<String, String> AVAILABLESCHOOLS = new HashMap<>(3);
	private static final List<String> AVAILABLEGENDERS = new ArrayList<>(2);
	
	static
	{
		AVAILABLESCHOOLS.put("HIGHSCHOOL", "High School");
		AVAILABLESCHOOLS.put("COLLEGE", "College");
		AVAILABLESCHOOLS.put("UNIVERSITY", "University");
		
		AVAILABLECOLORS.add("red");
		AVAILABLECOLORS.add("green");
		AVAILABLECOLORS.add("blue");
		
		AVAILABLEGENDERS.add("MALE");
		AVAILABLEGENDERS.add("FEMALE");
	}
	
	private UserDao userDao;
	
	
    public BasicServletController() 
    {
    }
    
    @Override
    public void init() throws ServletException
    {
    	super.init();
    	final ServletContext context = getServletContext();
    	context.setAttribute("qualification", AVAILABLESCHOOLS.values());
    	context.setAttribute("colors", AVAILABLECOLORS);
    	context.setAttribute("genders", AVAILABLEGENDERS);
    	/** rather ugly solution, just dummy*/
    	userDao = new UserDaoImpl(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String pathString = request.getServletPath();
		if(SEARCH.equals(pathString))
		{
			String userId = request.getQueryString();
			if(!isEmpty(userId))
			{
				userDao.deleteUser(userId);
				request.setAttribute("users", userDao.getUsers());
				forward(request, response, SEARCH);
				return;
			}
		}
		
		forward(request, response, USER_ORIGINATION);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String pathString = request.getServletPath();
		if(USER_ORIGINATION.equals(pathString))
		{
			String userName = request.getParameter("userName");
			String creditBalance = request.getParameter("creditBalance");
			String qualification = request.getParameter("qualification");
			List<String> colors = Arrays.asList(request.getParameterValues("colors"));
			String gender = request.getParameter("gender");
			
			if( isEmpty(userName) || isEmpty(qualification) || !AVAILABLESCHOOLS.containsValue(qualification) ||
					isEmpty(gender) || !AVAILABLEGENDERS.contains(gender) || !AVAILABLECOLORS.containsAll(colors) ||
					!isNumericValue(creditBalance)
					)
			{
				//TODO:handle errors
				throw new IllegalArgumentException("Invalid data!");
			}
			
			
	        User user = new User();
	        user.setUserName(userName);
	        user.setCreditBalance(creditBalance);
	        user.getFavouriteColor().addAll(colors);
	        
			if ("male".equalsIgnoreCase(gender))
			{
				user.setGender(Gender.MALE);
			} 
			else if ("female".equalsIgnoreCase(gender))
			{
				user.setGender(Gender.FEMALE);
			}
			
			if ("highschool".equalsIgnoreCase(qualification))
			{
				user.setQualification(Qualification.HIGHSCHOOL);
			}
			else if ("college".equalsIgnoreCase(qualification))
			{
				user.setQualification(Qualification.COLLEGE);
			}
			else if ("university".equalsIgnoreCase(qualification))
			{
				user.setQualification(Qualification.UNIVERSITY);
			}
	        
			userDao.storeUser(user);
			request.setAttribute("users", userDao.getUsers());
			forward(request, response, SEARCH);
		}
		
	}
	
	
	private static void forward(HttpServletRequest request, HttpServletResponse response, String url)
	{
        try 
        {
            request.getRequestDispatcher(String.format("/WEB-INF/jsp%s.jsp", url)).forward(request, response);
        } 
        catch (Exception ex) 
        {
            throw new IllegalStateException("Unable to forward(" + url + ").", ex);
        }
	}
	
	private static boolean isEmpty(String str) 
	{
  		return (str == null || "".equals(str));
  	}
	
	private static boolean isNumericValue(String number)
	{
        try 
        {
        	Integer.parseInt(number);
        } 
        catch (NumberFormatException ex) 
        {
            return false;
        }
		return true;
	}

}
