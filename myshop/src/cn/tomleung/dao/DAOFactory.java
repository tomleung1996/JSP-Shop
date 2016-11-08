package cn.tomleung.dao;

public class DAOFactory {
	public static UserDAO getUserDAOInstance(){  
        return new UserDAOImpl() ;  
    }
	public static GoodDAO getGoodDAOInstance(){  
        return new GoodDAOImpl() ;  
    } 
}
