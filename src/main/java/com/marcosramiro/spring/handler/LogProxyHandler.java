package com.marcosramiro.spring.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogProxyHandler implements InvocationHandler  {
	
	private static Logger LOGGER = LoggerFactory.getLogger(LogProxyHandler.class);
	
	private final Object target;
	
	public LogProxyHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		LOGGER.info("{}.{} - args: {}", target.getClass().getSimpleName(), method.getName(), obterArgumentoFormatado(args)   );
		
		long start = System.currentTimeMillis();
		
		Object result = method.invoke(target, args);
		long elapsed = System.currentTimeMillis() - start;
		
		LOGGER.info("{}.{} - tempo: {} ms", target.getClass().getSimpleName(), method.getName(), elapsed);

		return result;
	}
	
	private String obterArgumentoFormatado(Object[] args) {
		String argumentos = (String) Arrays.stream(args).reduce("", ( a , b) -> "".equals(a) ? String.format("%s", b) : String.format("%s,%s", a, b));
		String argumentosFormatado = String.format("[%s]", argumentos);
		return argumentosFormatado ;
	}

}
