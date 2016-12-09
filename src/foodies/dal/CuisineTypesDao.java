package foodies.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import foodies.model.*;


public class CuisineTypesDao {
	
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static CuisineTypesDao instance = null;
	protected CuisineTypesDao() {
		connectionManager = new ConnectionManager();
	}
	public static CuisineTypesDao getInstance() {
		if(instance == null) {
			instance = new CuisineTypesDao();
		}
		return instance;
	}
	
	public CuisineTypes create(CuisineTypes cuisineType) throws SQLException {
		String insertCuisineType = "INSERT INTO CuisineTypes(Name,ParentId,ParentIds) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCuisineType,
					Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, cuisineType.getCuisineTypeName());
			insertStmt.setInt(2, cuisineType.getParentId());
			insertStmt.setString(3, cuisineType.getParentIds());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int cuisineTypeId = -1;
			if(resultKey.next()) {
				cuisineTypeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			cuisineType.setCuisineTypeId(cuisineTypeId);

			return cuisineType;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public CuisineTypes delete(CuisineTypes cuisineType) throws SQLException {

		String deleteCuisineType = "DELETE FROM CuisineTypes WHERE CuisineTypeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCuisineType);
			deleteStmt.setInt(1, cuisineType.getCuisineTypeId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public CuisineTypes getCuisineTypesById(int cuisineTypeId) throws SQLException {
		String selectCuisineType = "SELECT * FROM CuisineTypes WHERE CuisineTypeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCuisineType);
			selectStmt.setInt(1, cuisineTypeId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultCuisineId = results.getInt("CuisineTypeId");
				String cuisineTypeName = results.getString("Name");
				int parentId = results.getInt("ParentId");
				String parentIds = results.getString("ParentIds");

				CuisineTypes cuisineType = new CuisineTypes(resultCuisineId, cuisineTypeName, parentId, parentIds);
				return cuisineType;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public CuisineTypes getCuisineTypesByName(String name) throws SQLException {
		String selectCuisineType = "SELECT * FROM CuisineTypes WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCuisineType);
			selectStmt.setString(1, name);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultCuisineId = results.getInt("CuisineTypeId");
				int parentId = results.getInt("ParentId");
				String parentIds = results.getString("ParentIds");

				CuisineTypes cuisineType = new CuisineTypes(resultCuisineId, name, parentId, parentIds);
				return cuisineType;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<CuisineTypes> getCuisineTypesOrderByCreated() throws SQLException {
		List<CuisineTypes> cuisineTypes = new ArrayList<CuisineTypes>();
		String selectCuisineType = "SELECT * FROM CuisineTypes "
									+ "WHERE CuisineTypes.Name != 'recipes' "
									+ "ORDER BY CuisineTypeId LIMIT 10; ";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCuisineType);

			results = selectStmt.executeQuery();

			while(results.next()) {
				int resultCuisineId = results.getInt("CuisineTypeId");
				String cuisineTypeName = results.getString("Name");
				int parentId = results.getInt("ParentId");
				String parentIds = results.getString("ParentIds");

				CuisineTypes cuisineType = new CuisineTypes(resultCuisineId, cuisineTypeName, parentId, parentIds);
				cuisineTypes.add(cuisineType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return cuisineTypes;
	}

}
