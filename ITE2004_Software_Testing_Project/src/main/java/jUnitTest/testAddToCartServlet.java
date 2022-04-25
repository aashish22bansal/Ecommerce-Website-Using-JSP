package jUnitTest;

//import junit.framework.*;


import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//import model.*;


public class testAddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testAddToCartServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            ArrayList<model.Cart> cartList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            
            model.Cart cm = new model.Cart();
            
            cm.setId(id);
            cm.setQuantity(1);
            
            HttpSession session = request.getSession();
            
            ArrayList<model.Cart> cart_list = (ArrayList<model.Cart>) session.getAttribute("cart-list");
            boolean exist = false;
            boolean added = false;
            if(cart_list == null){
                added = cartList.add(cm); // Add product to cartList
                session.setAttribute("cart-list", cartList); // Creating session
                response.sendRedirect("index.jsp");
            }
            else{
                cartList = cart_list;

                exist = false;
                for (model.Cart c : cart_list){
                    if (c.getId() == id){
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
                    }
                }

                if (!exist){
                    cartList.add(cm);
                    response.sendRedirect("index.jsp");
                }
            }
            junit.framework.TestCase.assertEquals(true, added);
            junit.framework.TestCase.assertEquals(true, exist);
        }
	}
}
