/**
 * 
 */
package servlet;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import model.Cart;

/**
 * @author aashish
 *
 */
public class AddToCartServletTest {

	/**
	 * Test method for {@link servlet.AddToCartServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
	 */
	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		//fail("Not yet implemented");
		response.setContentType("text/html;charset=UTF-8");
		AddToServletTestProcess addToServletTestProcess = new AddToServletTestProcess();

        try (PrintWriter out = response.getWriter()) {
        	
        	addToServletTestProcess.Processing(request, response);

            ArrayList<Cart> cartList = new ArrayList<>();
            int id = Integer.parseInt(request.getParameter("id"));
            
            Cart cm = new Cart();
            
            cm.setId(id);
            cm.setQuantity(1);
            
            HttpSession session = request.getSession();
            
            ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
            boolean exist = false;
            boolean added = false;
            if(cart_list == null){
                cartList.add(cm); // Add product to cartList
                session.setAttribute("cart-list", cartList); // Creating session
                response.sendRedirect("index.jsp");
            }
            else{
                cartList = cart_list;

                exist = false;
                for (Cart c : cart_list){
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
            TestCase.assertEquals(true, added);
            TestCase.assertEquals(true, exist);
        }
	}

}
