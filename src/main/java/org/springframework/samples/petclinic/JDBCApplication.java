package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.samples.petclinic.owner.Owner;

public class JDBCApplication {

	public static void main(String[] args) {
		
		System.out.println("-------- Test de conexión con MySQL ------------");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentro el driver en el Classpath");
			e.printStackTrace();
			return;
		}

		System.out.println("Driver instalado y funcionando");
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic","root", "root");
			if (connection != null)
				System.out.println("Conexión establecida");
			
			statement = connection.createStatement();
			String sql = "SELECT * FROM owners";
			ResultSet re = statement.executeQuery(sql);
				while(re.next()) {
					int id = re.getInt("id");
					String firstName = re.getString("first_name");
					String last_name = re.getString("last_name");
					String address = re.getString("address");
					String city = re.getString("city");
					String telephone = re.getString("telephone");
					System.out.print("Id: " + id);
					System.out.print("Nombre: " + firstName);
					System.out.print("Apellido: " + last_name);
					System.out.print("Dirección: " + address);
					System.out.print("Ciudad: " + city);
					System.out.print("Teléfono: " + telephone);
				}
			
			
			re.close();
			
		    //String sql1 = "INSERT INTO owners (first_name, last_name, address, city, telephone)"+"VALUES('Pablo','Lujan','dirección','ciudad','teléfono')";
		    
		   /* String sql1_1="SELECT * FROM pets ";
		    ResultSet numeroUltimaMascota = statement.executeQuery(sql1_1);
		    int idUltimo = numeroUltimaMascota.getInt("id");*/
		    /*String sql1_1="SELECT id FROM owners WHERE first_name=?;";
		    PreparedStatement preparedStatement=connection.prepareStatement(sql);
		    preparedStatement.setString(1, "David");*/
		    //String sql2 = "INSERT INTO pets (id, birth_date, type_id, owner_id)"+"VALUES('14','Nacho','2002-07-29','6','11')";
			String sql1="UPDATE owners"+"SET city=Sevilla"+"where first_name=Pablo ";
		
		    ResultSet stateSql1 = statement.executeQuery(sql1);
		    stateSql1.close();
		    
		    String duenoBuscado = "George Lujan";
		    

			Owner nuevoDueno=new Owner();
			nuevoDueno.setFirstName("Pablo");
			nuevoDueno.setLastName("Lujan");
			nuevoDueno.setAddress("miCasa");
			nuevoDueno.setCity("miCiudad");
			nuevoDueno.setTelephone("miTelefono");
			nuevoDueno.setId(nuevoDueno.getId());
			
			String direccion = nuevoDueno.getAddress();
			String ciudad =nuevoDueno.getCity();
			String nombre =nuevoDueno.getFirstName();
			String apellido =nuevoDueno.getLastName();
			String telefono = nuevoDueno.getTelephone();
			sql= "INSERT INTO owners (first_name, last_name, address, city, telephone)"+"VALUES(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,nombre);
			preparedStatement.setString(2,apellido);
			preparedStatement.setString(3,direccion);
			preparedStatement.setString(4,ciudad);
			preparedStatement.setString(5,telefono);
		    System.out.println("Éxito");
		    
		    
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		} finally {
			try {
				if(statement != null)
					connection.close();
			} catch (SQLException se) {
		    	  
		    }
		    try {
		        if(connection != null)
		            connection.close();
		    } catch (SQLException se) {
		         	se.printStackTrace();
		    }
		}
	
		
		
	
	
	
	
	
	
	
	}

}
