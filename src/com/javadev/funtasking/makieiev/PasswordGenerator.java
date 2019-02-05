package com.javadev.funtasking.makieiev;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;


public class PasswordGenerator {
    /**
     * It is all constant for generate random password
     * @param MINLENGTHPASSWORD min number of password
     * @param MAXLENGTHPASSWORD max number of password
     * @param DIFFPASSWORDLENGTH it's diff of min and max numbers
     * @param MIN it's first symbol of ASCII table for the password
     * @param MAX it's last symbol of ASCII table for the password
     * @param DIFF it's diff of min and max symbols of ASCII table
     */
    private static final int MINLENGTHPASSWORD = 8;
    private static final int MAXLENGTHPASSWORD = 16;
    private static final int DIFFPASSWORDLENGTH = MAXLENGTHPASSWORD - MINLENGTHPASSWORD;
    private static final int MIN = 32;
    private static final int MAX = 126;
    private static final int DIFF = MAX - MIN;

    /**
     * @return generate password
     */

    public String generatePassword(){
        Random random = new Random();
        int randomNumberForLength = random.nextInt(DIFFPASSWORDLENGTH + 1);
        randomNumberForLength += MINLENGTHPASSWORD;
        List<Character> passwordSymbol = getCharacterPassword(randomNumberForLength,random);
        StringBuilder password = new StringBuilder();

        passwordSymbol.stream().forEach(character -> password.append(character));

        return password.toString();
    }

    /**
     *
     * @param length it's random length for generate password from MINLENGTHPASSWORD to MAXLENGTHPASSWORD
     * @param random it's variable of Random class
     * @return List<Character> which has char symbols of password
     */

    private List<Character> getCharacterPassword(int length,Random random){
        List<Character> charPassword = new ArrayList<>();

        for(int i = 0;i< length;i++){
            int randomNumber = random.nextInt(DIFF + 1) + MIN;
            charPassword.add((char)randomNumber);
        }
        return charPassword;
    }

    public void printPassword(){
        System.out.println(generatePassword());
    }

    /**
     *
     * @param password it's generated password which write to txt file
     * This function use Base64 for encode password to byte code and write to the txt file
     */

    public void writeToFile(String password){
        try(FileOutputStream fos=new FileOutputStream("D://" + LocalDate.now() + ".txt"))
        {
            byte[] encodedBytes = Base64.getEncoder().encode(password.getBytes());
            fos.write(encodedBytes, 0, encodedBytes.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }finally {

        }
    }

    /**
     * This function use Base64 for decode password from txt file
     */

    public void readFile(){
        try(FileInputStream fin=new FileInputStream("D://" + LocalDate.now() + ".txt"))
        {
            System.out.printf("File size: %d bytes \n", fin.available());
            StringBuilder stringBuilder = new StringBuilder();
            int i=-1;
            while((i=fin.read())!=-1){
                stringBuilder.append((char)i);
            }
            byte[] decodeBytes = Base64.getDecoder().decode(stringBuilder.toString());
            System.out.print(new String(decodeBytes));
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }

}
