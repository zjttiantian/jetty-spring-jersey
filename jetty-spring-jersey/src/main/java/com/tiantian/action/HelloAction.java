package com.tiantian.action;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiantian.bean.UserBean;
import com.tiantian.service.UserService;

@Path("hello")
public class HelloAction {
   
    @Autowired
    private UserService userService;
   
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserBean getGreeting(@PathParam("id") Integer id) throws Exception {
        userService.addTest(id);
        return userService.queryById(id);
    }
    
 
}