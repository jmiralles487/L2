package pe.gob.mtpe.rios.infraestructura.configuraciones;

public class EncodePassword {
    public static String encode(String username, String password) {
        int liCont = 0;
        int liMagia = 0;
        int liLong = 0;
        int liChar = 0;
        int liAscii1 = 0;
        int liAscii2 = 0;
        int liSigno = 0;
        String lsLogin = "";
        String lsClave = "";
        StringBuilder lsCripto = new StringBuilder();
        StringBuilder lsResult = new StringBuilder();
        String pLogin = username;
        String pClave = password;

        // Determinar el login y la clave según sus longitudes
        if (pLogin.trim().length() > pClave.trim().length()) {
            lsLogin = pLogin;
            lsClave = repeatAndTruncate(pClave, lsLogin.length());
        } else {
            lsClave = pClave;
            lsLogin = repeatAndTruncate(pLogin, lsClave.length());
        }

        liLong = lsLogin.length();

        // Calcular li_Magia
        for (liCont = 0; liCont < liLong; liCont++) {
            liMagia = (liMagia + getAscii(lsLogin.charAt(liCont))) % 255 + 1;
        }
        liMagia = (liMagia + totalSegundos()) % 255 + 1;

        // Crear ls_Cripto
        for (liCont = 0; liCont < liLong; liCont++) {
            liAscii1 = getAscii(lsLogin.charAt(liCont));
            liAscii2 = getAscii(lsClave.charAt(liCont));
            liChar = liAscii2 + liAscii1 + liMagia;
            lsCripto.append(completaCodigo(String.valueOf(liChar), 3));
        }
        lsCripto.append(completaCodigo(String.valueOf(liMagia), 3));
        lsCripto.append(completaCodigo(String.valueOf(pClave.length()), 2));

        // Crear ls_Result
        for (liCont = 1; liCont <= lsCripto.length(); liCont++) {
            liSigno = (liCont % 2 == 0) ? 1 : -1;
            liChar = getAscii(lsCripto.charAt(liCont - 1));
            lsResult.append(asciiToString(liChar + (liLong * liSigno)));
        }

        return lsResult.toString();
    }

    private static int getAscii(char value) {
        return (int) value;
    }

    private static String asciiToString(int asciiValue) {
        return String.valueOf((char) asciiValue);
    }

    private static int totalSegundos() {
        java.time.LocalTime now = java.time.LocalTime.now();
        int horas = now.getHour();
        int minutos = now.getMinute();
        int segundos = now.getSecond();
        return (horas * 3600) + (minutos * 60) + segundos;
    }

    private static String completaCodigo(String codigo, int tamanio) {
        StringBuilder ncodigo = new StringBuilder();
        if (codigo.length() >= tamanio) {
            ncodigo.append(codigo.substring(0, tamanio));
        } else {
            ncodigo.append(new String(new char[tamanio - codigo.length()]).replace('\0', '0'));
            ncodigo.append(codigo);
        }
        return ncodigo.toString();
    }

    private static String repeatAndTruncate(String input, int length) {
        StringBuilder result = new StringBuilder();
        while (result.length() < length) {
            result.append(input);
        }
        return result.substring(0, length);
    }
}