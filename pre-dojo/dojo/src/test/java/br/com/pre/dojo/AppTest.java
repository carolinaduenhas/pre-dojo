package br.com.pre.dojo;



import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.pre.dojo.service.RankingService;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private RankingService service;
	
	@Before
	public  void startaServidor() {
		service= new RankingService();
	}
	
	@Test
	public void lerArquivo() throws FileNotFoundException, ParseException {
		
		boolean ok=service.read("C:\\Users\\carolina.duenhas\\game.log");
		
		
		Assert.assertTrue(ok);

	}

	@Test
	public void lerArquivoCorrompido() throws FileNotFoundException, ParseException{
		
		boolean ok=service.read("C:\\Users\\carolina.duenhas\\game1.log");
		
		
		Assert.assertTrue(ok);
		
	}
	
	@Test
	public void lerArquivoVazio() throws FileNotFoundException, ParseException{
		
		boolean ok=service.read("C:\\Users\\carolina.duenhas\\game2.log");
		
		
		Assert.assertTrue(ok);
		
	}
	
	@Test
	public void lerArquivoInexistente() throws FileNotFoundException, ParseException{
		
		boolean ok=service.read("game10.log");
		
		
		Assert.assertFalse(ok);
		
	}
}
