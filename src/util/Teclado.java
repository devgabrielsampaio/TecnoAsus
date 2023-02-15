/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author GabrielFSampaio
 */
public class Teclado {
    private static BufferedReader teclado = 
			new BufferedReader(new InputStreamReader(System.in));
	
	public static String lerTexto(String texto) {
		try {
			System.out.println(texto);
			return teclado.readLine();
			
		}catch(IOException ioe) {
			return null;
		}
	}
	
	public static int lerInt(String texto) {
		return Integer.parseInt(lerTexto(texto));
	}
	
	public static double lerDouble(String texto) {
		return Double.parseDouble(lerTexto(texto));
	}	
}
