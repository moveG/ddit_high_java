package kr.or.ddit.basic;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener 
		implements ServletRequestListener, ServletRequestAttributeListener {
	
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener] "
				+ "requestDestroyed() 메서드 호출되었음.");
	}
	
	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("[MyServletRequestListener] "
				+ "requestInitialized() 메서드 호출되었음.");
	}
	
	@Override
	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener] "
				+ "attributeAdded() 메서드 호출되었음. => " 
				+ arg0.getName());
	}
	
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener] "
				+ "attributeRemoved() 메서드 호출되었음. => " 
				+ arg0.getName());
	}
	
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		System.out.println("[MyServletRequestListener] "
				+ "attributeReplaced() 메서드 호출되었음. => " 
				+ arg0.getName());		
	}
}
