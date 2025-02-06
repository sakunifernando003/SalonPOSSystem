package org.example.demo.dao;

import org.example.demo.dao.SuperDAO;
import org.example.demo.dao.custom.Impl.CustomerDAOImpl;
import org.example.demo.dao.custom.Impl.EmployeeDAOImpl;

public class DAOFactory  {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory
                =new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
       EMPLOYEE, CUSTOMER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return (SuperDAO) new CustomerDAOImpl();

                case EMPLOYEE:
                    return (SuperDAO) new EmployeeDAOImpl();

            default:
                return null;
        }
    }

}
