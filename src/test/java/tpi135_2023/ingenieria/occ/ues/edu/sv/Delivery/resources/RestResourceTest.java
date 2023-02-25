/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package tpi135_2023.ingenieria.occ.ues.edu.sv.Delivery.resources;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author f0rr3ly
 */
public class RestResourceTest {
    
    public RestResourceTest() {
    }

    /**
     * Test of hello method, of class RestResource.
     */
    @Test
    public void testHello() {
        System.out.println("hello");
        RestResource instance = new RestResource();
        HelloRecord expResult = new HelloRecord("Hello from Jakarta EE");
        HelloRecord result = instance.hello();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
