package pe.gob.mtpe.rios.infraestructura.configuraciones;

public class DecodePassword {

    public static String decode(String username, String password) {
        int liCont = 0;
        int liMagia = 0;
        int liLong = 0;
        int liAscii1 = 0;
        int liAscii2 = 0;
        int liChar = 0;
        int liSigno = 0;
        String lsLogin = "";
        String lsCripto = "";
        String pLogin = username;
        String pCripto = password;

        liLong = (pCripto.length() - 5) / 3;
        for (liCont = 1; liCont <= pCripto.length(); liCont++) {
            liSigno = (liCont % 2 == 0) ? 1 : -1;
            liChar = getAscii(pCripto.substring(liCont - 1, liCont));
            lsCripto += asciiToString(liChar - (liLong * liSigno));
        }

        liLong = Integer.parseInt(lsCripto.substring(lsCripto.length() - 2));
        pLogin = pLogin + pLogin + pLogin + pLogin;
        pLogin = pLogin.substring(0, liLong);

        liMagia = Integer.parseInt(lsCripto.substring(lsCripto.length() - 5, lsCripto.length() - 2));

        for (liCont = 1; liCont <= liLong; liCont++) {
            liAscii1 = getAscii(pLogin.substring(liCont - 1, liCont));
            liAscii2 = Integer.parseInt(lsCripto.substring((liCont * 3) - 3, (liCont * 3)));
            lsLogin += asciiToString(liAscii2 - liAscii1 - liMagia);
        }

        return lsLogin;
    }

    public static int getAscii(String character) {
        return (int) character.charAt(0);
    }

    public static String asciiToString(int asciiValue) {
        return String.valueOf((char) asciiValue);
    }
}