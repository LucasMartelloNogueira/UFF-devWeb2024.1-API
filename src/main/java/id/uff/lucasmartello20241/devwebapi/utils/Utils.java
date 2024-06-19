package id.uff.lucasmartello20241.devwebapi.utils;

public class Utils {
    

    public static String createGenericEmail(String username) {

        return String.format("%s@gmail.com", username.replaceAll(" ", ""));
    }
}
