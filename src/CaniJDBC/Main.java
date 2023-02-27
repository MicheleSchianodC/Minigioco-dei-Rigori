package CaniJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		
		stampaCani();

	}

	
	
	public static void aggiungiCane(int id, String nome, int eta) {
		
		Connection conn = null; //questo reference sarà necessario per la connessione al database
		Statement stmt = null; //è un oggetto che astrarrà il concetto di statement SQL.
		
		String url = "jdbc:mysql://localhost:3306/cani";
		String username = "root";
		String password = "";
		
		try {
			
			//il secondo try è necessario per controllare che si sia il mysql connector
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			//DriverManager.getConnection crea un oggetto connection che registra le informazioni per la connessione al db
			conn = DriverManager.getConnection(url, username, password);
			//conn.createStatement fornisce le info sul database all'oggetto statement, così da poter realizzare query per QUEL database.
			stmt = conn.createStatement();
			
			String insert = "INSERT INTO dog VALUES(" + id + ", '" + nome + "', " + eta+");";
			//INSERT INTO dip VALUE(1, 'Nome', 'Cognome', 1234.56);
			
			int result = stmt.executeUpdate(insert);
			
			if(result > 0) {
				System.out.println(result + " rows affected");
			} else {
				System.out.println("Qualcosa è andato storto, aggiunta non effettuata!");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(stmt != null) {
				try {
					stmt.close();
					System.out.println("l'oggetto Statement è stato chiuso");
				} catch (SQLException exceptionStmt) {
					exceptionStmt.printStackTrace();
				}
			}
			stmt = null;

			if(conn != null) {
				try {
					conn.close();
					System.out.println("l'oggetto Connection è stato chiuso");
				} catch (SQLException exceptionConn) {
					exceptionConn.printStackTrace();
				}
			}
			conn = null;
		}

}

	public static void stampaCani() {
		
		Connection conn = null; //questo reference sarà necessario per la connessione al database
		Statement stmt = null; //è un oggetto che astrarrà il concetto di statement SQL.
		ResultSet rs = null; //ResultSet è un set dei risultati di una determinata query.
		
		
		String url = "jdbc:mysql://localhost:3306/cani";
		String username = "root";
		String password = "";
		
		try {
			
			//il secondo try è necessario per controllare che si sia il mysql connector
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			//DriverManager.getConnection crea un oggetto connection che registra le informazioni per la connessione al db
			conn = DriverManager.getConnection(url, username, password);
			//conn.createStatement fornisce le info sul database all'oggetto statement, così da poter realizzare query per QUEL database.
			stmt = conn.createStatement();
			//bisogna inizializzare l'oggetto resultSet a partire da stmt
			//String query = "SELECT * FROM dip";
			rs = stmt.executeQuery("SELECT * FROM dog");                  //rs va aggiunto solo se deve restiutire qualcoas, quindi negli stampa e negli Join
			
			System.out.println("Risultati della query: \n");
			//ATTENZIONE: solo per ResultSet si comincia a contare da UNO
			while(rs.next()) {
				System.out.println("id: " + rs.getInt(1) + " Nome: " + rs.getString(2) + " Età: " + rs.getInt(3));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(rs != null) {
				try {
					rs.close();
					System.out.println("l'oggetto Result Set è stato chiuso");
				} catch (SQLException exceptionRs) {
					exceptionRs.printStackTrace();
				}
			}
			rs = null;
			
			if(stmt != null) {
				try {
					stmt.close();
					System.out.println("l'oggetto Statement è stato chiuso");
				} catch (SQLException exceptionStmt) {
					exceptionStmt.printStackTrace();
				}
			}
			stmt = null;

			if(conn != null) {
				try {
					conn.close();
					System.out.println("l'oggetto Connection è stato chiuso");
				} catch (SQLException exceptionConn) {
					exceptionConn.printStackTrace();
				}
			}
			conn = null;
		}
		
	}	

	public static void cancellaCane(int id) {
		
		Connection conn = null; //questo reference sarà necessario per la connessione al database
		Statement stmt = null; //è un oggetto che astrarrà il concetto di statement SQL.
		
		String url = "jdbc:mysql://localhost:3306/cani";
		String username = "root";
		String password = "";
		
		try {
			
			//il secondo try è necessario per controllare che si sia il mysql connector
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			//DriverManager.getConnection crea un oggetto connection che registra le informazioni per la connessione al db
			conn = DriverManager.getConnection(url, username, password);
			//conn.createStatement fornisce le info sul database all'oggetto statement, così da poter realizzare query per QUEL database.
			stmt = conn.createStatement();
			
			String delete = "DELETE FROM dog WHERE id=("+id+");";    
			//INSERT INTO dip VALUE(1, 'Nome', 'Cognome', 1234.56);
			
			int result = stmt.executeUpdate(delete);
			
			if(result > 0) {
				System.out.println(result + " rows affected");
			} else {
				System.out.println("Qualcosa è andato storto, cancellazione non effettuata!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			if(stmt != null) {
				try {
					stmt.close();
					System.out.println("l'oggetto Statement è stato chiuso");
				} catch (SQLException exceptionStmt) {
					exceptionStmt.printStackTrace();
				}
			}
			stmt = null;

			if(conn != null) {
				try {
					conn.close();
					System.out.println("l'oggetto Connection è stato chiuso");
				} catch (SQLException exceptionConn) {
					exceptionConn.printStackTrace();
				}
			}
			conn = null;
		}
		
	}
	
	
}
