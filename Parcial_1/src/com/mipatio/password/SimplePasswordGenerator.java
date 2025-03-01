package com.mipatio.password;
import java.security.SecureRandom;




public class SimplePasswordGenerator {


	
	    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private static final String DIGITS = "0123456789";
	    private static final String SPECIAL_CHARS = "!@#$%^&*()-_+=<>?";

	    // Generar una contraseña
	    public String generatePassword(int minLength, int minDigits, int minUpperCase, int minLowerCase, int minSpecialChars) {
	        StringBuilder password = new StringBuilder();

	        // Generar caracteres mínimos requeridos para cada tipo
	        password.append(generateRandomChars(LOWERCASE, minLowerCase));
	        password.append(generateRandomChars(UPPERCASE, minUpperCase));
	        password.append(generateRandomChars(DIGITS, minDigits));
	        password.append(generateRandomChars(SPECIAL_CHARS, minSpecialChars));

	        // Rellenar con caracteres aleatorios hasta alcanzar la longitud mínima
	        String allChars = LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARS;
	        password.append(generateRandomChars(allChars, minLength - password.length()));

	        // Mezclar la contraseña y devolverla
	        return shuffleString(password.toString());
	    }

	    // Método para generar caracteres aleatorios
	    private String generateRandomChars(String chars, int length) {
	        StringBuilder result = new StringBuilder();
	        SecureRandom random = new SecureRandom();
	        for (int i = 0; i < length; i++) {
	            result.append(chars.charAt(random.nextInt(chars.length())));
	        }
	        return result.toString();
	    }

	    // Mezclar la contraseña para que no tenga un patrón predecible
	    private String shuffleString(String input) {
	        char[] array = input.toCharArray();
	        SecureRandom random = new SecureRandom();
	        for (int i = array.length - 1; i > 0; i--) {
	            int index = random.nextInt(i + 1);
	            char temp = array[i];
	            array[i] = array[index];
	            array[index] = temp;
	        }
	        return new String(array);
	        
	    }
	    public static void main(String[] args) {
	        SimplePasswordGenerator generator = new SimplePasswordGenerator();
	        // Generar una contraseña con los parámetros deseados
	        String password = generator.generatePassword(12, 2, 2, 2, 2);
	        System.out.println("Generated Password: " + password);
	    }
	}
