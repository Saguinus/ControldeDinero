import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AppControlDinero {

    private static final String URL = "jdbc:mysql://localhost:3306/controldeldinero";
    private static final String USER = "root";
    private static final String PASSWORD = "Saguinus86*Oedipus";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("=== Bienvenido al Sistema Control del Dinero ===");

        while (opcion != 3) {
            System.out.println("\n1. Registrar nuevo usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarUsuario(scanner);
                    break;
                case 2:
                    iniciarSesion(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        scanner.close();
    }

    private static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void registrarUsuario(Scanner scanner) {
        System.out.print("Ingresa un nombre de usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingresa una contraseña: ");
        String password = scanner.nextLine();

        String sql = "INSERT INTO usuarios (usuario, password) VALUES (?, ?)";

        try (Connection conn = conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            
            System.out.println("¡Usuario registrado con éxito!");

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("Error: El nombre de usuario ya existe.");
            } else {
                System.out.println("Error al registrar: " + e.getMessage());
            }
        }
    }

    private static void iniciarSesion(Scanner scanner) {
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        String sql = "SELECT id FROM usuarios WHERE usuario = ? AND password = ?";

        try (Connection conn = conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("¡Inicio de sesión exitoso! Bienvenido, " + usuario + ".");
                } else {
                    System.out.println("Credenciales incorrectas. Verifica tu usuario o contraseña.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }
}