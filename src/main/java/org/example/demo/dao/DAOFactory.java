package org.example.demo.dao;

import org.example.demo.dao.custom.Impl.AppointmentDAOImpl;
import org.example.demo.dao.custom.Impl.CustomerDAOImpl;
import org.example.demo.dao.custom.Impl.EmployeeDAOImpl;
import org.example.demo.dao.custom.Impl.UserDAOImpl;

public class DAOFactory  {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory
                =new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
       EMPLOYEE, User, CUSTOMERPROFILE, APPOINTMENT, CUSTOMER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();

                case EMPLOYEE:
                    return new EmployeeDAOImpl();
            case User:
                return new UserDAOImpl();
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            default:
                return null;
        }
    }

}
