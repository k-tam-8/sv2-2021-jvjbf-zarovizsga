package webshop;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.*;

public class ProductRepository {

    private MariaDbDataSource dataSource;

    public ProductRepository(MariaDbDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long insertProduct(String productName, int price, int stock) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("insert into products(product_name, price, stock) values(?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, productName);
            pstmt.setLong(2, price);
            pstmt.setLong(3, stock);
            pstmt.executeUpdate();

            return processStatementForGeneratedKeys(pstmt);

        } catch (SQLException sql) {
            throw new IllegalStateException("Cannot update db. ", sql);
        }
    }

    private long processStatementForGeneratedKeys(PreparedStatement pstmt) {
        try (ResultSet rs = pstmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
            throw new IllegalStateException("Insert to table failed.");
        } catch (SQLException sqle) {
            throw new IllegalStateException("No result found.", sqle);
        }
    }

    public Product findProductById(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select * from products where id =?")) {
            pstmt.setLong(1, id);
            return processStatementForProductCreate(pstmt);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot connect to db.", sqle);
        }
    }

    private Product processStatementForProductCreate(PreparedStatement pstmt) {
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new Product(rs.getLong("id"), rs.getString("product_name"),
                        (int) rs.getLong("price"), (int) rs.getLong("stock"));
            } else throw new IllegalArgumentException("No result found.");
        } catch (SQLException sqle) {
            throw new IllegalStateException("No result found.", sqle);
        }
    }

    public void updateProductStock(long id, int amount) {
        long productStock = getStockFromProductId(id);
        System.out.println(productStock);
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("update products set stock = ? where id=?")) {
            stmt.setLong(1, productStock - amount);
            stmt.setLong(2, id);
            stmt.execute();
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Cannot connect to db.", sqle);
        }
    }

    public long getStockFromProductId(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select * from products where id =?")) {
            pstmt.setLong(1, id);
            return processStatementForStock(id, pstmt);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot connect to db.", sqle);
        }
    }

    private long processStatementForStock(long id, PreparedStatement pstmt) {
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getLong("stock");
            }
            throw new IllegalArgumentException("Cant find product with id: " + id);
        } catch (SQLException sqle) {
            throw new IllegalStateException("No result found.", sqle);
        }
    }


}
